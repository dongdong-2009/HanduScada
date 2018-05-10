package main.com.handu.scada.protocol.protocol.FallTypeSwitch;

import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.Crc16Utils;
import main.com.handu.scada.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/03/14.
 */

@DtuUpParse
public class FallTypeSwitchUp implements IProtocol {

    private FallTypeSwitchUp() {
    }

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            if (mediaData == null) return null;
            byte bytes[] = mediaData.CommandData;
            if (!valid(bytes)) return null;
            String deviceAddress = Integer.toHexString(bytes[0]);
            int a = 0;
            int b = 0;
            int c = 0;
            byte value = bytes[3];
            switch (value) {
                case 0x00:
                    a = 0;
                    b = 0;
                    c = 0;
                    break;
                case 0x01:
                    a = 1;
                    b = 0;
                    c = 0;
                    break;
                case 0x02:
                    a = 0;
                    b = 1;
                    c = 0;
                    break;
                case 0x03:
                    a = 1;
                    b = 1;
                    c = 0;
                    break;
                case 0x04:
                    a = 0;
                    b = 0;
                    c = 1;
                    break;
                case 0x05:
                    a = 1;
                    b = 0;
                    c = 1;
                    break;
                case 0x06:
                    a = 0;
                    b = 1;
                    c = 1;
                    break;
                case 0x07:
                    a = 1;
                    b = 1;
                    c = 1;
                    break;
            }

            List<DataAttr> attrs = new ArrayList<>();

            DataAttr dataAttr = new DataAttr();
            dataAttr.setDateType(RemoteType.YX);
            dataAttr.setCnname("跌落开关A相");
            dataAttr.setName("A");
            dataAttr.setDtime(DateUtils.getNowSqlDateTime());
            dataAttr.setUnit("");
            dataAttr.setGroup("FallTypeSwitch");
            dataAttr.setValue(a);
            attrs.add(dataAttr);

            dataAttr = new DataAttr();
            dataAttr.setDateType(RemoteType.YX);
            dataAttr.setCnname("跌落开关B相");
            dataAttr.setName("B");
            dataAttr.setDtime(DateUtils.getNowSqlDateTime());
            dataAttr.setUnit("");
            dataAttr.setGroup("FallTypeSwitch");
            dataAttr.setValue(b);
            attrs.add(dataAttr);

            dataAttr = new DataAttr();
            dataAttr.setDateType(RemoteType.YX);
            dataAttr.setCnname("跌落开关C相");
            dataAttr.setName("C");
            dataAttr.setDtime(DateUtils.getNowSqlDateTime());
            dataAttr.setUnit("");
            dataAttr.setGroup("FallTypeSwitch");
            dataAttr.setValue(c);
            attrs.add(dataAttr);

            return new ProtocolLayerData() {{
                CommandName = "跌落开关装置";
                deviceTypeEnum = DeviceTypeEnum.FALL_TYPE_SWITCH;
                PostalAddress = deviceAddress;
                DTUString = mediaData.DTUString;
                attrList = attrs;
                TabName = DeviceTableEnum.Device_Falling_Type_Switch.getTableName().toLowerCase();
            }};
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }

    @Override
    public boolean valid(byte[] bytes) {
        try {
            if (bytes == null) return false;
            //跌落开关的长度为6
            if (bytes.length != 6) return false;
            if (bytes[0] != 0x01) return false;
            //跌落装置功能码是0x02
            if (bytes[1] != 0x02) return false;
            int Crc = Crc16Utils.calcCrc16(bytes, 0, bytes.length - 2);
            return (byte) (Crc & 0xFF) == bytes[4] && (byte) (Crc >> 8) == bytes[5];
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return false;
    }
}
