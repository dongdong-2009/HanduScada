package main.com.handu.scada.netty.server.switch101;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import main.com.handu.scada.business.switch101.SwitchStateCallback;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.switch101.protocol.ISwitchProtocol;
import main.com.handu.scada.switch101.protocol.SwitchDownParse;
import main.com.handu.scada.switch101.protocol.SwitchUpParse;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.bean.DownData;
import main.com.handu.scada.utils.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Created by 柳梦 on 2018/03/13.
 */
public class SwitchNetworkConnection {

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
    private SwitchStateCallback callback;
    /**
     * 发送命令低队列
     */
    private Queue<MediaData> lowQueue = new ArrayDeque<>();
    /**
     * 发送命令高队列
     */
    private Queue<MediaData> highQueue = new ArrayDeque<>();
    //最近一次发送时间
    private long lastSendTime;

    //高位地址
    private byte addressHigh;

    //低位地址
    private byte addressLow;

    /**
     * 上行解析的类型集合
     */
    private Set<ISwitchProtocol> iUpProtocols = new HashSet<>();
    /**
     * 下发解析类型集合
     */
    private Set<ISwitchProtocol> iDownParseProtocols = new HashSet<>();

    public SwitchNetworkConnection(ChannelHandlerContext context) {
        this.context = context;
        this.callback = new SwitchStateCallback();
        this.setLastSendTime(System.currentTimeMillis());
        init(ISwitchProtocol.class);
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
                    if (anno.annotationType() == SwitchUpParse.class || anno.annotationType() == SwitchDownParse.class) {
                        try {
                            Constructor[] constructors = clazz.getDeclaredConstructors();
                            AccessibleObject.setAccessible(constructors, true);
                            //找到私有的构造函数
                            for (Constructor con : constructors) {
                                if (con.isAccessible()) {
                                    //调用构造函数获取解析者实例
                                    ISwitchProtocol iProtocol = (ISwitchProtocol) con.newInstance();
                                    //加入到解析列表中
                                    if (anno.annotationType() == SwitchUpParse.class) {
                                        iUpProtocols.add(iProtocol);
                                    } else if (anno.annotationType() == SwitchDownParse.class) {
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

    public boolean isHasConfirmed() {
        return hasConfirmed;
    }

    public void setHasConfirmed(boolean hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
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

    public boolean isConnectionSuccess() {
        return isConnectionSuccess;
    }

    public void setConnectionSuccess(boolean connectionSuccess) {
        isConnectionSuccess = connectionSuccess;
    }

    public SwitchStateCallback getCallback() {
        return callback;
    }

    public void setCallback(SwitchStateCallback callback) {
        this.callback = callback;
    }

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

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public Queue<MediaData> getLowQueue() {
        return lowQueue;
    }

    public void setLowQueue(Queue<MediaData> lowQueue) {
        this.lowQueue = lowQueue;
    }

    public Queue<MediaData> getHighQueue() {
        return highQueue;
    }

    public void setHighQueue(Queue<MediaData> highQueue) {
        this.highQueue = highQueue;
    }

    public long getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(long lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    /**
     * 解析上行报文
     *
     * @param bytes
     */
    public void parseUp(byte[] bytes) {
        try {
            if (bytes != null) {
                for (ISwitchProtocol protocol : iUpProtocols) {
                    BaseData data = protocol.parse(bytes);
                    if (data != null) {
                        System.err.println(data.toString());
                        return;
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 解析上行报文
     *
     * @param downData
     */
    public BaseData parseDown(DownData downData) {
        try {
            if (downData != null) {
                for (ISwitchProtocol protocol : iDownParseProtocols) {
                    BaseData data = protocol.send(downData);
                    if (data != null) {
                        return data;
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * 发送
     *
     * @param bytes
     */
    private void send(byte[] bytes) {
        if (getContext() != null) {
            ByteBuf byteBuf = getContext().alloc().buffer(bytes.length);
            byteBuf.writeBytes(bytes);
            sendCommand(getContext(), byteBuf);
        }
    }

    /**
     * 发送
     *
     * @param ctx
     * @param data
     */
    private void sendCommand(ChannelHandlerContext ctx, ByteBuf data) {
        ChannelFuture f = ctx.writeAndFlush(data);
        f.addListener(future -> data.release());
    }
}
