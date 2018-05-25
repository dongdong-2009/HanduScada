package main.com.handu.scada.netty.server.protocol101;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import main.com.handu.scada.business.protocol101.Protocol101StateCallback;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DBEvent;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.protocol101.protocol.IProtocol101;
import main.com.handu.scada.protocol101.protocol.Protocol101DownParse;
import main.com.handu.scada.protocol101.protocol.Protocol101UpParse;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101BaseData;
import main.com.handu.scada.protocol101.protocol.enums.COT;
import main.com.handu.scada.protocol101.protocol.enums.DataType;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.utils.AnnotationUtils;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketAddress;
import java.util.*;

import static main.com.handu.scada.protocol101.protocol.enums.Ti.C_IC_NA_1;
import static main.com.handu.scada.protocol101.protocol.enums.Ti.M_EI_NA_1;

/**
 * Created by 柳梦 on 2018/03/13.
 * 开关报文处理
 */
public class Protocol101CommandHandler extends SimpleChannelInboundHandler<String> {

    private String ip;
    private String port;
    private boolean isBusy = false;
    //控制域C根据FCB变化为0x53
    private byte controlCode = 0x53;
    //帧计数位,翻转计数
    private byte FCB = 0;
    //设备终端是否回复了确认帧
    private boolean hasConfirmed;
    private String deviceAddress;
    private ChannelHandlerContext context;
    private Protocol101StateCallback callback;
    /**
     * 发送命令低队列
     */
    private Queue<Protocol101BaseData> lowQueue = new ArrayDeque<>();
    /**
     * 发送命令高队列
     */
    private Queue<Protocol101BaseData> highQueue = new ArrayDeque<>();
    //最近一次发送时间
    private long lastSendTime;
    //高位地址
    private byte addressHigh;
    //低位地址
    private byte addressLow;
    /**
     * 上行解析的类型集合
     */
    private Set<IProtocol101> iUpProtocols = new HashSet<>();
    /**
     * 下发解析类型集合
     */
    private Set<IProtocol101> iDownParseProtocols = new HashSet<>();

