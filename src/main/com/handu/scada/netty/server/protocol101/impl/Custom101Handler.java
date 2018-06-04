package main.com.handu.scada.netty.server.protocol101.impl;

import io.netty.buffer.ByteBuf;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.netty.server.protocol101.BaseHandler;
import main.com.handu.scada.netty.server.protocol101.Protocol101CommandHandler;
import main.com.handu.scada.protocol101.protocol.enums.COT;
import main.com.handu.scada.protocol101.protocol.enums.Ti;

import java.util.Arrays;

import static main.com.handu.scada.protocol101.protocol.enums.Ti.C_IC_NA_1;
import static main.com.handu.scada.protocol101.protocol.enums.Ti.M_EI_NA_1;

/**
 * Created by 柳梦 on 2018/05/29.
 * 标准101协议Handler
 */
public class Custom101Handler extends BaseHandler {

    public Custom101Handler(Protocol101CommandHandler handler) {
        super(handler);
    }

    @Override
    public void channelRead(byte[] bytes) {
        //确认
        if (bytes[0] == (byte) 0x10) {
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

    @Override
    public void startLinkQuest() {
        //开始主站链路请求
        byte[] b = new byte[6];
        b[0] = 0x10;
        b[1] = 0x49;
        b[2] = handler.getAddressLow();
        b[3] = handler.getAddressHigh();
        byte checksum = (byte) (b[1] + b[2] + b[3]);
        b[4] = checksum;
        b[5] = 0x16;
        handler.sendCommand(b);
    }

    @Override
    public void sendConfirm() {
        byte[] bytes = new byte[]{0x10, 0x00, handler.getAddressLow(), handler.getAddressHigh(), 0x00, 0x16};
        byte checksum = (byte) (handler.getAddressLow() + handler.getAddressHigh());
        bytes[bytes.length - 2] = checksum;
        handler.sendCommand(bytes);
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
                    b = new byte[]{0x10, 0x40, handler.getAddressLow(), handler.getAddressHigh(), 0x00, 0x16};
                    checksum = (byte) (0x40 + handler.getAddressLow() + handler.getAddressHigh());
                    b[b.length - 2] = checksum;
                    ByteBuf byteBuf = context.alloc().buffer(b.length);
                    byteBuf.writeBytes(b);
                    handler.sendCommand(b);
                }
                //设备确认帧
                else if (b[0] == (byte) 0x80) {
                    handler.setHasConfirmed(true);
                }
                //终端远方链路状态
                else if (b[0] == (byte) 0xc9) {
                    if (handler.isHasConfirmed()) {
                        //响应链路状态
                        b = new byte[]{0x10, 0x0b, handler.getAddressLow(), handler.getAddressHigh(), 0x00, 0x16};
                        checksum = (byte) (0x0b + handler.getAddressLow() + handler.getAddressHigh());
                        b[b.length - 2] = checksum;
                        ByteBuf byteBuf = context.alloc().buffer(b.length);
                        byteBuf.writeBytes(b);
                        handler.sendCommand(b);
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
                        handler.getCallback().online(context.channel().id().asShortText(), handler.getIp(), handler.getPort(), handler.getDeviceAddress(), MsgType.CONNECTION_SUCCESS);
                        sendConfirm();
                        handler.sendFirstAllCall();
                    }
                } else {
                    sendConfirm();
                    handler.upAnalysis(bytes);
                }
            }
        }
    }
}
