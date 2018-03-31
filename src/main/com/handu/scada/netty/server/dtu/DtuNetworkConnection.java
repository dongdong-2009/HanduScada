package main.com.handu.scada.netty.server.dtu;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import main.com.handu.scada.business.dtu.DtuStateCallback;
import main.com.handu.scada.business.dtu.DtuUpdateUtil;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DBEvent;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuDownParse;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.protocol.DLT645.impl.SecondLpRecordCreate;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.quartz.utils.DtuCommand;
import main.com.handu.scada.utils.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum.DTU_INFO;
import static main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum.DTU_RESTART;

public class DtuNetworkConnection {

    private boolean isBusy;
    private Channel channel;
    private String deviceAddress;
    private ChannelHandlerContext context;
    private String dtuAddress;
    private int sendCount;
    private int receiveCount;
    private DtuStateCallback callback;
    /**
     * 发送命令低队列
     */
    private Queue<MediaData> lowQueue = new ArrayDeque<>();
    /**
     * 发送命令高队列
     */
    private Queue<MediaData> highQueue = new ArrayDeque<>();

    /**
     * 升级
     */
    private boolean isUpdating = false;
    private float progress;
    private List<byte[]> updateBuffList;


    //最近一次发送时间
    private long lastSendTime;
    private long lastReceiptTime;

    /**
     * 上行解析的类型集合
     */
    private Set<IProtocol> iUpProtocols = new HashSet<>();
    /**
     * 下发解析类型集合
     */
    private Set<IProtocol> iDownParseProtocols = new HashSet<>();

    public boolean isUpdating() {
        return isUpdating;
    }

    public void setUpdating(boolean updating) {
        isUpdating = updating;
    }

    public List<byte[]> getUpdateBuffList() {
        return updateBuffList;
    }

    public void setUpdateBuffList(List<byte[]> updateBuffList) {
        this.updateBuffList = updateBuffList;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public DtuStateCallback getCallback() {
        return callback;
    }

    public void setCallback(DtuStateCallback callback) {
        this.callback = callback;
    }

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }

