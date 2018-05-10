package main.com.handu.scada.protocol.protocol.ReactivePower.impl;

import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.protocol.ReactivePower.BaseUpReactivePower;

/**
 * Created by 柳梦 on 2018/04/16.
 */

@DtuUpParse
public class ReactivePowerUp extends BaseUpReactivePower implements IProtocol {

    @Override
    public boolean valid(byte[] bytes) {
        try {
            if (bytes == null) return false;
            if (bytes.length < 16) return false;
            //漏保以0x68开头，中间6位地址，加上0x68，最后以0x16结束
            if (bytes[0] == (byte) 0x68 && bytes[9] == 0x68 && bytes[bytes.length - 1] == 0x16) {
                int blen = bytes[11] + 0x10;
                if (blen != bytes.length) return false;//长度校验
                byte cs = 0;
                int len = bytes.length - 2;
                //针头针尾校验，
                for (int i = 0; i < len; i++)//有14+Length个字节需要进行校验
                {
                    cs += bytes[i];//透传的帧从第1个字节开始
                }
                //校验和
                return cs == bytes[len];
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return false;
    }

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        byte[] bytes = mediaData.CommandData;
        if (valid(bytes)) {
            try {
                length = bytes.length;
                dataLength = bytes.length - 16;
                S_Type = bytes[10];
                CmdType = bytes[12];
                Command = bytes[13];
                byte[] data = new byte[dataLength];
                System.arraycopy(bytes, 14, data, 0, dataLength);
                dataAttrs = parse(data);
            } catch (Exception e) {
                ExceptionHandler.handle(e);
            }
            protocolLayerData = new ProtocolLayerData() {{
                deviceTypeEnum = DeviceTypeEnum.REACTIVE_POWER;
                CommandData = mediaData.CommandData;
                DTUString = mediaData.DTUString;
                attrList = dataAttrs;
                TabName = DeviceTableEnum.Device_Reactive_Power.getTableName();
                CmdType = cmdType;
            }};
        }
        return protocolLayerData;
    }
}
