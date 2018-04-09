package main.com.handu.scada.netty.server.dtu;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import main.com.handu.scada.business.dtu.DtuStateCallback;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.LogUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Queue;


public class DtuCommandHandler extends SimpleChannelInboundHandler<String> {

    private DtuStateCallback callback = new DtuStateCallback();

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && !ctx.channel().isActive()) {
            offline(ctx);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && ctx.channel().isActive()) {
            String clientId = ctx.channel().id().asShortText();
            callback.online(clientId, null, MsgType.ONLINE);
            DtuNetworkConnection state = new DtuNetworkConnection(ctx.channel(), ctx, callback);
            DtuChannelManager.addClient(clientId, state);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf result = (ByteBuf) msg;
        byte[] byteResult = new byte[result.readableBytes()];
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
                //二级漏保上传升级文件
                else if (byteResult[0] == 0x6E) {
                }
            }

        } catch (InterruptedException e) {
            ExceptionHandler.handle(e);
        } finally {
            // 释放资源，这行很关键
            result.release();
            //更改最后一次接收时间
            DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
            if (state != null) {
                state.setLastReceiptTime(System.currentTimeMillis());
            }
            if (Config.isDebug) {
                String resultStr = HexUtils.byteArrayToHexStr(byteResult);
                LogUtils.info("dtu channel read " + "data=" + resultStr);
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
        try {
            String clientId = ctx.channel().id().asShortText();
            String dtuAddress = DtuChannelManager.getDtuAddress(clientId);
            callback.offline(clientId, dtuAddress);
            DtuChannelManager.removeClient(clientId, dtuAddress);
        } catch (Exception e1) {
            ExceptionHandler.print(e1);
        } finally {
            ctx.close();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //写一个空的buf，并刷新写出区域。完成后关闭sock channel连接。
        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
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

    /**
     * 登录
     *
     * @param ctx
     * @param byteResult
     */
    private void login(ChannelHandlerContext ctx, byte[] byteResult) {
        printMsg(byteResult, "login");
        String dtuAddress = getDtuAddress(byteResult);
        DtuChannelManager.update((ctx.channel()).id().asShortText(), dtuAddress, MsgType.LOGIN);
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
     * @throws InterruptedException
     */
    private void heartbeat(ChannelHandlerContext ctx, byte[] byteResult) throws InterruptedException {
        printMsg(byteResult, "heartbeat");
        String dtuAddress = getDtuAddress(byteResult);
        DtuChannelManager.update((ctx.channel()).id().asShortText(), dtuAddress, MsgType.HEARTBEAT);

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
        Thread.sleep(800);
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            //最近一次接收时间
            nextSend(state, MsgType.HEARTBEAT);
        }
    }

    /**
     * 触发下一次发送
     *
     * @param state
     * @param type
     */
    private void nextSend(DtuNetworkConnection state, MsgType type) {
        ChannelHandlerContext context = state.getContext();
        if (context == null) return;
        MediaData data;
        Queue<MediaData> highQueue = state.getHighQueue();
        Queue<MediaData> lowQueue = state.getLowQueue();
        if (highQueue.size() > 0) {
            data = highQueue.poll();
            if (data != null) {
                ByteBuf byteBuf = context.alloc().buffer(data.CommandData.length);
                byteBuf.writeBytes(data.CommandData);
                state.setBusy(data.isWaitReceive);
                state.setLastSendTime(System.currentTimeMillis());
                sendCommand(context, byteBuf);
                printCommand(data, type);
            }
        } else if (lowQueue.size() > 0) {
            data = lowQueue.poll();
            if (data != null) {
                ByteBuf byteBuf = context.alloc().buffer(data.CommandData.length);
                byteBuf.writeBytes(data.CommandData);
                state.setLastSendTime(System.currentTimeMillis());
                state.setBusy(data.isWaitReceive);
                sendCommand(context, byteBuf);
                printCommand(data, type);
            }
        } else {
            state.setBusy(false);
        }
    }

    /**
     * 打印下发报文
     *
     * @param data
     */
    private void printCommand(MediaData data, MsgType type) {
        if (Config.isDebug) {
            String resultStr = HexUtils.byteArrayToHexStr(data.CommandData);
            LogUtils.info(type.name() + " next send-->deviceType=" + data.deviceTypeEnum.name() + ",cmdType=" + data.cmdTypeEnum.name() + ",data=" + resultStr);
        }
    }

    /**
     * 打印上行报文
     *
     * @param bytes
     * @param str
     */
    private void printMsg(byte[] bytes, String str) {
        if (Config.isDebug) {
            String resultStr = HexUtils.byteArrayToHexStr(bytes);
            LogUtils.info(str + "-->" + resultStr);
        }
    }

    /**
     * 接收报文
     *
     * @param ctx
     * @param byteResult
     * @throws InterruptedException
     */
    private void serialPort(ChannelHandlerContext ctx, byte[] byteResult) throws InterruptedException {
        printMsg(byteResult, "serialPort");
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        byte[] bytes = Arrays.copyOfRange(byteResult, 1, byteResult.length);
        if (state != null && state.getChannel().isActive()) {
            state.notifyUpParse(bytes);
            nextSend(state, MsgType.SERIAL_PORT);
        }
    }

    /**
     * 设备读取信息
     *
     * @param ctx
     * @param byteResult
     */
    private void deviceInfo(ChannelHandlerContext ctx, byte[] byteResult) throws InterruptedException {
        printMsg(byteResult, "deviceInfo");
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            String resultStr = new String(byteResult);
            LogUtils.info(state.getDtuAddress() + " info-->" + resultStr, true);
            nextSend(state, MsgType.DEVICE_INFO);
        }
    }

    /**
     * 根据二级漏保上报配置参数创建漏保档案
     *
     * @param byteResult
     */
    private void secondLpRecord(ChannelHandlerContext ctx, byte[] byteResult) {
        printMsg(byteResult, "secondLpRecord");
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
            state.createSecondLpRecord(byteResult);
        }
    }

    /**
     * dtu远程升级
     */
    private void dtuUpdate(ChannelHandlerContext ctx) {
        DtuNetworkConnection state = DtuChannelManager.getNetworkState(ctx.channel().id().asShortText());
        if (state != null && state.getChannel().isActive()) {
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
     * 根据报文获取设备地址
     *
     * @param bytes
     * @return
     */
    private String getDtuAddress(byte[] bytes) {
        int dtuLength = 8;
        byte[] bDtuID = new byte[dtuLength];
        System.arraycopy(bytes, 1, bDtuID, 0, dtuLength);
        return HexUtils.byteToString(bDtuID);
    }
}


