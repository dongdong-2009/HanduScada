package main.com.handu.scada.protocol.protocol.GDW3761.impl;

import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.protocol.GDW3761.AFN0CF25Data;

/**
 * Created by 柳梦 on 2018/04/26.
 */

@DtuUpParse
public class UpGDW3761 implements IProtocol {

    @Override
    public boolean valid(byte[] bytes) {
        try {
            if (bytes == null) return false;
            if (bytes.length < 10) return false;
            //台表
            if (bytes[0] == (byte) 0xFF && bytes[3] == 0x25 && bytes[6] == 0x68
                    && bytes[bytes.length - 1] == (byte) 0xFE && bytes[bytes.length - 4] == 0x16) {
                byte[] buff = new byte[bytes.length - 9];
                //取出数据域
                System.arraycopy(bytes, 6, buff, 0, buff.length);
                int h = buff[2] * 256;
                int l = buff[1];
                byte le = (byte) ((h + l) >> 2);
                byte blen = (byte) (le + (byte) 0x08);
                if (blen != buff.length) return false;//长度校验
                byte cs = 0;
                int len = buff.length - 8;
                //针头针尾校验，
                for (int i = 6; i < len; i++) {
                    cs += buff[i];
                }
                //校验和
                return cs == buff[buff.length - 2];
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return false;
    }

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            byte b[] = mediaData.CommandData;
            if (valid(b)) {
                //取出376.1报文
                byte[] buff = new byte[b.length - 9];
                System.arraycopy(b, 6, buff, 0, buff.length);
                //取出376.1数据域
                byte[] bytes = new byte[buff.length - 18];
                System.arraycopy(buff, 18, bytes, 0, bytes.length);
                AFN0CF25Data data = new AFN0CF25Data(bytes);
                return new ProtocolLayerData() {{
                    attrList = data.dataAttrs;
                    deviceTypeEnum = DeviceTypeEnum.HM;
                    CommandData = mediaData.CommandData;
                    DTUString = mediaData.DTUString;
                    TabName = DeviceTableEnum.Device_HM.getTableName();
                    CmdType = DeviceCmdTypeEnum.HM_AFN0C25;
                }};
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
