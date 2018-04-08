package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

import main.com.handu.scada.protocol.base.BaseDataAnalyze;
import main.com.handu.scada.protocol.base.BaseIdentifyCodeDesc;
import main.com.handu.scada.protocol.enums.LPState;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataAnalyze extends BaseDataAnalyze {

    private LPState state;

    public LPState getState() {
        return state;
    }

    public void setState(LPState state) {
        this.state = state;
    }

    @Override
    public List<DataAttr> getData(BaseIdentifyCodeDesc item) {
        if (item.data == null) return null;
        try {

            String[] itemNames;
            String[] itemCnNames;
            String itemName;
            String itemCnName;
            Object objVal;
            DataAttr dataAttr;

            int BCD1;
            int BCD2;
            int BCD3;
            int BCD4;
            int BCD5;
            int BCD6;

            List<DataAttr> list = new ArrayList<>();

            if (item.data.length > 0) {
                item.data = (DICodeLP2007.decrease33(item.data));
            }

            switch (item.cmdtype) {
                //region 电流
                case Current: {
                    itemNames = new String[]{"Ia", "Ib", "Ic", "UTPC"};
                    itemCnNames = new String[]{"A相电流", "B相电流", "C相电流", "电流三相不平衡"};

                    if (item.length == 9) {
                        int[][] BcdArr = new int[3][3];
                        int k = 0;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                BcdArr[i][j] = HexUtils.bcdByteToInt(item.data[k]);
                                k++;
                            }
                        }

                        for (int n = 0; n < 3; n++) {
                            itemName = itemNames[n];
                            itemCnName = itemCnNames[n];
                            objVal = BcdArr[n][2] * 1000 + BcdArr[n][1] * 10 + BcdArr[n][0] / 10;
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(objVal);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setDateType(RemoteType.YC);
                            list.add(dataAttr);
                        }

                        //计算电流 - 三相不平衡值
                        if (list.size() == 3) {
                            double tmpval = 0.0;
                            double maxI = getMaxFromList(list);
                            double minI = getMinFromList(list);

                            if (maxI == 0.0) {
                                tmpval = 0;
                            } else if (minI == 0.0) {
                                tmpval = 1;
                            } else {
                                tmpval = (maxI - minI) / maxI;
                                tmpval = Math.round(tmpval * 10000) / 10000.0;
                            }
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemNames[3]);
                            dataAttr.setValue(tmpval);
                            dataAttr.setUnit("");
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setCnname(itemCnNames[3]);
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setDateType(RemoteType.YC);
                            list.add(dataAttr);
                        }
                    }
                    return list;
                }// endregion
                case Voltage: //region 电压
                {
                    itemNames = new String[]{"Ua", "Ub", "Uc"};
                    itemCnNames = new String[]{"A相电压", "B相电压", "C相电压"};
                    if (item.length == 6) {
                        int[][] BcdArr = new int[3][2];
                        int k = 0;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 2; j++) {
                                BcdArr[i][j] = HexUtils.bcdByteToInt(item.data[k]);
                                k++;
                            }
                        }
                        for (int n = 0; n < 3; n++) {
                            itemName = itemNames[n];
                            itemCnName = itemCnNames[n];
                            objVal = BcdArr[n][1] * 10 + BcdArr[n][0] / 10;
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(objVal);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setDateType(RemoteType.YC);
                            list.add(dataAttr);
                        }
                    }
                    return list;
                }
                case ResidualAndMaxPhase://region 最大相 剩余电流
                {
                    itemNames = new String[]{"MaxPhase", "Io"};
                    itemCnNames = new String[]{"最大相", "剩余电流"};
                    if (item.length == 3) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];
                        byte Phase = item.data[0];
                        if ((Phase & 0x01) == 0x01)
                            objVal = "A";
                        else if ((Phase & 0x02) == 0x02)
                            objVal = "B";
                        else if ((Phase & 0x04) == 0x04)
                            objVal = "C";
                        else
                            objVal = "";
                        objVal += "相";

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit == null ? "" : item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);

                        BCD1 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[2]);
                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];
                        objVal = BCD2 * 100 + BCD1;

                        DataAttr dataAttr1 = new DataAttr();
                        dataAttr1.setName(itemName);
                        dataAttr1.setValue(objVal);
                        dataAttr1.setUnit(item.unit == null ? "" : item.unit.split(",")[1]);
                        dataAttr1.setDtime(item.dtime);
                        dataAttr1.setCnname(itemCnName);
                        dataAttr1.setGroup(item.cmdtype.name());
                        dataAttr1.setDateType(RemoteType.YC);
                        list.add(dataAttr1);
                    }
                    return list;
                }
                case Residual:
                    itemNames = new String[]{"Io"};
                    itemCnNames = new String[]{"剩余电流"};
                    if (item.length == 2) {
                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];
                        objVal = BCD2 * 100 + BCD1;

                        DataAttr dataAttr1 = new DataAttr();
                        dataAttr1.setName(itemName);
                        dataAttr1.setValue(objVal);
                        dataAttr1.setUnit(item.unit);
                        dataAttr1.setDtime(item.dtime);
                        dataAttr1.setCnname(itemCnName);
                        dataAttr1.setGroup(item.cmdtype.name());
                        dataAttr1.setDateType(RemoteType.YC);
                        list.add(dataAttr1);
                    }
                    return list;
                //当前额度剩余电流动作值 极限不驱动时间
                case ResidualActionValue:
                    itemNames = new String[]{"CurrentLimitValue", "LimitNonDriveTime"};
                    itemCnNames = new String[]{"当前额度剩余电流动作值", "极限不驱动时间"};
                    if (item.length == 4) {
                        int[][] BcdArr = new int[2][2];
                        int k = 0;
                        for (int i = 0; i < 2; i++) {
                            for (int j = 0; j < 2; j++) {
                                BcdArr[i][j] = HexUtils.bcdByteToInt(item.data[k]);
                                k++;
                            }
                        }
                        for (int n = 0; n < 2; n++) {
                            itemName = itemNames[n];
                            itemCnName = itemCnNames[n];
                            objVal = BcdArr[n][1] * 100 + BcdArr[n][0];
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(objVal);
                            dataAttr.setUnit(item.unit.split(",")[n]);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setInsertHistory(false);
                            dataAttr.setDateType(RemoteType.YC);
                            list.add(dataAttr);
                        }
                    }
                    return list;

                case RunState://region 漏保状态
                {
                    itemName = item.cmdtype.name();
                    if (item.length == 1) {
                        byte stateWord1 = item.data[0];
                        if ((stateWord1 & 0x60) == 0x60 || (stateWord1 & 0x60) == 0x20) {
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(LPState.OFF.getValue());
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setCnname("分合状态");
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setDateType(RemoteType.YX);
                            list.add(dataAttr);
                            state = LPState.OFF;
                        } else {
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(LPState.ON.getValue());
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setCnname("分合状态");
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setDateType(RemoteType.YX);
                            list.add(dataAttr);
                            state = LPState.ON;
                        }
                        tripEventRecord = new TripEventRecord();
                        tripEventRecord.setState(state);
                        tripEventRecord.setAddress(getAddress());
                    }
                    return list;
                }
                case SecondLpUploadDateTime:
                case SecondLpCollectDateTime: {
                    itemName = item.cmdtype.name();
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);//秒
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);//分
                    BCD3 = HexUtils.bcdByteToInt(item.data[2]);//时
                    BCD4 = HexUtils.bcdByteToInt(item.data[3]);//日
                    BCD5 = HexUtils.bcdByteToInt(item.data[4]);//月
                    BCD6 = HexUtils.bcdByteToInt(item.data[5]);//年
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(2000 + BCD6, BCD5 - 1, BCD4, BCD3, BCD2, BCD1);
                    Date time = calendar.getTime();
                    dataAttr = new DataAttr();
                    dataAttr.setName(itemName);
                    dataAttr.setValue(DateUtils.dateToStr(time));
                    dataAttr.setUnit(item.unit);
                    dataAttr.setDtime(item.dtime);
                    dataAttr.setCnname(item.cmdtype.getName());
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setDateType(RemoteType.YC);
                    list.add(dataAttr);
                    return list;
                }
                case RunDate://region 日期
                {
                    itemName = item.cmdtype.name();
                    //BCD1 = HexUtils.bcdByteToInt(item.data[0]);//周
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);//日
                    BCD3 = HexUtils.bcdByteToInt(item.data[2]);//月
                    BCD4 = HexUtils.bcdByteToInt(item.data[3]);//年
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(2000 + BCD4, BCD3 - 1, BCD2, 0, 0, 0);
                    Date date = calendar.getTime();
                    dataAttr = new DataAttr();
                    dataAttr.setName(itemName);
                    dataAttr.setValue(DateUtils.dateToStr(date));
                    dataAttr.setUnit(item.unit);
                    dataAttr.setDtime(item.dtime);
                    dataAttr.setCnname("日期");
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setDateType(RemoteType.YC);
                    list.add(dataAttr);
                    return list;
                }
                case RunTime://region 时间
                {
                    itemName = item.cmdtype.name();
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);//秒
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);//分
                    BCD3 = HexUtils.bcdByteToInt(item.data[2]);//时
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(2000, 0, 1, BCD3, BCD2, BCD1);
                    Date time = calendar.getTime();//注意这里只有时分秒有效，年月日为固定的2000-1-1
                    dataAttr = new DataAttr();
                    dataAttr.setName(itemName);
                    dataAttr.setValue(DateUtils.dateToStr(time));
                    dataAttr.setUnit(item.unit);
                    dataAttr.setDtime(item.dtime);
                    dataAttr.setCnname("时间");
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setDateType(RemoteType.YC);
                    list.add(dataAttr);
                    return list;
                }
                case ProtectorTripEventRecord://region 保护器跳闸事件记录
                {
                    TripEventRecordAnalyze record = new TripEventRecordAnalyze(item.data);
                    this.tripEventRecord = record.getTripEventRecord();
                    this.tripEventRecord.setAddress(getAddress());
                    return null;
                }

                case ReadControlWordParameterModule://region 读控制字参数模块
                {
                    this.controlWord = new DltControlWord(item.data);
                    return null;
                }
                case WriteControlWordParameterModule:
                case WriteControlWordParameterModule1:
                case WriteControlWordParameterModule2:
                case WriteControlWordParameterModule3:
                case WriteControlWordParameterModule4:
                case WriteControlWordParameterModule5:
                    return null;
                case ReadDeviceModel://region 设备型号
                {
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 10) {
                        itemName = itemNames[0];
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname("设备型号");
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setInsertHistory(false);
                        list.add(dataAttr);
                    }
                    return list;
                }
                //通讯地址
                case ReadPostalAddress:
                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setValue(HexUtils.byteArrayToHexStr(HexUtils.reverse(item.data)));
                    dataAttr.setUnit("");
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setCnname("读通讯地址");
                    dataAttr.setInsertHistory(false);
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setName(item.cmdtype.name());
                    list.add(dataAttr);
                    return list;
                //广播校时
                case BroadcastTime:
                    return null;
                case ExecuteON:
                case ExecuteOFF:
                case SwitchTrip:
                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YCC);
                    dataAttr.setValue(item.cmdtype.getName() + "操作成功");
                    dataAttr.setUnit("");
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setCnname(item.cmdtype.getName());
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setName(item.cmdtype.name());
                    list.add(dataAttr);
                    return list;
                //通信波特率
                case CommunicationBaudRateCharacter:
                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YT);
                    dataAttr.setValue(item.data[0]);
                    dataAttr.setUnit("");
                    dataAttr.setInsertHistory(false);
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setCnname("通信波特率征字(0-9)");
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setName(item.cmdtype.name());
                    list.add(dataAttr);
                    return list;

                //额定电流整定值", "电流超限报警整定值
                case CurrentSettingParameterBlock:

                    itemNames = new String[]{"Ratedcurrentsettingvalue", "Currentlimitalarmsettingvalue"};
                    itemCnNames = new String[]{"额定电流整定值", "电流超限报警整定值"};

                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                    BCD3 = HexUtils.bcdByteToInt(item.data[2]);
                    objVal = BCD3 * 1000 + BCD2 * 10 + BCD1 / 10;

                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setValue(objVal);
                    dataAttr.setUnit(item.unit.split(",")[0]);
                    dataAttr.setDtime(item.dtime);
                    dataAttr.setInsertHistory(false);
                    dataAttr.setCnname(itemCnNames[0]);
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setName(itemNames[0]);
                    list.add(dataAttr);

                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setUnit(item.unit.split(",")[1]);
                    dataAttr.setName(itemNames[1]);
                    dataAttr.setCnname(itemCnNames[1]);
                    dataAttr.setDtime(item.dtime);
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setInsertHistory(false);
                    dataAttr.setValue(HexUtils.bcdByteToInt(item.data[3]));
                    list.add(dataAttr);

                    return list;

                //"剩余电流超限报警整定值", "剩余电流记录变化差值整定值", "剩余电流记录间隔时间整定值"
                case ResidualCurrentSettingParameterBlock:
                    itemCnNames = new String[]{"剩余电流超限报警整定值", "剩余电流记录变化差值整定值", "剩余电流记录间隔时间整定值"};
                    itemNames = new String[]{"Residualcurrentalarmsettingvalue", "Residualcurrentrecord", "Residualcurrentrecordingintervalsettingvalue"};
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                    objVal = BCD2 * 100 + BCD1;

                    Object finalObjVal = objVal;
                    list.add(new DataAttr() {{
                        setDateType(RemoteType.YC);
                        setUnit(item.unit.split(",")[0]);
                        setName(itemNames[0]);
                        setCnname(itemCnNames[0]);
                        setDtime(item.dtime);
                        setGroup(item.cmdtype.name());
                        setValue(finalObjVal);
                        setInsertHistory(false);
                        setRecordchange(true);
                    }});

                    objVal = HexUtils.bcdByteToInt(item.data[2]);
                    Object finalObjVal1 = objVal;
                    list.add(new DataAttr() {{
                        setDateType(RemoteType.YC);
                        setUnit(item.unit.split(",")[1]);
                        setName(itemNames[1]);
                        setCnname(itemCnNames[1]);
                        setDtime(item.dtime);
                        setGroup(item.cmdtype.name());
                        setValue(finalObjVal1);
                        setInsertHistory(false);
                        setRecordchange(true);
                    }});

                    objVal = HexUtils.bcdByteToInt(item.data[3]);
                    Object finalObjVal2 = objVal;
                    list.add(new DataAttr() {{
                        setDateType(RemoteType.YC);
                        setUnit(item.unit.split(",")[2]);
                        setName(itemNames[2]);
                        setCnname(itemCnNames[2]);
                        setDtime(item.dtime);
                        setGroup(item.cmdtype.name());
                        setValue(finalObjVal2);
                        setInsertHistory(false);
                        setRecordchange(true);
                    }});

                    return list;

                // "过电压整定值", "欠电压整定值", "断相电压整定值"
                case VoltageSettingParameterBlock:

                    itemNames = new String[]{"Overvoltagesettingvalue", "Undervoltagesetting", "Settingvalueoffaultphasevoltage"};
                    itemCnNames = new String[]{"过电压整定值", "欠电压整定值", "断相电压整定值"};

                    if (item.length == 6) {
                        int[][] BcdArr = new int[3][2];
                        int k = 0;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 2; j++) {
                                BcdArr[i][j] = HexUtils.bcdByteToInt(item.data[k]);
                                k++;
                            }
                        }
                        for (int n = 0; n < 3; n++) {
                            itemName = itemNames[n];
                            itemCnName = itemCnNames[n];
                            objVal = BcdArr[n][1] * 10 + BcdArr[n][0] / 10;
                            dataAttr = new DataAttr();
                            dataAttr.setDateType(RemoteType.YC);
                            dataAttr.setValue(objVal);
                            dataAttr.setInsertHistory(false);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setName(itemName);
                            dataAttr.setRecordchange(true);
                            list.add(dataAttr);
                        }
                    }
                    return list;

                //额定电流
                case RatedCurrent:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 6) {
                        itemName = itemNames[0];
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname("额定电流");
                        dataAttr.setInsertHistory(false);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setName(itemName);
                        list.add(dataAttr);
                    }
                    return list;

                //额定电压
                case RatedVoltage:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 6) {
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        itemName = itemNames[0];
                        dataAttr = new DataAttr();
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname("额定电压");
                        dataAttr.setInsertHistory(false);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setName(itemName);
                        list.add(dataAttr);
                    }
                    return list;

                //自动重合闸时间范围
                case AutoReclosingTimeRange:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 24) {
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        itemName = itemNames[0];
                        dataAttr = new DataAttr();
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname("自动重合闸时间范围");
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setName(itemName);
                        list.add(dataAttr);
                    }
                    return list;
                //设备号
                case DeviceNumber:
                    dataAttr = new DataAttr();
                    dataAttr.setValue(HexUtils.byteArrayToHexStr(HexUtils.reverse(item.data)));
                    dataAttr.setUnit("");
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setCnname("设备号");
                    dataAttr.setInsertHistory(false);
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setName(item.cmdtype.name());
                    list.add(dataAttr);
                    return list;

                //剩余电流动作特性（ A型或 AC 型）
                case AAC:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 24) {
                        itemName = itemNames[0];
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setValue(objVal);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname(item.cmdtype.getName());
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setName(itemName);
                        list.add(dataAttr);
                    }
                    return list;

                //总跳闸次数
                case TripTimes:
                    itemNames = new String[]{
                            "TripTimes", "ClosedTripTimes", "ResidualTripTimes",
                            "CurrentTripTimes", "VoltageTripTimes", "ManualClosingTripTimes",
                            "ZeroProtectionTrippingTimes", "TrippingTimes"
                    };
                    itemCnNames = new String[]{
                            "总跳闸次数", "闭锁性跳闸次数",
                            "剩余电流保护跳闸次数", "电流保护跳闸次数",
                            "电压保护跳闸次数", "手动 闭锁跳闸次数"
                            , "缺零保护跳闸次数", "试验跳闸次数（定时、远程按、键）"
                    };

                    if (item.length == 16) {
                        int[][] BcdArr = new int[8][2];
                        int k = 0;
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 2; j++) {
                                BcdArr[i][j] = HexUtils.bcdByteToInt(item.data[k]);
                                k++;
                            }
                        }
                        for (int n = 0; n < 8; n++) {
                            itemName = itemNames[n];
                            itemCnName = itemCnNames[n];
                            objVal = BcdArr[n][1] * 100 + BcdArr[n][0];
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(objVal);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setInsertHistory(false);
                            dataAttr.setDtime(item.dtime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdtype.name());
                            dataAttr.setDateType(RemoteType.YC);
                            list.add(dataAttr);
                        }
                    }
                    return list;

                //生产日期
                case ProduceDate:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 24) {
                        itemName = itemNames[0];
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname("生产日期");
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;

                //最大（ 壳架 ）电流 (Inm)
                case MaxShellCurrent:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 6) {
                        itemName = itemNames[0];
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname("最大（ 壳架 ）电流 (Inm)");
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;

                //退出剩余电流保护次数
                case ExitResidualModule:
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);

                    itemName = item.cmdtype.name();
                    itemCnName = "退出剩余电流保护次数";

                    objVal = BCD2 * 100 + BCD1;
                    dataAttr = new DataAttr();
                    dataAttr.setName(itemName);
                    dataAttr.setValue(objVal);
                    dataAttr.setUnit(item.unit);
                    dataAttr.setInsertHistory(false);
                    dataAttr.setDtime(item.dtime);
                    dataAttr.setCnname(itemCnName);
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setDateType(RemoteType.YC);
                    list.add(dataAttr);
                    return list;

                //保护器运行时间总累计
                case RunTotalTime:
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                    BCD3 = HexUtils.bcdByteToInt(item.data[2]);
                    BCD4 = HexUtils.bcdByteToInt(item.data[3]);

                    itemName = item.cmdtype.name();
                    itemCnName = "保护器运行时间总累计";

                    objVal = BCD4 * 10000 + BCD3 * 1000 + BCD2 * 100 + BCD1;
                    dataAttr = new DataAttr();
                    dataAttr.setName(itemName);
                    dataAttr.setValue(objVal);
                    dataAttr.setUnit(item.unit);
                    dataAttr.setInsertHistory(false);
                    dataAttr.setDtime(item.dtime);
                    dataAttr.setCnname(itemCnName);
                    dataAttr.setGroup(item.cmdtype.name());
                    dataAttr.setDateType(RemoteType.YC);
                    list.add(dataAttr);

                    return list;

                //厂家硬件版本号
                case HardWareVersionNumber:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 32) {
                        itemName = itemNames[0];
                        itemCnName = "厂家硬件版本号";
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;
                //厂家固件版本号
                case FirmwareVersionNumber:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 32) {
                        itemName = itemNames[0];
                        itemCnName = "厂家固件版本号";
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;

                //协议版本号
                case ProtocolVerNumber:
                    itemNames = new String[]{item.cmdtype.name()};
                    if (item.length == 16) {
                        itemName = itemNames[0];
                        itemCnName = "协议版本号";
                        objVal = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;
                //剩余电流最大相,最大值,最大值及发生时刻
                case MaxRC:
                    //剩余电流最小相,最小值,最小值及发生时刻
                case MinRC:
                    itemNames = new String[]{
                            "MaxPhase", "Io", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdtype.getName(), "剩余电流", "出现时刻"
                    };
                    if (item.length == 9) {

                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];
                        byte Phase = item.data[0];
                        if ((Phase & 0x01) == 0x01)
                            objVal = "A";
                        else if ((Phase & 0x02) == 0x02)
                            objVal = "B";
                        else if ((Phase & 0x04) == 0x04)
                            objVal = "C";
                        else
                            objVal = "";
                        objVal += "相";

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit("");
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);


                        BCD1 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[2]);
                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];
                        objVal = BCD2 * 100 + BCD1;

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit.split(",")[1]);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);

                        itemName = itemNames[2];
                        itemCnName = itemCnNames[2];

                        BCD1 = HexUtils.bcdByteToInt(item.data[3]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[4]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[5]);
                        BCD4 = HexUtils.bcdByteToInt(item.data[6]);
                        BCD5 = HexUtils.bcdByteToInt(item.data[7]);
                        BCD6 = HexUtils.bcdByteToInt(item.data[8]);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2000 + BCD6, BCD5 - 1, BCD4, BCD3, BCD2, BCD1);
                        Date alarmTime = calendar.getTime();

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(DateUtils.dateToStr(alarmTime));
                        dataAttr.setUnit("");
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);

                    }
                    return list;

                case MaxAC:
                case MaxBC:
                case MaxCC:
                    itemNames = new String[]{
                            "MaxCValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdtype.getName(), "出现时刻"
                    };

                    if (item.length == 9) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[2]);

                        objVal = BCD3 * 1000 + BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);

                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];

                        BCD1 = HexUtils.bcdByteToInt(item.data[3]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[4]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[5]);
                        BCD4 = HexUtils.bcdByteToInt(item.data[6]);
                        BCD5 = HexUtils.bcdByteToInt(item.data[7]);
                        BCD6 = HexUtils.bcdByteToInt(item.data[8]);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2000 + BCD6, BCD5 - 1, BCD4, BCD3, BCD2, BCD1);
                        Date alarmTime = calendar.getTime();

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(DateUtils.dateToStr(alarmTime));
                        dataAttr.setUnit("");
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;

                case MinAC:
                case MinBC:
                case MinCC:
                    itemNames = new String[]{
                            "MinCValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdtype.getName(), "出现时刻"
                    };

                    if (item.length == 9) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[2]);

                        objVal = BCD3 * 1000 + BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);

                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];

                        BCD1 = HexUtils.bcdByteToInt(item.data[3]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[4]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[5]);
                        BCD4 = HexUtils.bcdByteToInt(item.data[6]);
                        BCD5 = HexUtils.bcdByteToInt(item.data[7]);
                        BCD6 = HexUtils.bcdByteToInt(item.data[8]);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2000 + BCD6, BCD5 - 1, BCD4, BCD3, BCD2, BCD1);
                        Date alarmTime = calendar.getTime();

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(DateUtils.dateToStr(alarmTime));
                        dataAttr.setUnit("");
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;

                case MaxAV:
                case MaxBV:
                case MaxCV:
                    itemNames = new String[]{
                            "MaxVValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdtype.getName(), "出现时刻"
                    };

                    if (item.length == 8) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);

                        objVal = BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(objVal);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);

                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];

                        BCD1 = HexUtils.bcdByteToInt(item.data[2]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[3]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[4]);
                        BCD4 = HexUtils.bcdByteToInt(item.data[5]);
                        BCD5 = HexUtils.bcdByteToInt(item.data[6]);
                        BCD6 = HexUtils.bcdByteToInt(item.data[7]);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2000 + BCD6, BCD5 - 1, BCD4, BCD3, BCD2, BCD1);
                        Date alarmTime = calendar.getTime();

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(DateUtils.dateToStr(alarmTime));
                        dataAttr.setUnit("");
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;

                case MinAV:
                case MinBV:
                case MinCV:
                    itemNames = new String[]{
                            "MinVValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdtype.getName(), "出现时刻"
                    };

                    if (item.length == 8) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);

                        objVal = BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setValue(objVal);
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);

                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];

                        BCD1 = HexUtils.bcdByteToInt(item.data[2]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[3]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[4]);
                        BCD4 = HexUtils.bcdByteToInt(item.data[5]);
                        BCD5 = HexUtils.bcdByteToInt(item.data[6]);
                        BCD6 = HexUtils.bcdByteToInt(item.data[7]);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(2000 + BCD6, BCD5 - 1, BCD4, BCD3, BCD2, BCD1);
                        Date alarmTime = calendar.getTime();

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(DateUtils.dateToStr(alarmTime));
                        dataAttr.setUnit("");
                        dataAttr.setDtime(item.dtime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdtype.name());
                        dataAttr.setDateType(RemoteType.YC);
                        list.add(dataAttr);
                    }
                    return list;

                default:
                    return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    private double getMaxFromList(List<DataAttr> list) {
        double max = -1;
        for (DataAttr dataAttr : list) {
            double temp = Double.parseDouble(dataAttr.getValue().toString());
            if (max < temp) {
                max = temp;
            }
        }
        return max;
    }

    private double getMinFromList(List<DataAttr> list) {
        double min = 9999;
        for (DataAttr dataAttr : list) {
            double temp = Double.parseDouble(dataAttr.getValue().toString());
            if (min > temp) {
                min = temp;
            }
        }
        return min;
    }
}
