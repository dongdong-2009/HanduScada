package main.com.handu.scada.netty.server.protocol101;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import main.com.handu.scada.business.protocol101.Protocol101StateCallback;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.BaseEvent;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.protocol101.protocol.IProtocol101;
import main.com.handu.scada.protocol101.protocol.Protocol101DownParse;
import main.com.handu.scada.protocol101.protocol.Protocol101UpParse;
import main.com.handu.scada.protocol101.protocol.bean.BaseData;
import main.com.handu.scada.protocol101.protocol.enums.COT;
import main.com.handu.scada.protocol101.protocol.enums.DataType;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.protocol101.protocol.enums.Ti;
import main.com.handu.scada.utils.AnnotationUtils;
import main.com.handu.scada.utils.HexUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import static main.com.handu.scada.protocol101.protocol.enums.Ti.C_IC_NA_1;
import static main.com.handu.scada.protocol101.protocol.enums.Ti.M_EI_NA_1;

/**
 * Created by 柳梦 on 2018/03/13.
 * 开关报文处理
 */
public class Protocol101CommandHandler extends SimpleChannelInboundHandler<String> {

    private boolean isBusy = false;
    //控制域C是否为0x53
    private boolean is53 = true;
    //设备终端是否回复了确认帧
    private boolean hasConfirmed;
    /**
     * 是否最终初始化成功
     */
    private boolean isConnectionSuccess = false;
    private String deviceAddress;
    private ChannelHandlerContext context;
    private Protocol101StateCallback callback;
    /**
     * 发送命令低队列
     */
    private Queue<BaseData> lowQueue = new ArrayDeque<>();
    /**
     * 发送命令高队列
     */
    private Queue<BaseData> highQueue = new ArrayDeque<>();
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

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean is53() {
        return is53;
    }

    public void setIs53(boolean is53) {
        this.is53 = is53;
    }

    public boolean isHasConfirmed() {
        return hasConfirmed;
    }

    public void setHasConfirmed(boolean hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
    }

    public boolean isConnectionSuccess() {
        return isConnectionSuccess;
    }

