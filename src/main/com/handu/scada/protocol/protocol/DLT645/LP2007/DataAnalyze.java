package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.base.BaseDataAnalyze;
import main.com.handu.scada.protocol.base.BaseIdentifyCodeDesc;
import main.com.handu.scada.protocol.enums.LPState;
import main.com.handu.scada.protocol.enums.RemoteType;
import main.com.handu.scada.protocol.protocol.DLT645.CommonTripReasonEnum;
import main.com.handu.scada.protocol.protocol.DLT645.TripEventRecord;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.*;

public class DataAnalyze extends BaseDataAnalyze {

    @Override
    public List<DataAttr> getData(BaseIdentifyCodeDesc item) {
        try {
            if (item.data == null) return null;
            if (item.data.length == 0) return null;
            if (item.length != item.data.length) return null;
            this.cmdType = item.cmdType;
            item.data = (DICodeLP2007.decrease33(item.data));
            dataAttrs = new ArrayList<>();
            switch (item.cmdType) {
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
                            value = BcdArr[n][2] * 1000 + BcdArr[n][1] * 10 + BcdArr[n][0] / 10;
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(value);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdType.name());
                            dataAttr.setDateType(RemoteType.YC);
                            dataAttrs.add(dataAttr);
                        }

                        //计算电流 - 三相不平衡值
                        if (dataAttrs.size() == 3) {
                            double tmpVal;
                            double maxI = getMaxFromList(dataAttrs);
                            double minI = getMinFromList(dataAttrs);
                            if (maxI == 0.0) {
                                tmpVal = 0;
                            } else if (minI == 0.0) {
                                tmpVal = 1;
                            } else {
                                tmpVal = (maxI - minI) / maxI;
                                tmpVal = Math.round(tmpVal * 10000) / 10000.0;
                            }
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemNames[3]);
                            dataAttr.setValue(tmpVal);
                            dataAttr.setUnit("%");
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setCnname(itemCnNames[3]);
                            dataAttr.setGroup("UTPC");
                            dataAttr.setDateType(RemoteType.YC);
                            dataAttrs.add(dataAttr);
                        }
                    }
                    return dataAttrs;
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
                            value = BcdArr[n][1] * 10 + BcdArr[n][0] / 10;
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(value);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdType.name());
                            dataAttr.setDateType(RemoteType.YC);
                            dataAttrs.add(dataAttr);
                        }
                    }
                    return dataAttrs;
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
                            value = "A";
                        else if ((Phase & 0x02) == 0x02)
                            value = "B";
                        else if ((Phase & 0x04) == 0x04)
                            value = "C";
                        else
                            value = "";
                        value += "相";

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit == null ? "" : item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);

                        BCD1 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[2]);
                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];
                        value = BCD2 * 100 + BCD1;

                        DataAttr dataAttr1 = new DataAttr();
                        dataAttr1.setName(itemName);
                        dataAttr1.setValue(value);
                        dataAttr1.setUnit(item.unit == null ? "" : item.unit.split(",")[1]);
                        dataAttr1.setDtime(item.dTime);
                        dataAttr1.setCnname(itemCnName);
                        dataAttr1.setGroup(item.cmdType.name());
                        dataAttr1.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr1);
                    }
                    return dataAttrs;
                }
                case Residual:
                    itemNames = new String[]{"Io"};
                    itemCnNames = new String[]{"剩余电流"};
                    if (item.length == 2) {
                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];
                        value = BCD2 * 100 + BCD1;

                        DataAttr dataAttr1 = new DataAttr();
                        dataAttr1.setName(itemName);
                        dataAttr1.setValue(value);
                        dataAttr1.setUnit(item.unit);
                        dataAttr1.setDtime(item.dTime);
                        dataAttr1.setCnname(itemCnName);
                        dataAttr1.setGroup(item.cmdType.name());
                        dataAttr1.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr1);
                    }
                    return dataAttrs;
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
                            value = BcdArr[n][1] * 100 + BcdArr[n][0];
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(value);
                            dataAttr.setUnit(item.unit.split(",")[n]);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdType.name());
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setInsertHistory(false);
                            dataAttr.setDateType(RemoteType.YC);
                            dataAttrs.add(dataAttr);
                        }
                    }
                    return dataAttrs;

                case RunState://region 漏保状态
                {
                    itemName = item.cmdType.name();
                    if (item.length == 1) {
                        byte stateWord = item.data[0];
                        if ((stateWord & 0x60) == 0x60 || (stateWord & 0x60) == 0x20) {
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(LPState.OFF.getValue());
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setCnname("分合状态");
                            dataAttr.setGroup(item.cmdType.name());
                            dataAttr.setDateType(RemoteType.YX);
                            dataAttrs.add(dataAttr);
                        } else {
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(LPState.ON.getValue());
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setCnname("分合状态");
                            dataAttr.setGroup(item.cmdType.name());
                            dataAttr.setDateType(RemoteType.YX);
                            dataAttrs.add(dataAttr);
                            tripEventRecord = new TripEventRecord();
                            tripEventRecord.setState(LPState.ON);
                            tripEventRecord.setAlarmReason("合闸成功");
                            tripEventRecord.setAlarmPhase("未知相");
                            tripEventRecord.tripReason = CommonTripReasonEnum.Normal;
                            tripEventRecord.setAddress(getAddress());
                        }
                    }
                    return dataAttrs;
                }
                case SecondLpUploadDateTime:
                case SecondLpCollectDateTime: {
                    itemName = item.cmdType.name();
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
                    dataAttr.setDtime(item.dTime);
                    dataAttr.setCnname(item.cmdType.getName());
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttrs.add(dataAttr);
                    return dataAttrs;
                }
                case RunDate://region 日期
                {
                    itemName = item.cmdType.name();
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
                    dataAttr.setDtime(item.dTime);
                    dataAttr.setCnname("日期");
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttrs.add(dataAttr);
                    return dataAttrs;
                }
                case RunTime://region 时间
                {
                    itemName = item.cmdType.name();
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
                    dataAttr.setDtime(item.dTime);
                    dataAttr.setCnname("时间");
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttrs.add(dataAttr);
                    return dataAttrs;
                }
                case ProtectorTripEventRecord://region 保护器跳闸事件记录
                {
                    TripEventRecordAnalyze record = new TripEventRecordAnalyze(item.data);
                    tripEventRecord = record.getTripEventRecord();
                    tripEventRecord.setState(LPState.OFF);
                    tripEventRecord.setAddress(getAddress());
                    return null;
                }
                case ReadControlWordParameterModule://region 读控制字参数模块
                {
                    this.controlWord = new DltControlWord(item.data, DeviceGroup.LP2007);
                    return controlWord.parseControlWord();
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
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 10) {
                        itemName = itemNames[0];
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname("设备型号");
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setInsertHistory(false);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;
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
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setName(item.cmdType.name());
                    dataAttrs.add(dataAttr);
                    return dataAttrs;
                //广播校时
                case BroadcastTime:
                    return null;
                case ExecuteON:
                case ExecuteOFF:
                case SwitchTrip:
                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YCC);
                    dataAttr.setValue(item.cmdType.getName() + "操作成功");
                    dataAttr.setUnit("");
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setCnname(item.cmdType.getName());
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setName(item.cmdType.name());
                    dataAttrs.add(dataAttr);
                    return dataAttrs;
                //通信波特率
                case CommunicationBaudRateCharacter:
                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YT);
                    dataAttr.setValue(item.data[0]);
                    dataAttr.setUnit("");
                    dataAttr.setInsertHistory(false);
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setCnname("通信波特率征字(0-9)");
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setName(item.cmdType.name());
                    dataAttrs.add(dataAttr);
                    return dataAttrs;

                //额定电流整定值", "电流超限报警整定值
                case CurrentSettingParameterBlock:
                    itemNames = new String[]{"Ratedcurrentsettingvalue", "Currentlimitalarmsettingvalue"};
                    itemCnNames = new String[]{"额定电流整定值", "电流超限报警整定值"};

                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                    BCD3 = HexUtils.bcdByteToInt(item.data[2]);
                    value = BCD3 * 1000 + BCD2 * 10 + BCD1 / 10;

                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setValue(value);
                    dataAttr.setUnit(item.unit.split(",")[0]);
                    dataAttr.setDtime(item.dTime);
                    dataAttr.setInsertHistory(false);
                    dataAttr.setCnname(itemCnNames[0]);
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setName(itemNames[0]);
                    dataAttrs.add(dataAttr);

                    dataAttr = new DataAttr();
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setUnit(item.unit.split(",")[1]);
                    dataAttr.setName(itemNames[1]);
                    dataAttr.setCnname(itemCnNames[1]);
                    dataAttr.setDtime(item.dTime);
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setInsertHistory(false);
                    dataAttr.setValue(HexUtils.bcdByteToInt(item.data[3]));
                    dataAttrs.add(dataAttr);
                    return dataAttrs;

                //"剩余电流超限报警整定值", "剩余电流记录变化差值整定值", "剩余电流记录间隔时间整定值"
                case ResidualCurrentSettingParameterBlock:
                    itemCnNames = new String[]{"剩余电流超限报警整定值", "剩余电流记录变化差值整定值", "剩余电流记录间隔时间整定值"};
                    itemNames = new String[]{"Residualcurrentalarmsettingvalue", "Residualcurrentrecord", "Residualcurrentrecordingintervalsettingvalue"};
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                    value = BCD2 * 100 + BCD1;

                    Object finalObjVal = value;
                    dataAttrs.add(new DataAttr() {{
                        setDateType(RemoteType.YC);
                        setUnit(item.unit.split(",")[0]);
                        setName(itemNames[0]);
                        setCnname(itemCnNames[0]);
                        setDtime(item.dTime);
                        setGroup(item.cmdType.name());
                        setValue(finalObjVal);
                        setInsertHistory(false);
                        setRecordchange(true);
                    }});

                    value = HexUtils.bcdByteToInt(item.data[2]);
                    Object finalObjVal1 = value;
                    dataAttrs.add(new DataAttr() {{
                        setDateType(RemoteType.YC);
                        setUnit(item.unit.split(",")[1]);
                        setName(itemNames[1]);
                        setCnname(itemCnNames[1]);
                        setDtime(item.dTime);
                        setGroup(item.cmdType.name());
                        setValue(finalObjVal1);
                        setInsertHistory(false);
                        setRecordchange(true);
                    }});

                    value = HexUtils.bcdByteToInt(item.data[3]);
                    Object finalObjVal2 = value;
                    dataAttrs.add(new DataAttr() {{
                        setDateType(RemoteType.YC);
                        setUnit(item.unit.split(",")[2]);
                        setName(itemNames[2]);
                        setCnname(itemCnNames[2]);
                        setDtime(item.dTime);
                        setGroup(item.cmdType.name());
                        setValue(finalObjVal2);
                        setInsertHistory(false);
                        setRecordchange(true);
                    }});

                    return dataAttrs;

                // "过电压整定值", "欠电压整定值", "断相电压整定值"
                case VoltageSettingParameterBlock:

                    itemNames = new String[]{"Overvoltagesettingvalue", "Undervoltagesetting", "Settingvalueoffaultphasevoltage"};
                    itemCnNames = new String[]{"过电压整定值", "欠电压整定值", "断相电压整定值"};

                    if (item.data.length == 6) {
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
                            value = BcdArr[n][1] * 10 + BcdArr[n][0] / 10;
                            dataAttr = new DataAttr();
                            dataAttr.setDateType(RemoteType.YC);
                            dataAttr.setValue(value);
                            dataAttr.setInsertHistory(false);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdType.name());
                            dataAttr.setName(itemName);
                            dataAttr.setRecordchange(true);
                            dataAttrs.add(dataAttr);
                        }
                    }
                    return dataAttrs;

                //额定电流
                case RatedCurrent:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 6) {
                        itemName = itemNames[0];
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname("额定电流");
                        dataAttr.setInsertHistory(false);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setName(itemName);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                //额定电压
                case RatedVoltage:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 6) {
                        value = HexUtils.HexArrayToASCII(item.data);
                        itemName = itemNames[0];
                        dataAttr = new DataAttr();
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname("额定电压");
                        dataAttr.setInsertHistory(false);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setName(itemName);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                //自动重合闸时间范围
                case AutoReclosingTimeRange:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 24) {
                        value = HexUtils.HexArrayToASCII(item.data);
                        itemName = itemNames[0];
                        dataAttr = new DataAttr();
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname("自动重合闸时间范围");
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setName(itemName);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;
                //设备号
                case DeviceNumber:
                    dataAttr = new DataAttr();
                    dataAttr.setValue(HexUtils.byteArrayToHexStr(HexUtils.reverse(item.data)));
                    dataAttr.setUnit("");
                    dataAttr.setDtime(DateUtils.getNowSqlDateTime());
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttr.setCnname("设备号");
                    dataAttr.setInsertHistory(false);
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setName(item.cmdType.name());
                    dataAttrs.add(dataAttr);
                    return dataAttrs;

                //剩余电流动作特性（ A型或 AC 型）
                case AAC:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 24) {
                        itemName = itemNames[0];
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setValue(value);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttr.setCnname(item.cmdType.getName());
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setName(itemName);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

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
                            value = BcdArr[n][1] * 100 + BcdArr[n][0];
                            dataAttr = new DataAttr();
                            dataAttr.setName(itemName);
                            dataAttr.setValue(value);
                            dataAttr.setUnit(item.unit);
                            dataAttr.setInsertHistory(false);
                            dataAttr.setDtime(item.dTime);
                            dataAttr.setCnname(itemCnName);
                            dataAttr.setGroup(item.cmdType.name());
                            dataAttr.setDateType(RemoteType.YC);
                            dataAttrs.add(dataAttr);
                        }
                    }
                    return dataAttrs;

                //生产日期
                case ProduceDate:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 24) {
                        itemName = itemNames[0];
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname("生产日期");
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                //最大（ 壳架 ）电流 (Inm)
                case MaxShellCurrent:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 6) {
                        itemName = itemNames[0];
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname("最大（ 壳架 ）电流 (Inm)");
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                //退出剩余电流保护次数
                case ExitResidualModule:
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);

                    itemName = item.cmdType.name();
                    itemCnName = "退出剩余电流保护次数";

                    value = BCD2 * 100 + BCD1;
                    dataAttr = new DataAttr();
                    dataAttr.setName(itemName);
                    dataAttr.setValue(value);
                    dataAttr.setUnit(item.unit);
                    dataAttr.setInsertHistory(false);
                    dataAttr.setDtime(item.dTime);
                    dataAttr.setCnname(itemCnName);
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttrs.add(dataAttr);
                    return dataAttrs;

                //保护器运行时间总累计
                case RunTotalTime:
                    BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                    BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                    BCD3 = HexUtils.bcdByteToInt(item.data[2]);
                    BCD4 = HexUtils.bcdByteToInt(item.data[3]);

                    itemName = item.cmdType.name();
                    itemCnName = "保护器运行时间总累计";

                    value = BCD4 * 10000 + BCD3 * 1000 + BCD2 * 100 + BCD1;
                    dataAttr = new DataAttr();
                    dataAttr.setName(itemName);
                    dataAttr.setValue(value);
                    dataAttr.setUnit(item.unit);
                    dataAttr.setInsertHistory(false);
                    dataAttr.setDtime(item.dTime);
                    dataAttr.setCnname(itemCnName);
                    dataAttr.setGroup(item.cmdType.name());
                    dataAttr.setDateType(RemoteType.YC);
                    dataAttrs.add(dataAttr);

                    return dataAttrs;

                //厂家硬件版本号
                case HardWareVersionNumber:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 32) {
                        itemName = itemNames[0];
                        itemCnName = "厂家硬件版本号";
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;
                //厂家固件版本号
                case FirmwareVersionNumber:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 32) {
                        itemName = itemNames[0];
                        itemCnName = "厂家固件版本号";
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                //协议版本号
                case ProtocolVerNumber:
                    itemNames = new String[]{item.cmdType.name()};
                    if (item.length == 16) {
                        itemName = itemNames[0];
                        itemCnName = "协议版本号";
                        value = HexUtils.HexArrayToASCII(item.data);
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setInsertHistory(false);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;
                //剩余电流最大相,最大值,最大值及发生时刻
                case MaxRC:
                    //剩余电流最小相,最小值,最小值及发生时刻
                case MinRC:
                    itemNames = new String[]{
                            "MaxPhase", "Io", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdType.getName(), "剩余电流", "出现时刻"
                    };
                    if (item.length == 9) {

                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];
                        byte Phase = item.data[0];
                        if ((Phase & 0x01) == 0x01)
                            value = "A";
                        else if ((Phase & 0x02) == 0x02)
                            value = "B";
                        else if ((Phase & 0x04) == 0x04)
                            value = "C";
                        else
                            value = "";
                        value += "相";

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit("");
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);


                        BCD1 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[2]);
                        itemName = itemNames[1];
                        itemCnName = itemCnNames[1];
                        value = BCD2 * 100 + BCD1;

                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit.split(",")[1]);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);

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
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);

                    }
                    return dataAttrs;

                case MaxAC:
                case MaxBC:
                case MaxCC:
                    itemNames = new String[]{
                            "MaxCValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdType.getName(), "出现时刻"
                    };

                    if (item.length == 9) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[2]);

                        value = BCD3 * 1000 + BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);

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
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                case MinAC:
                case MinBC:
                case MinCC:
                    itemNames = new String[]{
                            "MinCValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdType.getName(), "出现时刻"
                    };

                    if (item.length == 9) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);
                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD3 = HexUtils.bcdByteToInt(item.data[2]);

                        value = BCD3 * 1000 + BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);

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
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                case MaxAV:
                case MaxBV:
                case MaxCV:
                    itemNames = new String[]{
                            "MaxVValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdType.getName(), "出现时刻"
                    };

                    if (item.length == 8) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);

                        value = BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setValue(value);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);

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
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                case MinAV:
                case MinBV:
                case MinCV:
                    itemNames = new String[]{
                            "MinVValue", "HappenTime"
                    };
                    itemCnNames = new String[]{
                            item.cmdType.getName(), "出现时刻"
                    };

                    if (item.length == 8) {
                        itemName = itemNames[0];
                        itemCnName = itemCnNames[0];

                        BCD1 = HexUtils.bcdByteToInt(item.data[0]);
                        BCD2 = HexUtils.bcdByteToInt(item.data[1]);

                        value = BCD2 * 10 + BCD1 / 10;
                        dataAttr = new DataAttr();
                        dataAttr.setName(itemName);
                        dataAttr.setUnit(item.unit.split(",")[0]);
                        dataAttr.setValue(value);
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);

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
                        dataAttr.setDtime(item.dTime);
                        dataAttr.setCnname(itemCnName);
                        dataAttr.setGroup(item.cmdType.name());
                        dataAttr.setDateType(RemoteType.YC);
                        dataAttrs.add(dataAttr);
                    }
                    return dataAttrs;

                default:
                    return null;
            }
        } catch (Exception ex) {
            ExceptionHandler.handle(ex);
        }
        return null;
    }

    private double getMaxFromList(List<DataAttr> list) {
        double max = -1;
        Optional<DataAttr> o = list.stream().max((o1, o2) -> {
            double v1 = Double.parseDouble(o1.getValue().toString());
            double v2 = Double.parseDouble(o2.getValue().toString());
            return v1 < v2 ? -1 : v1 == v2 ? 0 : 1;
        });
        return o.isPresent() ? Double.parseDouble(String.valueOf(o.get().getValue())) : max;
    }

    private double getMinFromList(List<DataAttr> list) {
        double min = 9999;
        Optional<DataAttr> o = list.stream().min((o1, o2) -> {
            double v1 = Double.parseDouble(o1.getValue().toString());
            double v2 = Double.parseDouble(o2.getValue().toString());
            return v1 < v2 ? -1 : v1 == v2 ? 0 : 1;
        });
        return o.isPresent() ? Double.parseDouble(String.valueOf(o.get().getValue())) : min;
    }
}