    public int getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(int receiveCount) {
        this.receiveCount = receiveCount;
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

    public long getLastReceiptTime() {
        return lastReceiptTime;
    }

    public void setLastReceiptTime(long lastReceiptTime) {
        this.lastReceiptTime = lastReceiptTime;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
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

    public DtuNetworkConnection(Channel channel, ChannelHandlerContext context, DtuStateCallback callback) {
        this.channel = channel;
        this.context = context;
        this.callback = callback;
        this.setLastSendTime(System.currentTimeMillis());
        init(IProtocol.class);
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    /**
     * 初始化解析类
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
                    if (anno.annotationType() == DtuUpParse.class || anno.annotationType() == DtuDownParse.class) {
                        try {
                            Constructor[] constructors = clazz.getDeclaredConstructors();
                            AccessibleObject.setAccessible(constructors, true);
                            //找到私有的构造函数
                            for (Constructor con : constructors) {
                                if (con.isAccessible()) {
                                    //调用构造函数获取解析者实例
                                    IProtocol iProtocol = (IProtocol) con.newInstance();
                                    //加入到解析列表中
                                    if (anno.annotationType() == DtuUpParse.class) {
                                        iUpProtocols.add(iProtocol);
                                    } else if (anno.annotationType() == DtuDownParse.class) {
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
     * 如果是二级漏保档案上报,创建二级漏保档案
     */
    public void createSecondLpRecord(byte[] bytes) {
        MediaData mediaData = new MediaData();
        mediaData.DTUString = getDtuAddress();
        mediaData.CommandData = bytes;
        SecondLpRecordCreate create = new SecondLpRecordCreate();
        try {
            ProtocolLayerData protocolLayerData = create.parse(mediaData);
            if (protocolLayerData != null) {
                EventManager.getInstance().publish(new DBEvent(protocolLayerData));
            }
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
    }

    /**
     * 通知相应的解析类开始解析
     *
     * @param bytes 数据源
     */
    public void notifyUpParse(byte[] bytes) {
        MediaData mediaData = new MediaData();
        mediaData.CommandData = bytes;
        mediaData.DTUString = getDtuAddress();
        for (IProtocol protocol : iUpProtocols) {
            try {
                ProtocolLayerData protocolLayerData = protocol.parse(mediaData);
                if (protocolLayerData != null) {
                    //如果是二级漏保的整点遥测上报
                    List<List<DataAttr>> list = protocolLayerData.secondAttrList;
                    if (list != null && list.size() > 0) {
                        for (List<DataAttr> dataAttrs : list) {
                            ProtocolLayerData p = new ProtocolLayerData();
                            p.CommandData = protocolLayerData.CommandData;
                            p.CommandName = protocolLayerData.CommandName;
                            p.DLT645Address = protocolLayerData.DLT645Address;
                            p.PostalAddress = protocolLayerData.PostalAddress;
                            p.DTUString = protocolLayerData.DTUString;
                            p.attrList = dataAttrs;
                            p.TabName = protocolLayerData.TabName;
                            p.CmdType = protocolLayerData.CmdType;
                            p.tripEventRecord = protocolLayerData.tripEventRecord;
                            p.controlWord = protocolLayerData.controlWord;
                            EventManager.getInstance().publish(new DBEvent(p));
                        }
                    } else {
                        //其他的漏保数据上则通知入库
                        EventManager.getInstance().publish(new DBEvent(protocolLayerData));
                    }
                    return;
                }
            } catch (Exception e) {
                ExceptionHandler.print(e);
            }
        }
    }

    /**
     * 通知下行解析
     */
    public MediaData notifyDownParse(ProtocolLayerData protocolLayerData) {
        if (protocolLayerData.CmdType == DTU_INFO) {
            LogUtils.info("dtu " + protocolLayerData.DTUString + " read info ...", true);
            return new MediaData() {{
                CommandData = new byte[]{0X5C};
                deviceTypeEnum = DeviceTypeEnum.DTU;
                DTUString = protocolLayerData.DTUString;
            }};
        }
        if (protocolLayerData.CmdType == DTU_RESTART) {
            LogUtils.info("dtu " + protocolLayerData.DTUString + " restart...", true);
            return new MediaData() {{
                CommandData = new byte[]{(byte) 0x5F};
                deviceTypeEnum = DeviceTypeEnum.DTU;
                DTUString = protocolLayerData.DTUString;
            }};
        }
        for (IProtocol protocol : iDownParseProtocols) {
            try {
                MediaData data = protocol.sendCommand(protocolLayerData);
                if (data != null) {
                    if (data.CommandData != null) {
                        byte[] bytes = new byte[data.CommandData.length + 1];
                        bytes[0] = 0x5A;
                        System.arraycopy(data.CommandData, 0, bytes, 1, data.CommandData.length);
                        data.CommandData = bytes;
                        return data;
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.print(e);
            }
        }
        return null;
    }

    /**
     * 获取未下发的命令数量
     *
     * @return
     */
    public int getQueueSize() {
        return highQueue.size() + lowQueue.size();
    }

    /**
     * 清空发送队列
     */
    public void clearQueue() {
        highQueue.clear();
        lowQueue.clear();
    }

    /**
     * 开始升级请求
     *
     * @param dtuAddress
     */
    public void startUpdate(List<byte[]> updateBuffList, String dtuAddress) {
        try {
            if (Objects.equals(this.dtuAddress, dtuAddress)) {
                if (!isUpdating) {
                    this.setUpdateBuffList(updateBuffList);
                    isUpdating = true;
                    handShake();
                } else {
                    LogUtils.error(dtuAddress + "is updating,please wait...", true);
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    /**
     * 更新错误
     */
    public void updateError() {
        progress = 0;
        isUpdating = false;
        LogUtils.error(dtuAddress + " update error...", true);
        TxtUtils.updateError(dtuAddress);
    }

    /**
     * 更新停止
     */
    public void stopUpdate() {
        progress = 0;
        this.updateBuffList = null;
        isUpdating = false;
        LogUtils.error(dtuAddress + " stop update...", true);
    }

    /**
     * 更新成功
     */
    public void updateSuccess() {
        progress = 0;
        isUpdating = false;
        this.updateBuffList = null;
        LogUtils.info(dtuAddress + " update success...", true);
        TxtUtils.updateSuccess(dtuAddress);
        DtuCommand.getInstance().restartDtu(dtuAddress);
    }

    /**
     * 发送
     *
     * @param mediaData
     */
    private void sendUpdateCommand(MediaData mediaData) {
        ChannelHandlerContext context = this.getContext();
        if (context == null) return;
        ByteBuf byteBuf = context.alloc().buffer(mediaData.CommandData.length);
        byteBuf.writeBytes(mediaData.CommandData);
        context.writeAndFlush(byteBuf);
    }

    /**
     * 发送升级请求握手帧
     */
    private void handShake() {
        byte[] buff = getHandShakeBuffer(getUpdateBuffList());
        if (buff != null) {
            sendUpdateCommand(buff);
            LogUtils.info(dtuAddress + "update command  has send,please wait...", true);
        }
    }

    /**
     * 发送升级数据
     *
     * @param bytes
     */
    private void sendUpdateCommand(byte[] bytes) {
        MediaData mediaData = new MediaData() {{
            DTUString = dtuAddress;
            CommandData = bytes;
            HasDTUHead = false;
        }};
        sendUpdateCommand(mediaData);
    }

    /**
     * 开始正式升级
     */
    public void update() {
        progress = 0;
        LogUtils.info(dtuAddress + "start update...", true);
        new Thread(() -> {
            try {
                int frameNo = 0;
                byte[] bytes;
                int count = getUpdateBuffList().size();
                List<byte[]> firstList = new ArrayList<>();
                List<byte[]> secondList = new ArrayList<>();

                while (isUpdating) {
                    if (frameNo < count) {
                        //等于64K时,暂停3秒,等待DTU写入
                        if (frameNo == 0x40) {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ignored) {
                            }
                        }
                        if (frameNo < 0x40) {
                            firstList.add(getUpdateBuffList().get(frameNo));
                        } else {
                            secondList.add(getUpdateBuffList().get(frameNo));
                        }
                        bytes = getSendCmd(getUpdateBuffList().get(frameNo), (byte) 0x5e, (byte) 0x02, frameNo);
                        sendUpdateCommand(bytes);
                        frameNo++;
                        progress = frameNo * 1f / (count + 1);
                        Thread.sleep(DtuUpdateUtil.interval);
                    }
                    //已发送完毕,暂停2秒等待写入,发送校验位
                    else {
                        Thread.sleep(2000);
                        byte[] cs = new byte[4];
                        cs[0] = 0x00;
                        cs[1] = 0x00;
                        cs[2] = 0x00;
                        cs[3] = 0x00;
                        byte[] allBytes1 = getAllBytes(firstList);
                        int crc = Crc16Utils.calcCrc16(allBytes1, 0, 65535);
                        cs[1] = (byte) (crc & 0xFF);
                        cs[0] = (byte) (crc >> 0x08);
                        if (secondList.size() > 0) {
                            byte[] allBytes2 = getAllBytes(secondList);
                            crc = Crc16Utils.calcCrc16(allBytes2, 0, 65535);
                            cs[3] = (byte) (crc & 0xFF);
                            cs[2] = (byte) (crc >> 0x08);
                        } else {
                            cs[3] = 0x00;
                            cs[2] = (byte) 0xFF;
                        }
                        bytes = getSendCmd(cs, (byte) 0x5e, (byte) 0x02, frameNo);
                        sendUpdateCommand(bytes);
                        frameNo++;
                        progress = 1f;
                        isUpdating = false;
                    }
                    if ((progress * 100) % 20 == 0) {
                        String p = String.format("%.2f", progress * 100) + "%";
                        LogUtils.info(dtuAddress + "update progress--" + p, true);
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
        }).start();
    }

    /**
     * 获取所有数据的bytes
     *
     * @param updateBuffList
     * @return
     */
    private byte[] getAllBytes(List<byte[]> updateBuffList) {
        try {
            byte[] bytes = new byte[65535];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) 0xFF;
            }

            List<Byte> list = new ArrayList<>();
            for (byte[] b : updateBuffList) {
                for (byte b1 : b) {
                    list.add(b1);
                }
            }

            Byte[] temps = list.toArray(new Byte[list.size()]);
            if (temps.length > 65535) {
                bytes = new byte[temps.length];
            }
            for (int i = 0; i < temps.length; i++) {
                bytes[i] = temps[i];
            }
            return bytes;
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * 获取升级握手报文
     *
     * @param sendBufferList
     * @return
     */
    private byte[] getHandShakeBuffer(List<byte[]> sendBufferList) {
        try {
            byte[] bytes = new byte[4];
            if (sendBufferList == null || sendBufferList.size() < 1) return null;
            int count = sendBufferList.size() + 1;//增加一个激活贞
            int count_ = sendBufferList.get(0).length;
            String hex = HexUtils.hexPadLeft(Integer.toHexString(count), 4);
            String hex_ = HexUtils.hexPadLeft(Integer.toHexString(count_), 4);
            bytes[0] = HexUtils.HexToByte(hex.substring(0, 2));
            bytes[1] = HexUtils.HexToByte(hex.substring(2, 4));
            bytes[2] = HexUtils.HexToByte(hex_.substring(0, 2));
            bytes[3] = HexUtils.HexToByte(hex_.substring(2, 4));
            return getSendCmd(bytes, (byte) 0x5e, (byte) 0x01, 0);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * 获取下发报文
     *
     * @param bytes
     * @param protocol
     * @param frame
     * @param frameNo
     * @return
     */
    private byte[] getSendCmd(byte[] bytes, byte protocol, byte frame, int frameNo) {
        try {
            byte[] cmd = new byte[bytes.length + 10];
            //String frameLen = HexUtils.intToHex(cmd.length - 2);
            //cmd[0] = Byte.parseByte(frameLen.substring(0, 2));//适配众山协议
            //cmd[1] = Byte.parseByte(frameLen.substring(2, 2));
            cmd[0] = protocol;//功能码
            //以下代码适配自定义协议
            cmd[1] = (byte) 0xff;
            String frameLen = HexUtils.hexPadLeft(Integer.toHexString(bytes.length + 3), 4);
            cmd[2] = HexUtils.HexToByte(frameLen.substring(0, 2));//适配自定义协议 帧长度
            cmd[3] = HexUtils.HexToByte(frameLen.substring(2, 4));//适配自定义协议
            cmd[4] = frame;
            cmd[5] = HexUtils.HexToByte(HexUtils.hexPadLeft(Integer.toHexString(frameNo), 4).substring(0, 2));//适配自定义协议 帧序号
            //cmd[5] = 0;//适配自定义协议 帧序号
            cmd[6] = HexUtils.HexToByte(HexUtils.hexPadLeft(Integer.toHexString(frameNo), 4).substring(2, 4));//适配自定义协议
            //cmd[6] = (byte) frameNo;//适配自定义协议
            System.arraycopy(bytes, 0, cmd, 7, bytes.length); //拷贝自定义协议中的数据部分
            int crc = Crc16Utils.calcCrc16(cmd, 4, bytes.length + 3);
            cmd[cmd.length - 2] = (byte) (crc & 0xff);
            cmd[cmd.length - 3] = (byte) (crc >> 0x08);
            cmd[cmd.length - 1] = (byte) 0xfe;
            return cmd;
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}

