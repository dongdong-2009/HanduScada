package main.com.handu.scada.netty.server.protocol101;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import main.com.handu.scada.business.protocol101.Protocol101StateCallback;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.db.bean.common.Device101CacheResult;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DBEvent;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.netty.server.protocol101.factory.Protocol101HandlerFactory;
import main.com.handu.scada.protocol101.faultRecord.FaultRecordFile;
import main.com.handu.scada.protocol101.faultRecord.FaultRecordFileManager;
import main.com.handu.scada.protocol101.faultRecord.FaultRecordJsonManager;
import main.com.handu.scada.protocol101.faultRecord.FileCmdType;
import main.com.handu.scada.protocol101.protocol.IProtocol101;
import main.com.handu.scada.protocol101.protocol.Protocol101DownParse;
import main.com.handu.scada.protocol101.protocol.Protocol101UpParse;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    private Queue<Protocol101Data> lowQueue = new ArrayDeque<>();
    /**
     * 发送命令高队列
     */
    private Queue<Protocol101Data> highQueue = new ArrayDeque<>();
    //最近一次发送时间
    private long lastSendTime;
    //最后一次接收时间
    private long lastReceiptTime;
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
    //负责建立链路的handler
    private IHandler handler;
    //设备类型
    private DeviceTypeEnum deviceTypeEnum;

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public byte getControlCode() {
        return controlCode;
    }

    public byte getFCB() {
        return FCB;
    }

    public boolean isHasConfirmed() {
        return hasConfirmed;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public Protocol101StateCallback getCallback() {
        return callback;
    }

    public Queue<Protocol101Data> getLowQueue() {
        return lowQueue;
    }

    public Queue<Protocol101Data> getHighQueue() {
        return highQueue;
    }

    public long getLastReceiptTime() {
        return lastReceiptTime;
    }

    public void setLastReceiptTime(long lastReceiptTime) {
        this.lastReceiptTime = lastReceiptTime;
    }

    public long getLastSendTime() {
        return lastSendTime;
    }

    public byte getAddressHigh() {
        return addressHigh;
    }

    public byte getAddressLow() {
        return addressLow;
    }

    public Set<IProtocol101> getiUpProtocols() {
        return iUpProtocols;
    }

    public Set<IProtocol101> getiDownParseProtocols() {
        return iDownParseProtocols;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setControlCode(byte controlCode) {
        this.controlCode = controlCode;
    }

    public void setFCB(byte FCB) {
        this.FCB = FCB;
    }

    public void setHasConfirmed(boolean hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public void setLastSendTime(long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public void setAddressHigh(byte addressHigh) {
        this.addressHigh = addressHigh;
    }

    public void setAddressLow(byte addressLow) {
        this.addressLow = addressLow;
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

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        online(ctx);
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
                callback.offline(ctx.channel().id().asShortText(), ip, port, deviceAddress);
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            ctx.close();
        }
    }

    /**
     * 上线
     */
    private void online(ChannelHandlerContext ctx) {
        if (ctx.channel() != null && ctx.channel().isActive()) {
            //15分钟没收到数据服务端主动断掉连接
            ctx.channel().closeFuture().addListener(future -> offline(ctx));
            ctx.executor().scheduleAtFixedRate(() -> {
                if (System.currentTimeMillis() - lastReceiptTime > 900) {
                    this.context.channel().close();
                }
            }, 900, 1200, TimeUnit.SECONDS);
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
                }
            }
            SocketAddress localAddress = ctx.channel().localAddress();
            address = localAddress.toString().replace("/", "");
            if (StringsUtils.isNotEmpty(address)) {
                String[] s = address.split(":");
                if (s.length == 2) {
                    port = s[1];
                }
            }
            //这里判断是否为无登录报文的101设备，检查是否配置了ip地址，
            //callback.online(ctx.channel().id().asShortText(), ip, port, address, MsgType.ONLINE);
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
                } else {
                    if (handler != null) {
                        handler.channelRead(bytes);
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            setLastReceiptTime(System.currentTimeMillis());
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
                int high = b[1] << 8;
                //获取设备的地址
                String deviceAddress = String.valueOf(high + low);
                addressLow = b[0];
                addressHigh = b[1];
                this.deviceAddress = deviceAddress;
                Protocol101CtxManager.addHandler(this.deviceAddress, this);
                callback.online(context.channel().id().asShortText(), ip, port, this.deviceAddress, MsgType.LOGIN);
                Device101CacheResult result = MyCacheManager.getDevice101CacheMap().get(this.deviceAddress);
                if (result != null) {
                    DeviceTypeEnum deviceTypeEnum = DeviceTypeEnum.getDeviceTypeByDeviceType(result.getDeviceType());
                    if (deviceTypeEnum != null) {
                        this.deviceTypeEnum = deviceTypeEnum;
                        handler = Protocol101HandlerFactory.getInstance().getHandler(this, this.deviceTypeEnum);
                        if (handler != null) {
                            //开始链路请求
                            handler.startLinkQuest();
                        }
                    }
                }
            }
        }
    }

    /**
     * 发送第一次总召
     */
    public void sendFirstAllCall() {
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
    public void upAnalysis(byte[] bytes) {
        if (bytes == null) return;
        for (IProtocol101 protocol : iUpProtocols) {
            try {
                Protocol101Data data = protocol.parse(bytes);
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
     * 读取录波文件目录和文件名称
     */
    public void readFaultFileCatalogue() {
        downAnalysis(new Protocol101Data() {{
            setCmdType(Protocol101CmdEnum.READ_FILE_CATALOGUE);
            setDeviceType(deviceTypeEnum);
            setAddress(deviceAddress);
        }});
    }

    /**
     * 读文件传输确认
     */
    public void readFaultFileConfirm(FaultRecordFile file) {
        byte data[] = new byte[]{
                FileCmdType.READ_FILE_RESPONSE_CONFIRM.getId(),
                file.getId1(),
                file.getId2(),
                file.getId3(),
                file.getId4(),
                file.getDataNum()[0],
                file.getDataNum()[1],
                file.getDataNum()[2],
                file.getDataNum()[3],
                file.getMore()
        };
        downAnalysis(new Protocol101Data() {{
            setCommandData(data);
            setCmdType(Protocol101CmdEnum.READ_FILE_CONFIRM);
            setDeviceType(deviceTypeEnum);
            setAddress(deviceAddress);
        }});
    }

    /**
     * 读取录波文件
     */
    public void readFaultRecordFile() {
        List<FaultRecordFile> files = FaultRecordFileManager.getInstance().getFaultRecordFiles(deviceAddress);
        if (files != null) {
            List<String> names = files.stream().map(FaultRecordFile::getName).collect(Collectors.toList());
            List<String> notReadFileNames = FaultRecordJsonManager.getInstance().getNotReadFileNames(deviceAddress, names);
            files = files.stream()
                    .filter(e -> notReadFileNames.contains(e.getName()))
                    .collect(Collectors.toList());
            for (FaultRecordFile file : files) {
                byte fileName[] = file.getName().getBytes();
                byte data[] = new byte[2 + fileName.length];
                //读文件激活
                data[0] = FileCmdType.READ_FILE_START.getId();
                //文件名长度
                data[1] = (byte) fileName.length;
                System.arraycopy(fileName, 0, data, 2, fileName.length);
                downAnalysis(new Protocol101Data() {{
                    setCommandData(data);
                    setCmdType(Protocol101CmdEnum.READ_FILE_START);
                    setDeviceType(deviceTypeEnum);
                    setAddress(deviceAddress);
                }});
            }
        }
    }

    /**
     * 解析下行报文
     *
     * @param data
     */
    public synchronized void downAnalysis(Protocol101Data data) {
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
    public void sendCommand(byte[] bytes) {
        try {
            isBusy = true;
            ByteBuf byteBuf = context.alloc().buffer(bytes.length);
            byteBuf.writeBytes(bytes);
            ChannelFuture f = context.writeAndFlush(byteBuf);
            f.addListener(future -> printSendMsg(bytes));
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * @param data
     */
    private void sendCommand(Protocol101Data data) {
        if (data != null && data.getCommandData() != null) {
            byte[] bytes = data.getCommandData();
            sendCommand(bytes);
        }
    }

    /**
     * 存入队列或直接发送
     *
     * @param data
     */
    private void sendOrInsertIntoQueue(Protocol101Data data) {
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
                printSendMsg(data.getCommandData(), "into queue");
            }
        }
    }

    /**
     * 下一次发送
     */
    private void nextSend() {
        Protocol101Data data;
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
            LogUtils.error("protocol101--" + deviceTypeEnum + "--" + deviceAddress + "--receive-->" + resultStr);
        }
    }

    /**
     * 打印下发报文
     *
     * @param bytes
     */
    private void printSendMsg(byte[] bytes) {
        printSendMsg(bytes, "send");
    }

    private void printSendMsg(byte[] bytes, String msg) {
        if (Config.isDebug) {
            String resultStr = HexUtils.byteArrayToHexStr(bytes);
            LogUtils.info("protocol101--" + deviceTypeEnum + "--" + deviceAddress + "--" + msg + "-->" + resultStr);
        }
    }
}
