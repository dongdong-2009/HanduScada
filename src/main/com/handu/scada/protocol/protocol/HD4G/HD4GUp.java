package main.com.handu.scada.protocol.protocol.HD4G;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.Crc16Utils;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 柳梦 on 2018/04/09.
 */

@DtuUpParse
public class HD4GUp implements IProtocol {

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            byte[] bytes = mediaData.CommandData;
            if (valid(bytes)) {
                mediaData.cmdTypeEnum = DeviceCmdTypeEnum.ConcentratorHeartbeatTime;
                byte[] data = new byte[6];
                System.arraycopy(bytes, 7, data, 0, data.length);
                int year = 2000 + HexUtils.bcdByteToInt(data[5]);
                int month = HexUtils.bcdByteToInt((byte) (data[4] & 0x1f));
                int day = HexUtils.bcdByteToInt(data[3]);
                int h = HexUtils.bcdByteToInt(data[2]);
                int m = HexUtils.bcdByteToInt(data[1]);
                int s = HexUtils.bcdByteToInt(data[0]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month - 1, day, h, m, s);
                return new ProtocolLayerData() {{
                    DTUString = mediaData.DTUString;
                    CmdType = mediaData.cmdTypeEnum;
                    deviceTypeEnum = DeviceTypeEnum.DTU;
                    attrList = new ArrayList<DataAttr>() {{
                        add(new DataAttr() {{
                            setInsertHistory(false);
                            setValue(DateUtils.date2SqlDate(calendar.getTime()));
                            setGroup(mediaData.cmdTypeEnum.name());
                            setDateType(RemoteType.YC);
                            setDtime(DateUtils.getNowSqlDateTime());
                            setUnit("年月日时分秒");
                            setName(mediaData.cmdTypeEnum.name());
                            setCnname(mediaData.cmdTypeEnum.getName());
                        }});
                    }};
                }};
            }
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return null;
    }

    @Override
    public void getAddress(byte[] buff) {

    }

    @Override
    public boolean valid(byte[] bytes) {
        try {
            if (bytes == null || bytes[0] != 0x6B) return false;
            if (bytes.length != 16 || bytes[4] != 0x6B) return false;
            int Crc = Crc16Utils.calcCrc16(bytes, 4, 9);
            return (byte) (Crc & 0xFF) == bytes[bytes.length - 2] && (byte) (Crc >> 8) == bytes[bytes.length - 3];
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return false;
    }

    @Override
    public MediaData sendCommand(ProtocolLayerData protocolLayerData) {
        return null;
    }
}
