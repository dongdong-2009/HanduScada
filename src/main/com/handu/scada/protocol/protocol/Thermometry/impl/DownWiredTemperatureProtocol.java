package main.com.handu.scada.protocol.protocol.Thermometry.impl;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuDownParse;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.protocol.Thermometry.BaseDownStreamTemperature;
import main.com.handu.scada.protocol.protocol.Thermometry.TemperatureCmdType;
import main.com.handu.scada.utils.Crc16Utils;
import main.com.handu.scada.utils.HexUtils;

import static main.com.handu.scada.protocol.protocol.Thermometry.TemperatureCmdType.All_CALL;

/**
 * Created by 柳梦 on 2018/01/25.
 */

@DtuDownParse
public class DownWiredTemperatureProtocol extends BaseDownStreamTemperature {

    //固定地址01，不需要激活
    private static byte[] AllCallBytes = new byte[]{
            0x01, 0x03, 0x00, 0x00, 0x00, 0x05, (byte) 0x85, (byte) 0xC9
    };

    private DownWiredTemperatureProtocol() {
        init(All_CALL);
    }

    private void init(TemperatureCmdType cmdType) {
        switch (cmdType) {
            case All_CALL:
                msgName = "有线测温总召";
                arrLength = AllCallBytes.length;
                break;
            default:
                break;
        }
    }

    @Override
    protected void getBytes() {
        try {
            cmdByte = AllCallBytes;
            cmdByte[0] = address;
            //计算校验位
            int crc = Crc16Utils.calcCrc16(cmdByte, 0, cmdByte.length - 2);
            byte[] CS = new byte[2];
            CS[1] = (byte) (crc & 0xFF);
            CS[0] = (byte) (crc >> 8);
            cmdByte[arrLength - 1] = CS[0];
            cmdByte[arrLength - 2] = CS[1];
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    @Override
    public MediaData sendCommand(ProtocolLayerData protocolLayerData) {
        if (protocolLayerData.deviceTypeEnum != DeviceTypeEnum.YK_WIRED_TEMPERATURE) return null;
        try {
            if (protocolLayerData.CmdType == DeviceCmdTypeEnum.ALL_CALL) {
                MediaData mediaData = new MediaData() {
                    {
                        DTUString = protocolLayerData.DTUString;
                        PostalAddress = protocolLayerData.PostalAddress;
                        HasDTUHead = protocolLayerData.HasDTUHead;
                        CommandData = protocolLayerData.CommandData;
                    }
                };
                int a = Integer.parseInt(mediaData.PostalAddress);
                address = HexUtils.intToByte(a);
                dtuAddress = mediaData.DTUString;
                getBytes();
                mediaData.CommandData = cmdByte;
                mediaData.MsgName = msgName;
                return mediaData;
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
