package main.com.handu.scada.protocol101.protocol.bean;

import main.com.handu.scada.protocol101.protocol.enums.DataType;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/05/30.
 * 定值和参数
 */
public class ConstantValue {
    private static final ConcurrentHashMap<Integer, String> constantValueMap = new ConcurrentHashMap<Integer, String>() {{
        put(0x8220, "故障指示灯自动复归投入");
        put(0x8221, "故障指示灯自动复归时间");
        put(0x8222, "故障遥信保持时间");
        put(0x8223, "首端FTU投入");
        put(0x8224, "X时间定值");
        put(0x8225, "Y时间定值");
        put(0x8226, "C时间定值");
        put(0x8227, "S时间定值");
        put(0x8228, "单相接地跳闸时间");
        put(0x8229, "选线跳闸重合时间定值");
        put(0x822A, "自适应相间短路故障处理投入");
        put(0x822B, "自适应单相接地故障处理投入");
        put(0x822C, "一次重合闸投退");
        put(0x822D, "一次重合时间");
        put(0x822E, "大电流闭锁重投退");
        put(0x822F, "大电流闭重定值");
    }};

    public static DataAttr getDataAttr(byte[] po) {
        byte l = po[0];
        byte h = po[1];
        byte length = po[3];
        byte[] data = new byte[length];
        System.arraycopy(po, 4, data, 0, data.length);
        int poAddress = HexUtils.byteToInt(h) * 256 + HexUtils.byteToInt(l);
        String name = constantValueMap.get(poAddress);
        if (name != null) {
            DataAttr dataAttr = new DataAttr();
            dataAttr.setPointPosition(poAddress);
            dataAttr.setName(name);
            switch (poAddress) {
                case 0x8220:
                    dataAttr.setValue("");
                    dataAttr.setUnit("");
                    break;
                case 0x8221:
                    break;
                case 0x8222:
                    break;
                case 0x8223:
                    break;
                case 0x8224:
                    break;
                case 0x8225:
                    break;
                case 0x8226:
                    break;
                case 0x8227:
                    break;
                case 0x8228:
                    break;
                case 0x8229:
                    break;
                case 0x822A:
                    break;
                case 0x822B:
                    break;
                case 0x822C:
                    break;
                case 0x822D:
                    break;
                case 0x822E:
                    break;
                case 0x822F:
                    break;
            }
            dataAttr.setDataType(DataType.CV);
            dataAttr.setRecordTime(DateUtils.getNowSqlDateTime());
            return dataAttr;
        }
        return null;
    }
}
