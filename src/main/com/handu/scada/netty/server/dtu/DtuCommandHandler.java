package main.com.handu.scada.netty.server.dtu;


import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DtuCommandHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && !ctx.channel().isActive()) {
            offline(ctx);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        if (channel != null && channel.isActive()) {
            String clientId = ctx.channel().id().asShortText();
            DtuNetworkConnection state = new DtuNetworkConnection(ctx.channel(), ctx);
            state.getCallback().online(clientId, null, MsgType.ONLINE);
            DtuChannelManager.addClient(clientId, state);
            //连接创建后3倍心跳后开始检查，以后每隔4倍心跳时间检查是否有数据传输，没有则断掉连接
            ctx.executor().scheduleAtFixedRate(new TimeOutRunnable(ctx), 3 * Config.getHeartBeat(), 4 * Config.getHeartBeat(), TimeUnit.MILLISECONDS);
            channel.closeFuture().addListener(new OfflineFutureListener(ctx));
        }
    }

    /**
     * 掉线监听
     */
    private class OfflineFutureListener implements ChannelFutureListener {

        OfflineFutureListener(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        private ChannelHandlerContext ctx;

        @Override
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (ctx != null) {
                offline(ctx);
            }
        }
    }

    /**
     * 超时处理
     * 一定时间没收到数据服务端主动断掉连接
     */
    private class TimeOutRunnable implements Runnable {

        private ChannelHandlerContext ctx;

        TimeOutRunnable(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            if (ctx != null) {
                Channel channel = ctx.channel();
                if (channel != null && channel.isActive()) {
                    String clientId = ctx.channel().id().asShortText();
                    DtuNetworkConnection state = DtuChannelManager.getNetworkState(clientId);
                    if (state != null) {
                        long time = System.currentTimeMillis() - state.getLastReceiptTime();
                        //如果最后一次接收时间与当前的时间差值大于三倍心跳就强制下线
                        boolean isActive = time < Config.getHeartBeat() * 3;
                        if (!isActive) channel.close();
                    }
                }
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf result = (ByteBuf) msg;
        int length = result.readableBytes();
        if (length > 0) {
            byte[] byteResult = new byte[length];
            try {
                // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
                result.readBytes(byteResult);
                if (byteResult.length > 0) {
                    //登录
                    if (byteResult[0] == (byte) 0x01) {
                        login(ctx, byteResult);
                    }
                    //心跳
                    else if (byteResult[0] == (byte) 0x02) {
                        heartbeat(ctx, byteResult);
                    }
                    //漏保上行下行都是5A
                    else if (byteResult[0] == (byte) 0x5A) {
                        serialPort(ctx, byteResult);
                    }
                    //0x5c,dtu配置报文特殊事件
                    else if (byteResult[0] == (byte) 0x5c) {
                        deviceInfo(ctx, byteResult);
                    }
                    //0x5e,远程升级
                    else if (byteResult[0] == (byte) 0x5E) {
                        dtuUpdate(ctx);
                    }
                    //二级漏保上报配置数据
                    else if (byteResult[0] == (byte) 0x5B) {
                        secondLpRecord(ctx, byteResult);
                    }
                    //集中器最后一次心跳时间
                    else if (byteResult[0] == 0x6B) {
                        concentratorHeartbeatTime(ctx, byteResult);
                    }
                    //信号强度
                    else if (byteResult[0] == 0x7B) {
                        signalStrength(ctx, byteResult);
                    }
                    //集中器台表数据
                    else if (byteResult[0] == 0x25) {
                        afn0c25(ctx, byteResult);
                    }
                    //ACK或者NACK帧
                    else if (byteResult[0] == 0x55 || byteResult[0] == (byte) 0xAA) {
                        ack(ctx, byteResult);
                    }//二级漏保上传升级文件
                    else if (byteResult[0] == 0x6E) {
                    }
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            } finally {
                //释放资源，这行很关键
                ReferenceCountUtil.release(result);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        if (Config.isDebug) ExceptionHandler.print(e);
        offline(ctx);
    }

    /**
     * 设备下线，异常断开
     *
     * @param ctx
     */
    private void offline(ChannelHandlerContext ctx) {
        if (ctx == null) return;
        try {
            String clientId = ctx.channel().id().asShortText();
            DtuNetworkConnection connection = DtuChannelManager.getNetworkState(clientId);
            if (connection != null) {
                String dtuAddress = DtuChannelManager.getDtuAddress(clientId);
                connection.getCallback().offline(clientId, dtuAddress);
                DtuChannelManager.removeClient(clientId, dtuAddress);
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        } finally {
            ctx.close();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 发送
     *
     * @param ctx
     * @param data
     */
    private void sendCommand(ChannelHandlerContext ctx, ByteBuf data) {
        ctx.writeAndFlush(data);
    }

    /**
     * 登录
     *
     * @param ctx
     * @param byteResult
     */
    private void login(ChannelHandlerContext ctx, byte[] byteResult) {
        printReceiveMsg(null, byteResult, "login");
        String dtuAddress = getDtuAddress(byteResult);
        if (!StringsUtils.isEmpty(dtuAddress)) {
            DtuChannelManager.update((ctx.channel()).id().asShortText(), dtuAddress, MsgType.LOGIN);
        }
        Integer loginResLength = 20 - 2;
        byte[] buffer2 = new byte[loginResLength];
        System.arraycopy(byteResult, 0, buffer2, 0, 9);
        //  buffer2[1] = 0x12;  // 数据长度
        buffer2[0] = 0x01; //addClient by 0016 2016-6-23 置为1，登录包。修改前DTU收为02
        buffer2[9] = 0x01; // -- 01 登录成功    00 登录不成功

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int month = cal.get(Calendar.MONTH);//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒

        buffer2[17] = (byte) (second);
        buffer2[16] = (byte) (minute);
        buffer2[15] = (byte) (hour);
        buffer2[14] = (byte) (day);
        buffer2[13] = (byte) (month);
        buffer2[12] = (byte) (year - 2000);

        ByteBuf byteBuf = ctx.alloc().buffer(loginResLength);
        byteBuf.writeBytes(buffer2);

        sendCommand(ctx, byteBuf);
    }

    /**
     * 心跳
     *
     * @param ctx
     * @param byteResult
     */
    private void heartbeat(ChannelHandlerContext ctx, byte[] byteResult) {
        String dtuAddress = getDtuAddress(byteResult);
        if (!StringsUtils.isEmpty(dtuAddress)) {
            DtuChannelManager.update((ctx.channel()).id().asShortText(), dtuAddress, MsgType.HEARTBEAT);
        }

        Integer beatResLength = 12 - 2;
        byte[] buffer;
        buffer = new byte[beatResLength];
        System.arraycopy(byteResult, 0, buffer, 0, 9);
        // buffer[1] = 0x0a;
        buffer[0] = 0x02;
        buffer[9] = 0x01; //01  -- 01 登录成功    00 登录不成功

        ByteBuf byteBuf = ctx.alloc().buffer(beatResLength); // (2)
        byteBuf.writeBytes(buffer);
        sendCommand(ctx, byteBuf);

        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "heartbeat");
            state.setLastReceiptTime(System.currentTimeMillis());
            state.nextSend();
        }
    }

    /**
     * 接收报文
     *
     * @param ctx
     * @param byteResult
     */
    private void serialPort(ChannelHandlerContext ctx, byte[] byteResult) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        byte[] bytes = Arrays.copyOfRange(byteResult, 1, byteResult.length);
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "serialPort");
            state.setLastReceiptTime(System.currentTimeMillis());
            state.notifyUpParse(bytes);
            state.nextSend();
        }
    }

    /**
     * 设备读取信息
     *
     * @param ctx
     * @param byteResult
     */
    private void deviceInfo(ChannelHandlerContext ctx, byte[] byteResult) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "deviceInfo");
            state.setLastReceiptTime(System.currentTimeMillis());
            String resultStr = new String(byteResult);
            LogUtils.info(state.getDtuAddress() + " info-->" + resultStr, true);
            state.nextSend();
        }
    }

    /**
     * 信号强度
     *
     * @param ctx
     * @param byteResult
     */
    private void signalStrength(ChannelHandlerContext ctx, byte[] byteResult) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "signalStrength");
            state.setLastReceiptTime(System.currentTimeMillis());
            String resultStr = new String(byteResult);
            LogUtils.info(state.getDtuAddress() + " signalStrength-->" + resultStr, true);
            state.nextSend();
        }
    }

    /**
     * 根据二级漏保上报配置参数创建漏保档案
     *
     * @param byteResult
     */
    private void secondLpRecord(ChannelHandlerContext ctx, byte[] byteResult) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "secondLpRecord");
            state.setLastReceiptTime(System.currentTimeMillis());
            state.createSecondLpRecord(byteResult);
            state.nextSend();
        }
    }

    /**
     * 集中器最后一次心跳时间
     */
    private void concentratorHeartbeatTime(ChannelHandlerContext ctx, byte[] byteResult) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "concentratorHeartbeatTime");
            state.setLastReceiptTime(System.currentTimeMillis());
            state.notifyUpParse(byteResult);
            state.nextSend();
        }
    }

    /**
     * dtu远程升级
     */
    private void dtuUpdate(ChannelHandlerContext ctx) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            state.setLastReceiptTime(System.currentTimeMillis());
            if (state.getProgress() < 1 && state.getProgress() > 0) {
                state.updateError();
            } else if (state.getProgress() == 0) {
                if (state.isUpdating()) {
                    state.update();
                }
            } else if (state.getProgress() == 1) {
                state.updateSuccess();
            }
        }
    }

    /**
     * 台表afn0c25数据
     */
    private void afn0c25(ChannelHandlerContext ctx, byte[] byteResult) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        byte[] bytes = Arrays.copyOfRange(byteResult, 1, byteResult.length);
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "afn0c25");
            state.setLastReceiptTime(System.currentTimeMillis());
            state.notifyUpParse(bytes);
            state.nextSend();
        }
    }

    /**
     * ack或nack帧
     *
     * @param ctx
     * @param byteResult
     */
    private void ack(ChannelHandlerContext ctx, byte[] byteResult) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            printReceiveMsg(state, byteResult, "ack");
            state.setLastReceiptTime(System.currentTimeMillis());
            state.nextSend();
        }
    }

    /**
     * 根据报文获取设备地址
     *
     * @param bytes
     * @return
     */
    private String getDtuAddress(byte[] bytes) {
        try {
            int dtuLength = 8;
            byte[] bDtuID = new byte[dtuLength];
            System.arraycopy(bytes, 1, bDtuID, 0, dtuLength);
            return HexUtils.byteToASCIIString(bDtuID);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    /**
     * 打印上行报文
     *
     * @param connection
     * @param bytes
     * @param type
     */
    private void printReceiveMsg(DtuNetworkConnection connection, byte[] bytes, String type) {
        if (Config.isDebug) {
            String resultStr = HexUtils.byteArrayToHexStr(bytes);
            LogUtils.info((connection == null ? "" : connection.getDtuAddress()) + " " + type + " receive-->" + resultStr);
        }
    }
}


