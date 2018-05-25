package main.com.handu.scada.protocol.protocol.FallTypeSwitch;

import main.com.handu.scada.enums.DeviceTableEnum;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.IProtocol;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/05/24.
 * 汉度自定义协议跌落装置
 */
@DtuUpParse
public class HDFallTypeSwitch implements IProtocol {

    @Override
    public boolean valid(byte[] bytes) {
        try {
            if (bytes == null) return false;
            if (bytes.length != 24) return false;
            if (bytes[0] == 0x68 && bytes[bytes.length - 1] == 0x16) {
                byte checkSum = 0;
                for (int i = 4; i < bytes.length - 2; i++) {
                    checkSum += bytes[i];
                }
                return bytes[bytes.length - 2] == checkSum;
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return false;
    }

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            byte[] bytes = mediaData.CommandData;
            if (valid(mediaData.CommandData)) {
                byte[] addresses = new byte[8];
                System.arraycopy(bytes, 5, addresses, 0, 8);
                String address = HexUtils.byteToASCIIString(addresses);
                //值
                int value = HexUtils.byteToInt(bytes[bytes.length - 3]);
                //测温或者跌落
                int type = HexUtils.byteAsciiToInt(addresses[0]);
                //相
                int phase = HexUtils.byteAsciiToInt(addresses[7]);
                DeviceTypeEnum deviceTypeEnum_;
                String tableName;
                DataAttr dataAttr;
                //1表示测温
                if (type == 1) {
                    deviceTypeEnum_ = DeviceTypeEnum.WIRELESS_TEMPERATURE;
                    tableName = DeviceTableEnum.Device_Temperature.getTableName().toLowerCase();
                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setCnname("测温探头" + phase);
                    dataAttr.setName(String.valueOf(phase));
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setUnit("°C");
                    dataAttr.setGroup("AreaTemperature");
                    dataAttr.setValue(value);
                }
                //2表示跌落装置
                else {
                    deviceTypeEnum_ = DeviceTypeEnum.FALL_TYPE_SWITCH;
                    tableName = DeviceTableEnum.Device_Falling_Type_Switch.getTableName().toLowerCase();
                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YX);
                    dataAttr.setCnname("跌落开关" + (phase == 1 ? "A" : phase == 2 ? "B" : "C") + "相");
                    dataAttr.setName(phase == 1 ? "A" : phase == 2 ? "B" : "C");
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setUnit("");
                    dataAttr.setGroup("FallTypeSwitch");
                    dataAttr.setValue(value);
                }
                List<DataAttr> attrs = new ArrayList<>();
                attrs.add(dataAttr);
                return new ProtocolLayerData() {{
                    PostalAddress = address;
                    this.deviceTypeEnum = deviceTypeEnum_;
                    DTUString = mediaData.DTUString;
                    attrList = attrs;
                    TabName = tableName;
                }};
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