    public void setConnectionSuccess(boolean connectionSuccess) {
        isConnectionSuccess = connectionSuccess;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public long getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public byte getAddressHigh() {
        return addressHigh;
    }

    public void setAddressHigh(byte addressHigh) {
        this.addressHigh = addressHigh;
    }

    public byte getAddressLow() {
        return addressLow;
    }

    public void setAddressLow(byte addressLow) {
        this.addressLow = addressLow;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && ctx.channel().isActive()) {
            this.context = ctx;
            this.callback = new Protocol101StateCallback();
            this.setLastSendTime(System.currentTimeMillis());
            init(IProtocol101.class);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        offline(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        offline(ctx);
        if (Config.isDebug) ExceptionHandler.print(cause);
    }

    /**
     * 下线
     */
    private void offline(ChannelHandlerContext ctx) {
        if (ctx == null) return;
        try {
            if (ctx.channel() != null && !ctx.channel().isActive()) {
                Protocol101CtxManager.removeHandler(getDeviceAddress());
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
            //msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
            byteBuf.readBytes(bytes);
            if (bytes.length > 0) {
                //101协议设备登录
                if (bytes[0] == (byte) 0xeb) {
                    login(ctx, bytes);
                }
                //确认
                else if (bytes[0] == (byte) 0x10) {
                    clientNotarize(ctx, bytes);
                }
                //上行
                else if (bytes[0] == 0x68) {
                    serialPort(ctx, bytes);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            //释放资源，这行很关键
            ReferenceCountUtil.release(byteBuf);
            //触发下一次发送
            if (!is53) setIs53(true);
            else setIs53(false);
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
    private void login(ChannelHandlerContext ctx, byte[] bytes) {
        if (bytes.length == 9) {
            byte[] b = new byte[2];
            System.arraycopy(bytes, 6, b, 0, b.length);
            int low = HexUtils.byteToInt(b[0]);
            int high = HexUtils.byteToInt(b[1]) * 256;
            //获取开关的地址
            String deviceAddress = String.valueOf(high + low);
            setAddressLow(b[0]);
            setAddressHigh(b[1]);
            setDeviceAddress(deviceAddress);
            //开始主站链路请求
            b = new byte[6];
            b[0] = 0x10;
            b[1] = 0x49;
            b[2] = getAddressLow();
            b[3] = getAddressHigh();
            byte checksum = (byte) (b[1] + b[2] + b[3]);
            b[4] = checksum;
            b[5] = 0x16;
            sendCommand(b);
            Protocol101CtxManager.addHandler(getDeviceAddress(), this);
            callback.online(ctx.channel().id().asShortText(), deviceAddress, MsgType.LOGIN);
        }
    }

    /**
     * 设备确认
     */
    private void clientNotarize(ChannelHandlerContext ctx, byte[] bytes) {
        if (bytes.length == 6) {
            byte[] b = new byte[4];
            System.arraycopy(bytes, 1, b, 0, b.length);
            byte checksum = (byte) (b[0] + b[1] + b[2]);
            if (b[3] == checksum) {
                //链路响应
                if (b[0] == (byte) 0x8b) {
                    //链路复位
                    b = new byte[]{0x10, 0x40, getAddressLow(), getAddressHigh(), 0x00, 0x16};
                    checksum = (byte) (0x40 + getAddressHigh() + getAddressLow());
                    b[b.length - 2] = checksum;
                    ByteBuf byteBuf = ctx.alloc().buffer(b.length);
                    byteBuf.writeBytes(b);
                    sendCommand(b);
                }
                //设备确认帧
                else if (b[0] == (byte) 0x80) {
                    setHasConfirmed(true);
                }
                //终端远方链路状态
                else if (b[0] == (byte) 0xc9) {
                    if (isHasConfirmed()) {
                        //响应链路状态
                        b = new byte[]{0x10, 0x0b, getAddressLow(), getAddressHigh(), 0x00, 0x16};
                        checksum = (byte) (0x0b + getAddressHigh() + getAddressLow());
                        b[b.length - 2] = checksum;
                        ByteBuf byteBuf = ctx.alloc().buffer(b.length);
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
    private void serialPort(ChannelHandlerContext ctx, byte[] bytes) {

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
                    callback.online(ctx.channel().id().asShortText(), getDeviceAddress(), MsgType.CONNECTION);
                    sendFirstAllCall();
                }
            }
            //遥信报文
            //68 12 12 68 73 ae 02 01 87 14 00 ae 02 01 00 01 00 00 01 00 00 00 72 16
            else if (tiType.getDataType() == DataType.YX) {
                sendConfirm();
                up(bytes);
            }
            //遥测报文
            //68 1d 1d 68 53 ae 02 09 86 14 00 ae 02 01 40 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 97 16
            else if (tiType.getDataType() == DataType.YC) {
                sendConfirm();
                up(bytes);
            }
        }
    }

    /**
     * 主站回复 10 00 xx xx xx 16
     */
    private void sendConfirm() {
        sendCommand(Protocol101CmdEnum.CONFIRM);
    }

    /**
     * 总召
     */
    private void sendFirstAllCall() {
        setConnectionSuccess(true);
        setIs53(true);
        //开始总召命令激活帧
        sendCommand(Protocol101CmdEnum.ALL_CALL);
    }

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

    /**
     * 解析上行报文
     *
     * @param bytes
     */
    public void up(byte[] bytes) {
        if (bytes == null) return;
        for (IProtocol101 protocol : iUpProtocols) {
            try {
                BaseData data = protocol.parse(bytes);
                if (data != null) {
                    EventManager.getInstance().publish(new BaseEvent(data));
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
     * @param downData
     */
    public synchronized void down(BaseData downData) {
        if (downData == null) return;
        for (IProtocol101 protocol : iDownParseProtocols) {
            try {
                downData = protocol.send(downData);
                break;
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }
        if (downData.getCommandData() != null) {
            sendCommand(downData);
        }
    }

    /**
     * @param bytes
     */
    public void sendCommand(byte[] bytes) {
        isBusy = true;
        ByteBuf byteBuf = context.alloc().buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        ChannelFuture f = context.writeAndFlush(byteBuf);
        f.addListener(future -> {
        });
    }

    public void sendCommand(Protocol101CmdEnum cmdEnum) {
        BaseData data = new BaseData();
        data.setAddress(getDeviceAddress());
        data.setCmdType(cmdEnum);
        data.setControlCode((byte) (is53() ? 0x53 : 0x73));
        down(data);
    }

    public void sendCommand(BaseData data) {
        if (data != null && data.getCommandData() != null) {
            if (!isBusy || System.currentTimeMillis() - getLastSendTime() > 60000) {
                isBusy = true;
                sendCommand(data.getCommandData());
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

    public void nextSend() {
        if (context == null) return;
        BaseData data;
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
            setBusy(false);
        }
    }
}
