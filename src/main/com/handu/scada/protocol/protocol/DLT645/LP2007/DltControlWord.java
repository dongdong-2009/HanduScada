package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.DLT645.LP1997.HZQL_Version;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.*;


/**
 * 控制字
 */
public class DltControlWord {

    private static Map<Integer, HZQL_Version> hzql_versionMap = new HashMap<Integer, HZQL_Version>() {{
        put(1, new HZQL_Version(1, "QLLT-250A", new int[]{60, 250, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{200, 300}));
        put(2, new HZQL_Version(2, "QLLT-250A", new int[]{60, 250, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(3, new HZQL_Version(3, "QLLT-100A", new int[]{20, 100, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{200, 300}));
        put(4, new HZQL_Version(4, "QLLT-100A", new int[]{20, 100, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(5, new HZQL_Version(5, "QLLT-400A", new int[]{200, 400, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{200, 500}));
        put(6, new HZQL_Version(6, "QLLT-400A", new int[]{200, 400, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(7, new HZQL_Version(7, "QLLT-600A", new int[]{200, 600, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{200, 300}));
        put(8, new HZQL_Version(8, "QLLT-600A", new int[]{200, 600, 10}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(9, new HZQL_Version(9, "QLL1-Z(250A)", new int[]{250}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(10, new HZQL_Version(10, "QLL1-Z(100A)", new int[]{100}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(11, new HZQL_Version(11, "QLL1-Z(400A)", new int[]{400}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(12, new HZQL_Version(12, "QLKZ-630A", new int[]{200, 630, 20}, new int[]{100, 300, 500, 800, 0}, new int[]{300, 500}));
        put(35, new HZQL_Version(35, "二级保-100A", new int[]{20, 100, 10}, new int[]{75, 150}, new int[]{300}));
        put(101, new HZQL_Version(101, "JD6-6", new int[]{0}, new int[]{100, 300, 500, 800, 0}, new int[]{200, 500}));
        put(200, new HZQL_Version(200, "单相200A漏电保护器", new int[]{20, 200, 10}, new int[]{50, 100, 150, 250, 300, 0}, new int[]{200}));
    }};

    public boolean flagAllAlarm;//数据总告警开关

    public boolean flagLightAlarm;//报警灯光

    public boolean flagAudioAlarm;//报警声音

    public boolean flagTimelyTrial;//定时试跳

    public boolean flagLevelReturn;//档位返回

    public boolean flagReClosing;//重合闸

    public boolean flagUnderVoltageAlarm;//欠压告警

    public boolean flagUnderVoltageControl;//欠压控制

    public boolean flagOverVoltageAlarm;//过压告警

    public boolean flagOverVoltageControl;//过压控制

    public boolean flagMissPhaseAlarm;//缺相告警

    public boolean flagMissPhaseControl;//缺相控制

    public boolean flagOverCurrentAlarm;//过流告警

    public boolean flagOverCurrentControl;//过流控制

    public boolean flagTrialSource;//试跳源

    public boolean flagMissEarthLineAlarm;//缺零告警

    public boolean flagMissEarthLineControl;//缺零控制

    public int ResidualThresholdLevel;//额定剩余电流动作值，剩余电流当前档位值

    public int DelayTimeLevel;//额定极限不驱动时间，漏电分断延迟时间值

    public int ResidualAlarmTimeLevel;//剩余电流报警时间

    public Date recordTime;

    private byte[] word;

    private DeviceGroup deviceGroup;

    private List<DataAttr> dataAttrs;

    public DltControlWord(byte[] word, DeviceGroup deviceGroup) {
        this.word = word;
        this.deviceGroup = deviceGroup;
    }

    public List<DataAttr> parseControlWord() {
        if (word == null) return null;
        if (deviceGroup == null) return null;
        //设备分组，主要区分新老漏保
        if (deviceGroup == DeviceGroup.LP2007) {
            if (word.length < 4) return null;
            byte controlWord = word[0];
            flagAllAlarm = (controlWord & 0x40) != 0;
            flagLightAlarm = (controlWord & 0x20) != 0;
            flagAudioAlarm = (controlWord & 0x10) != 0;
            flagTimelyTrial = (controlWord & 0x08) != 0;
            flagLevelReturn = (controlWord & 0x04) != 0;
            flagReClosing = (controlWord & 0x02) != 0;

            controlWord = word[1];
            flagUnderVoltageAlarm = (controlWord & 0x80) != 0;
            flagUnderVoltageControl = (controlWord & 0x40) != 0;
            flagOverVoltageAlarm = (controlWord & 0x20) != 0;
            flagOverVoltageControl = (controlWord & 0x10) != 0;
            flagMissPhaseAlarm = (controlWord & 0x08) != 0;
            flagMissPhaseControl = (controlWord & 0x04) != 0;
            flagOverCurrentAlarm = (controlWord & 0x02) != 0;
            flagOverCurrentControl = (controlWord & 0x01) != 0;

            controlWord = word[2];
            flagOverCurrentAlarm = (controlWord & 0x80) != 0;
            flagMissEarthLineAlarm = (controlWord & 0x02) != 0;
            flagMissEarthLineControl = (controlWord & 0x01) != 0;

            controlWord = word[3];
            ResidualThresholdLevel = (controlWord & 0xf0) >> 4;
            DelayTimeLevel = (controlWord & 0x0c) >> 2;
            ResidualAlarmTimeLevel = controlWord & 0x03;

        } else if (deviceGroup == DeviceGroup.LP1997) {
            if (word.length < 10) return null;
            //保护器型号ID
            byte controlWord = word[9];
            int id = HexUtils.byteToInt(controlWord);
            HZQL_Version version = hzql_versionMap.get(id);
            if (version != null) {
                byte n1 = word[0];
                byte n2 = word[1];
                int n = HexUtils.byteToInt(n1) + HexUtils.byteToInt(n2) * 256;
                dataAttrs = new ArrayList<DataAttr>() {{
                    add(new DataAttr() {{
                        setName("Ratedcurrentsettingvalue");
                        setValue(version.getRatedLoadCurrent(n));
                        setGroup(DeviceCmdTypeEnum.CurrentSettingParameterBlock.name());
                        setCnname("额定电流整定值");
                        setUnit("A");
                        setDtime(DateUtils.getNowSqlDateTime());
                        setDateType(RemoteType.YC);
                    }});
                }};
                //剩余电流档位
                byte b = word[2];
                ResidualThresholdLevel = version.getResidualCurrent(b);
                //漏电分断延迟时间值
                b = word[5];
                DelayTimeLevel = version.getDelayTime(b);
            }

            //开关功能设定字
            controlWord = word[8];
            flagAllAlarm = HexUtils.getBitFromByte(6, controlWord) == 1;
            flagOverVoltageAlarm = HexUtils.getBitFromByte(1, controlWord) == 1;
            flagUnderVoltageAlarm = HexUtils.getBitFromByte(0, controlWord) == 1;
        }
        recordTime = DateUtils.getNowSqlDateTime();
        return dataAttrs;
    }
}
