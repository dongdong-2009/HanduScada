package main.com.handu.scada.netty.server.switch101;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import main.com.handu.scada.config.Config;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.switch101.protocol.enums.COT;
import main.com.handu.scada.switch101.protocol.enums.DataType;
import main.com.handu.scada.switch101.protocol.enums.Ti;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.LogUtils;

import static main.com.handu.scada.switch101.protocol.enums.Ti.C_IC_NA_1;
import static main.com.handu.scada.switch101.protocol.enums.Ti.M_EI_NA_1;

/**
 * Created by 柳梦 on 2018/03/13.
 * 开关报文处理
 */
public class SwitchCommandHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && ctx.channel().isActive()) {
            String connectionId = ctx.channel().id().asShortText();
            SwitchNetworkConnection connection = new SwitchNetworkConnection(ctx);
            connection.getCallback().online(connectionId, null, MsgType.ONLINE);
            SwitchCtxManager.addConnection(connectionId, connection);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel() != null && !ctx.channel().isActive()) {
            String connectionId = ctx.channel().id().asShortText();
            SwitchNetworkConnection connection = SwitchCtxManager.getSwitchNetworkConnection(connectionId);
            if (connection != null) {
                connection.getCallback().offline(connectionId, connection.getDeviceAddress());
            }
            SwitchCtxManager.removeClient(connectionId);
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (Config.isDebug) ExceptionHandler.handle(cause);
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        try {
            // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
            byteBuf.readBytes(bytes);
            if (bytes.length > 0) {
                //登录
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
            if (Config.isDebug) {
                String resultStr = HexUtils.byteArrayToHexStr(bytes);
                LogUtils.info("switch channel read " + "data=" + resultStr);
            }
            // 释放资源，这行很关键
            byteBuf.release();
        }
    }

    /**
     * 设备登录
     */
    private void login(ChannelHandlerContext ctx, byte[] bytes) {
        if (bytes.length == 9) {
            byte[] b = new byte[2];
            System.arraycopy(bytes, 6, b, 0, b.length);
            SwitchNetworkConnection connection = SwitchCtxManager.getSwitchNetworkConnection(ctx.channel().id().asShortText());
            if (connection != null) {
                int low = HexUtils.byteToInt(b[0]);
                int high = HexUtils.byteToInt(b[1]) * 256;
                String deviceAddress = String.valueOf(high + low);
                connection.setAddressLow(b[0]);
                connection.setAddressHigh(b[1]);
                connection.setDeviceAddress(deviceAddress);
                connection.getCallback().online(ctx.channel().id().asShortText(), deviceAddress, MsgType.LOGIN);
                //开始主站链路请求
                b = new byte[6];
                b[0] = 0x10;
                b[1] = 0x49;
                b[2] = connection.getAddressLow();
                b[3] = connection.getAddressHigh();
                byte checksum = (byte) (b[1] + b[2] + b[3]);
                b[4] = checksum;
                b[5] = 0x16;
                ByteBuf byteBuf = ctx.alloc().buffer(b.length);
                byteBuf.writeBytes(b);
                sendCommand(ctx, byteBuf);
            }
        }
    }

    /**
     * 设备确认
     */
    private void clientNotarize(ChannelHandlerContext ctx, byte[] bytes) {
        SwitchNetworkConnection connection = SwitchCtxManager.getSwitchNetworkConnection(ctx.channel().id().asShortText());
        if (connection != null) {
            if (bytes.length == 6) {
                byte[] b = new byte[4];
                System.arraycopy(bytes, 1, b, 0, b.length);
                byte checksum = (byte) (b[0] + b[1] + b[2]);
                if (b[3] == checksum) {
                    //链路响应
                    if (b[0] == (byte) 0x8b) {
                        //链路复位
                        b = new byte[]{0x10, 0x40, connection.getAddressLow(), connection.getAddressHigh(), 0x00, 0x16};
                        checksum = (byte) (0x40 + connection.getAddressHigh() + connection.getAddressLow());
                        b[b.length - 2] = checksum;
                        ByteBuf byteBuf = ctx.alloc().buffer(b.length);
                        byteBuf.writeBytes(b);
                        sendCommand(ctx, byteBuf);
                    }
                    //设备确认帧
                    else if (b[0] == (byte) 0x80) {
                        connection.setHasConfirmed(true);
                    }
                    //终端远方链路状态
                    else if (b[0] == (byte) 0xc9) {
                        if (connection.isHasConfirmed()) {
                            //响应链路状态
                            b = new byte[]{0x10, 0x0b, connection.getAddressLow(), connection.getAddressHigh(), 0x00, 0x16};
                            checksum = (byte) (0x0b + connection.getAddressHigh() + connection.getAddressLow());
                            b[b.length - 2] = checksum;
                            ByteBuf byteBuf = ctx.alloc().buffer(b.length);
                            byteBuf.writeBytes(b);
                            sendCommand(ctx, byteBuf);
                        }
                    }
                    //终端远方复位链路
                    else if (b[0] == (byte) 0xc0) {
                        //复位确认
                        sendConfirm(ctx, connection);
                    }
                }
            }
        }
    }

    /**
     * 收到0x68开始的数据
     */
    private void serialPort(ChannelHandlerContext ctx, byte[] bytes) {
        SwitchNetworkConnection connection = SwitchCtxManager.getSwitchNetworkConnection(ctx.channel().id().asShortText());
        if (connection != null) {
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
                        sendConfirm(ctx, connection);
                    }
                    //总召唤激活终止
                    else if (reason == COT.COT0a.getCot()) {
                        sendConfirm(ctx, connection);
                    }
                }
                //初始化结束
                else if (tiType == M_EI_NA_1) {
                    //总召
                    if (reason == COT.COT04.getCot()) {
                        connection.getCallback().online(ctx.channel().id().asShortText(), connection.getDeviceAddress(), MsgType.CONNECTION);
                        sendAllCall(ctx, connection);
                    }
                }
                //遥信报文
                //68 12 12 68 73 ae 02 01 87 14 00 ae 02 01 00 01 00 00 01 00 00 00 72 16
                else if (tiType.getDataType() == DataType.YX) {
                    sendConfirm(ctx, connection);
                    connection.parseUp(bytes);
                }
                //遥测报文
                //68 1d 1d 68 53 ae 02 09 86 14 00 ae 02 01 40 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 97 16
                else if (tiType.getDataType() == DataType.YC) {
                    sendConfirm(ctx, connection);
                    connection.parseUp(bytes);
                }
            }
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

    /**
     * 主站回复 10 00 xx xx xx 16
     *
     * @param ctx
     * @param connection
     */
    private void sendConfirm(ChannelHandlerContext ctx, SwitchNetworkConnection connection) {
        byte[] b = new byte[]{0x10, 0x00, connection.getAddressLow(), connection.getAddressHigh(), 0x00, 0x16};
        byte checksum = (byte) (connection.getAddressHigh() + connection.getAddressLow());
        b[b.length - 2] = checksum;
        ByteBuf byteBuf = ctx.alloc().buffer(b.length);
        byteBuf.writeBytes(b);
        sendCommand(ctx, byteBuf);
    }

    /**
     * @param ctx
     * @param connection
     */
    private void sendAllCall(ChannelHandlerContext ctx, SwitchNetworkConnection connection) {
        connection.setConnectionSuccess(true);
        //第一次发送总召命令 控制域C变为0x53
        connection.setIs53(true);
        //开始总召命令激活帧
        byte reason = COT.COT06.getCot();
        byte ti = Ti.C_IC_NA_1.getTiType();
        byte[] b = new byte[]{0x68, 0x0C, 0x0C, 0x68, (byte) (connection.is53() ? 0x53 : 0x73), connection.getAddressLow(), connection.getAddressHigh(), ti, 0x01, reason, 0x00, connection.getAddressLow(), connection.getAddressHigh(), 0x00, 0x00, 0x14, 0x00, 0x16};
        byte checksum = 0;
        for (int i = 4; i < b.length - 2; i++) {
            checksum += b[i];
        }
        b[b.length - 2] = checksum;
        ByteBuf byteBuf = ctx.alloc().buffer(b.length);
        byteBuf.writeBytes(b);
        sendCommand(ctx, byteBuf);
    }
}
