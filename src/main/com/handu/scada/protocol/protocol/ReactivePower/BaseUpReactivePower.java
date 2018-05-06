package main.com.handu.scada.protocol.protocol.ReactivePower;

import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 柳梦 on 2018/04/23.
 */
public class BaseUpReactivePower {
    protected byte S_Type;
    protected byte CmdType;
    protected byte Command;
    protected int dataLength;
    protected int length;
    protected Object value;
    protected DeviceCmdTypeEnum cmdType;

    private byte[] bytes;
    private byte b;

    protected List<DataAttr> dataAttrs;
    protected ProtocolLayerData protocolLayerData;

    protected List<DataAttr> parse(byte[] data) {

        String[] itemNames;
        String[] itemCnNames;
        String itemName;
        String itemCnName;
        DataAttr dataAttr;

        if (CmdType == (byte) 0x81) {
            dataAttrs = new ArrayList<>();
            if (Command == (byte) 0x0A) {
                cmdType = DeviceCmdTypeEnum.CapacitorControlParams;
                bytes = new byte[2];
                System.arraycopy(data, 0, bytes, 0, 2);
                //高低位
                int low = HexUtils.byteToInt(bytes[0]);
                int high = HexUtils.byteToInt(bytes[1]) * 256;
                value = low + high;
                dataAttr = new DataAttr() {{
                    setDtime(DateUtils.getNowSqlDateTime());
                    setInsertHistory(false);
                    setDateType(RemoteType.YX);
                    setValue(value);
                    setGroup("capacitorControlParams");
                    setName("capacitorInputInterval");
                    setCnname("电容投入间隔");
                    setUnit("s");
                }};
                dataAttrs.add(dataAttr);

                bytes = new byte[2];
                System.arraycopy(data, 2, bytes, 0, 2);
                //高低位
                low = HexUtils.byteToInt(bytes[0]);
                high = HexUtils.byteToInt(bytes[1]) * 256;
                value = low + high;
                dataAttr = new DataAttr() {{
                    setDtime(DateUtils.getNowSqlDateTime());
                    setInsertHistory(false);
                    setDateType(RemoteType.YX);
                    setValue(value);
                    setGroup("capacitorControlParams");
                    setName("capacitorExciseInterval");
                    setCnname("电容切除间隔");
                    setUnit("s");
                }};
                dataAttrs.add(dataAttr);

                b = data[4];
                value = b == 0x01 ? 1 : 0;
                dataAttr = new DataAttr() {{
                    setDtime(DateUtils.getNowSqlDateTime());
                    setInsertHistory(false);
                    setDateType(RemoteType.YX);
                    setValue(value);
                    setGroup("capacitorControlParams");
                    setName("capacitorSwitchState");
                    setCnname("模拟投切模式开关");
                    setUnit("");
                }};
                dataAttrs.add(dataAttr);
            } else if (Command == (byte) 0x0B) {
                cmdType = DeviceCmdTypeEnum.SecondSideSamplingInfo;
                //共补
                if (S_Type == 0x01) {
                    bytes = new byte[6];
                    itemNames = new String[]{"Ua", "Ub", "Uc"};
                    itemCnNames = new String[]{"A相电压", "B相电压", "C相电压"};
                    int[][] BcdArr = new int[3][2];
                    int k = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 2; j++) {
                            BcdArr[i][j] = HexUtils.bcdByteToInt(bytes[k]);
                            k++;
                        }
                    }
                    for (int n = 0; n < 3; n++) {
                        itemName = itemNames[n];
                        itemCnName = itemCnNames[n];
                        value = BcdArr[n][1] * 10 + BcdArr[n][0] / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit("V");
                        dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                }
                //分补
                else if (S_Type == 0x02) {

                }
            }
        }
        return dataAttrs;
    }
}
