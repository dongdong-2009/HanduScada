package main.com.handu.scada.protocol.protocol.FallTypeSwitch;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuDownParse;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.utils.Crc16Utils;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/03/14.
 */

@DtuDownParse
public class FallTypeSwitchDown implements IProtocol {

    private byte[] allCall = new byte[]{
            0x01, 0x02, 0x00, 0x00, 0x00, 0x04, 0x79, (byte) 0xC9
    };

    @Override
    public MediaData sendCommand(ProtocolLayerData protocolLayerData) {
        if (protocolLayerData.deviceTypeEnum != DeviceTypeEnum.CY_FALL_TYPE_SWITCH) return null;
        return new MediaData() {
            {
                MsgName = "跌落开关总召";
                DTUString = protocolLayerData.DTUString;
                PostalAddress = protocolLayerData.PostalAddress;
                HasDTUHead = protocolLayerData.HasDTUHead;
                CommandData = getBytes(protocolLayerData);
            }
        };
    }

    /**
     * 计算地址，校验位，获取下发报文
     *
     * @param protocolLayerData
     * @return byte[]
     */
    private byte[] getBytes(ProtocolLayerData protocolLayerData) {
        try {
            if (protocolLayerData.CmdType == DeviceCmdTypeEnum.ALL_CALL) {
                try {
                    int address = Integer.parseInt(protocolLayerData.PostalAddress);
                    allCall[0] = HexUtils.intToByte(address);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                int crc = Crc16Utils.calcCrc16(allCall, 0, allCall.length - 2);
                byte b1 = (byte) (crc & 0xFF);
                byte b2 = (byte) (crc >> 8);
                allCall[allCall.length - 2] = b1;
                allCall[allCall.length - 1] = b2;
                return allCall;
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