    /**
     * 初始化获取解析类和下发类
     */
    private void init(Class c) {
        // 获取Main包下面的所有注解类
        Set<Class<?>> clazzs = AnnotationUtils.getClasses(c.getPackage().getName());
        if (clazzs != null && clazzs.size() > 0) {
            for (Class<?> clazz : clazzs) {
                // 获取类上的注解
                Annotation[] annos = clazz.getAnnotations();
                for (Annotation anno : annos) {
                    //如果该对象是我们需要的注解类型
                    if (anno.annotationType() == Protocol101UpParse.class || anno.annotationType() == Protocol101DownParse.class) {
                        try {
                            Constructor[] constructors = clazz.getDeclaredConstructors();
                            AccessibleObject.setAccessible(constructors, true);
                            //找到私有的构造函数
                            for (Constructor con : constructors) {
                                if (con.isAccessible()) {
                                    //调用构造函数获取解析者实例
                                    IProtocol101 iProtocol = (IProtocol101) con.newInstance();
                                    //加入到解析列表中
                                    if (anno.annotationType() == Protocol101UpParse.class) {
                                        iUpProtocols.add(iProtocol);
                                    } else if (anno.annotationType() == Protocol101DownParse.class) {
                                        iDownParseProtocols.add(iProtocol);
                                    }
                                }
                            }
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            ExceptionHandler.handle(e);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && ctx.channel().isActive()) {
            init(IProtocol101.class);
            this.context = ctx;
            this.callback = new Protocol101StateCallback();
            this.lastSendTime = System.currentTimeMillis();
            SocketAddress socketAddress = ctx.channel().remoteAddress();
            String address = socketAddress.toString().replace("/", "");
            if (StringsUtils.isNotEmpty(address)) {
                String[] s = address.split(":");
                if (s.length == 2) {
                    ip = s[0];
                    port = s[1];
                }
            }
            callback.online(ctx.channel().id().asShortText(), address, MsgType.ONLINE);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        offline(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (Config.isDebug) ExceptionHandler.print(cause);
        offline(ctx);
    }

    /**
     * 下线
     */
    private void offline(ChannelHandlerContext ctx) {
        if (ctx == null) return;
        try {
            if (ctx.channel() != null && !ctx.channel().isActive()) {
                Protocol101CtxManager.removeHandler(deviceAddress);
                callback.offline(ctx.channel().id().asShortText(), deviceAddress);
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            ctx.close();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        try {
            //msg中存储的是ByteBuf类型的数据，把数据
            // 读取到byte[]中
            byteBuf.readBytes(bytes);
            if (bytes.length > 0) {
                //101协议设备登录
                if (bytes[0] == (byte) 0xeb) {
                    login(bytes);
                }
                //确认
                else if (bytes[0] == (byte) 0x10) {
                    if (bytes.length == 6) {
                        clientNotarize(bytes);
                    }
                    //设备10帧和10帧粘包
                    else if (bytes.length == 12) {
                        byte[] b = new byte[6];
                        System.arraycopy(bytes, 0, b, 0, b.length);
                        clientNotarize(b);
                        b = new byte[6];
                        System.arraycopy(bytes, 6, b, 0, b.length);
                        clientNotarize(b);
                    }
                    //处理10帧和68帧粘包
                    else {
                        //长度要大于6才认为是10和68粘包
                        if (bytes.length > 6) {
                            byte[] b = Arrays.copyOf(bytes, 6);
                            clientNotarize(b);
                            b = new byte[bytes.length - 6];
                            System.arraycopy(bytes, 6, b, 0, b.length);
                            if (b[0] == 0x68) {
                                serialPort(b);
                            }
                        }
                    }
                }
                //上行
                else if (bytes[0] == 0x68) {
                    serialPort(bytes);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            if (bytes.length > 0) printReceiveMsg(bytes);
            //释放资源，这行很关键
            ReferenceCountUtil.release(byteBuf);
            //触发下一次发送
            nextSend();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 设备登录
     */
    private void login(byte[] bytes) {
        if (bytes.length >= 9) {
            byte[] b = new byte[3];
            System.arraycopy(bytes, 6, b, 0, b.length);
            if (b[2] == 0x16) {
                int low = HexUtils.byteToInt(b[0]);
                int high = HexUtils.byteToInt(b[1]) * 256;
                //获取开关的地址
                String deviceAddress = String.valueOf(high + low);
                addressLow = b[0];
                addressHigh = b[1];
                this.deviceAddress = deviceAddress;
                Protocol101CtxManager.addHandler(deviceAddress, this);
                callback.online(context.channel().id().asShortText(), this.deviceAddress, MsgType.LOGIN);
                startLinkQuest();
            }
        }
    }

    /**
     * 开始链路请求
     */
    private void startLinkQuest() {
        //开始主站链路请求
        byte[] b = new byte[6];
        b[0] = 0x10;
        b[1] = 0x49;
        b[2] = addressLow;
        b[3] = addressHigh;
        byte checksum = (byte) (b[1] + b[2] + b[3]);
        b[4] = checksum;
        b[5] = 0x16;
        sendCommand(b);
    }

    /**
     * 设备确认帧
     */
    private void clientNotarize(byte[] bytes) {
        if (bytes.length == 6) {
            byte[] b = new byte[4];
            System.arraycopy(bytes, 1, b, 0, b.length);
            byte checksum = (byte) (b[0] + b[1] + b[2]);
            if (b[3] == checksum) {
                //链路响应
                if (b[0] == (byte) 0x8b) {
                    //链路复位
                    b = new byte[]{0x10, 0x40, addressLow, addressHigh, 0x00, 0x16};
                    checksum = (byte) (0x40 + addressLow + addressHigh);
                    b[b.length - 2] = checksum;
                    ByteBuf byteBuf = context.alloc().buffer(b.length);
                    byteBuf.writeBytes(b);
                    sendCommand(b);
                }
                //设备确认帧
                else if (b[0] == (byte) 0x80) {
                    hasConfirmed = true;
                }
                //终端远方链路状态
                else if (b[0] == (byte) 0xc9) {
                    if (hasConfirmed) {
                        //响应链路状态
                        b = new byte[]{0x10, 0x0b, addressLow, addressHigh, 0x00, 0x16};
                        checksum = (byte) (0x0b + addressLow + addressHigh);
                        b[b.length - 2] = checksum;
                        ByteBuf byteBuf = context.alloc().buffer(b.length);
                        byteBuf.writeBytes(b);
                        sendCommand(b);
                    }
                }
                //终端远方复位链路
                else if (b[0] == (byte) 0xc0) {
                    //复位确认
                    sendConfirm();
                }
            }
        }
    }

    /**
     * 收到0x68开始的数据
     */
    private void serialPort(byte[] bytes) {
        if (bytes.length >= 9) {
            //标识码
            byte ti = bytes[7];
            //传送原因
            byte reason = bytes[9];
            //数据长度
            //byte length = bytes[1];
            Ti tiType = Ti.getTI(ti);
            if (tiType != null) {
                //召唤开始和终止(总召)
                if (tiType == C_IC_NA_1) {
                    //总召唤激活确认
                    if (reason == COT.COT07.getCot()) {
                        sendConfirm();
                    }
                    //总召唤激活终止
                    else if (reason == COT.COT0a.getCot()) {
                        sendConfirm();
                    }
                }
                //初始化结束
                else if (tiType == M_EI_NA_1) {
                    //总召
                    if (reason == COT.COT04.getCot()) {
                        callback.online(context.channel().id().asShortText(), deviceAddress, MsgType.CONNECTION_SUCCESS);
                        sendConfirm();
                        sendFirstAllCall();
                    }
                }
                //遥信报文
                //68 12 12 68 73 ae 02 01 87 14 00 ae 02 01 00 01 00 00 01 00 00 00 72 16
                else if (tiType.getDataType() == DataType.YX) {
                    sendConfirm();
                    upAnalysis(bytes);
                }
                //遥测报文
                //68 1d 1d 68 53 ae 02 09 86 14 00 ae 02 01 40 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 97 16
                else if (tiType.getDataType() == DataType.YC) {
                    sendConfirm();
                    upAnalysis(bytes);
                }
            }
        }
    }

    /**
     * 主站回复 10 00 xx xx xx 16
     */
    private void sendConfirm() {
        byte[] bytes = new byte[]{0x10, 0x00, addressLow, addressHigh, 0x00, 0x16};
        byte checksum = (byte) (addressLow + addressHigh);
        bytes[bytes.length - 2] = checksum;
        sendCommand(bytes);
    }

    /**
     * 发送第一次总召
     */
    private void sendFirstAllCall() {
        //第一次总召翻转位为0
        FCB = 0;
        byte QOI = 0x14;
        controlCode = 0x53;
        //开始总召命令激活帧
        byte bytes[] = new byte[]{
                0x68, 0x0C, 0x0C, 0x68,
                controlCode, addressLow, addressHigh,
                Ti.C_IC_NA_1.getTiType(),
                0x01, 0x06, 0x00, addressLow,
                addressHigh, 0x00, 0x00, QOI,
                0x00, 0x16
        };
        byte checksum = 0x00;
        for (int i = 4; i < bytes.length - 2; i++) {
            checksum += bytes[i];
        }
        bytes[bytes.length - 2] = checksum;
        sendCommand(bytes);
    }

    /**
     * 解析上行报文
     *
     * @param bytes
     */
    private void upAnalysis(byte[] bytes) {
        if (bytes == null) return;
        for (IProtocol101 protocol : iUpProtocols) {
            try {
                Protocol101BaseData data = protocol.parse(bytes);
                if (data != null) {
                    data.setIp(this.ip);
                    data.setPort(this.port);
                    EventManager.getInstance().publish(new DBEvent(data));
                    return;
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
    }

    /**
     * 解析下行报文
     *
     * @param data
     */
    public synchronized void downAnalysis(Protocol101BaseData data) {
        if (data == null) return;
        FCB = (byte) (FCB == 0x00 ? 0x01 : 0x00);
        controlCode = (byte) (FCB == 0x00 ? 0x53 : 0x73);
        data.setControlCode(controlCode);
        for (IProtocol101 protocol : iDownParseProtocols) {
            try {
                data = protocol.send(data);
                break;
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
        if (data.getCommandData() != null) {
            sendOrInsertIntoQueue(data);
        }
    }

    /**
     * @param bytes
     */
    private void sendCommand(byte[] bytes) {
        isBusy = true;
        ByteBuf byteBuf = context.alloc().buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        ChannelFuture f = context.writeAndFlush(byteBuf);
        f.addListener(future -> printSendMsg(bytes));
    }

    /**
     * @param data
     */
    private void sendCommand(Protocol101BaseData data) {
        if (data != null && data.getCommandData() != null) {
            byte[] bytes = data.getCommandData();
            isBusy = true;
            ByteBuf byteBuf = context.alloc().buffer(bytes.length);
            byteBuf.writeBytes(bytes);
            ChannelFuture f = context.writeAndFlush(byteBuf);
            f.addListener(future -> printSendMsg(bytes));
        }
    }

    /**
     * 存入队列或直接发送
     *
     * @param data
     */
    private void sendOrInsertIntoQueue(Protocol101BaseData data) {
        if (data != null && data.getCommandData() != null) {
            if (!isBusy || System.currentTimeMillis() - lastSendTime > 60000) {
                isBusy = true;
                sendCommand(data);
            } else {
                MsgPriority priority = data.getPriority();
                if (priority == MsgPriority.HIGH) {
                    highQueue.add(data);
                } else if (priority == MsgPriority.LOW) {
                    lowQueue.add(data);
                }
            }
        }
    }

    /**
     * 下一次发送
     */
    private void nextSend() {
        Protocol101BaseData data;
        if (highQueue.size() > 0) {
            data = highQueue.poll();
            if (data != null) {
                sendCommand(data);
            }
        } else if (lowQueue.size() > 0) {
            data = lowQueue.poll();
            if (data != null) {
                sendCommand(data);
            }
        } else {
            isBusy = false;
        }
    }

    /**
     * 打印上行报文
     *
     * @param bytes
     */
    private void printReceiveMsg(byte[] bytes) {
        if (Config.isDebug) {
            String resultStr = HexUtils.byteArrayToHexStr(bytes);
            LogUtils.error("protocol101 switch " + deviceAddress + " receive-->" + resultStr);
        }
    }

    /**
     * 打印下发报文
     *
     * @param bytes
     */
    private void printSendMsg(byte[] bytes) {
        if (Config.isDebug) {
            String resultStr = HexUtils.byteArrayToHexStr(bytes);
            LogUtils.info("protocol101 switch " + deviceAddress + " downAnalysis-->" + resultStr);
        }
    }
}
