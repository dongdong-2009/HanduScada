package main.com.handu.scada.protocol.protocol.Thermometry.impl;

import main.com.handu.scada.enums.TableEnum;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuUpParse;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.protocol.protocol.Thermometry.BaseUpTemperatureProtocol;
import main.com.handu.scada.protocol.protocol.Thermometry.DirectionType;
import main.com.handu.scada.utils.Crc16Utils;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 柳梦 on 2018/01/25.
 */

@DtuUpParse
public class UpWiredTemperatureProtocol extends BaseUpTemperatureProtocol {

    @Override
    public ProtocolLayerData parse(MediaData mediaData) {
        try {
            if (mediaData == null) return null;
            byte upstream[] = mediaData.CommandData;
            if (!valid(upstream)) return null;
            dtuAddress = mediaData.DTUString;
            probedic = new HashMap<>();
            int temp = Integer.parseInt(HexUtils.byteArrayToHexStr(upstream, 5, 2));
            if (temp > 0x05 && temp < 0xF5) probedic.put((byte) 1, (double) (temp - 0x23));
            temp = Integer.parseInt(HexUtils.byteArrayToHexStr(upstream, 7, 2));
            if (temp > 0x05 && temp < 0xF5) probedic.put((byte) 2, (double) (temp - 0x23));
            temp = Integer.parseInt(HexUtils.byteArrayToHexStr(upstream, 9, 2));
            if (temp > 0x05 && temp < 0xF5) probedic.put((byte) 3, (double) (temp - 0x23));
            IsNormal = true;
            address = upstream[0];
            Direct = DirectionType.UP;
            values = new ArrayList<>();
            String addressStr = Integer.toHexString(address);
            int index = 0;
            for (Map.Entry<Byte, Double> entry : probedic.entrySet()) {
                index++;
                DataAttr dataAttr = new DataAttr();
                dataAttr.setDateType(RemoteType.YC);
                dataAttr.setValue(entry.getValue());
                dataAttr.setName(addressStr + index);
                dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                dataAttr.setCnname(addressStr + index);
                dataAttr.setGroup("areaTemperature");
                dataAttr.setUnit("°C");
                values.add(dataAttr);
            }
            IsSuccess = true;
            protocolLayerData = new ProtocolLayerData() {{
                CommandData = mediaData.CommandData;
                CommandName = "优科测温遥测";
                PostalAddress = addressStr;
                DTUString = mediaData.DTUString;
                attrList = values;
                deviceTypeEnum = DeviceTypeEnum.THERMOMETRY;
                TabName = TableEnum.Device_Temperature.getTableName();
            }};
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return protocolLayerData;
    }

    @Override
    public void getAddress(byte[] buff) {
    }

    @Override
    public boolean valid(byte[] bytes) {
        try {
            if (bytes == null) return false;
            if (bytes.length < 14) return false;
            //测温功能码是0x03
            if (bytes[1] != 0x03) return false;
            int Crc = Crc16Utils.calcCrc16(bytes, 0, bytes.length - 2);
            return (byte) (Crc & 0xFF) == bytes[13] && (byte) (Crc >> 8) == bytes[14];
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
