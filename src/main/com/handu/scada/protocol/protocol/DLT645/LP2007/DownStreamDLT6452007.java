package main.com.handu.scada.protocol.protocol.DLT645.LP2007;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.base.DownStreamDLT645;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.WR;
import main.com.handu.scada.utils.HexUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.Calendar;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public class DownStreamDLT6452007 extends DownStreamDLT645 {

    public DownStreamDLT6452007(DeviceCmdTypeEnum cmdType) {
        init(cmdType);
    }

    public DownStreamDLT6452007(DeviceCmdTypeEnum cmdType, Object data) {
        this.data = data;
        init(cmdType);
    }

    @Override
    public void GetBytes() {
        //全零漏保
        if (StringsUtils.padLeft(this.dtuAddress, 12, "0").equals(StringsUtils.padLeft(this.address, 12, "0"))) {
            this.address = "000000000000";
        }
        //地址为1 的漏保
        else if (StringsUtils.padLeft((Integer.parseInt(this.dtuAddress) + 1), 12, "0")
                .equals(StringsUtils.padLeft(this.address, 12, "0"))) {
            this.address = "000000000001";
        }
        if (!super.getBytes()) {
            cmdByte = null;
        }
    }

    private void init(DeviceCmdTypeEnum cmdType) {
        try {
            //默认设置
            controlCode = 0x11;
            length = 0x04;
            msgName = cmdType.getName();
            wr = WR.R;
            switch (cmdType) {
                case Voltage:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.Voltage);
                    break;
                case MaxRC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxRC);
                    break;
                case MinRC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MinRC);
                    break;
                case MaxAC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxAC);
                    break;
                case MaxBC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxBC);
                    break;
                case MaxCC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxCC);
                    break;
                case MinAC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MinAC);
                    break;
                case MinBC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MinBC);
                    break;
                case MinCC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MinCC);
                    break;
                case MaxAV:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxAV);
                    break;
                case MaxBV:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxBV);
                    break;
                case MaxCV:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxCV);
                    break;
                case MinAV:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MinAV);
                    break;
                case MinBV:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MinBV);
                    break;
                case MinCV:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MinCV);
                    break;
                case AAC:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.AAC);
                    break;
                case DeviceNumber:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.DeviceNumber);
                    break;
                case TripTimes:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.TripTimes);
                    break;
                case ProduceDate:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ProduceDate);
                    break;
                case ExitResidualModule:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ExitResidualModule);
                    break;
                case MaxShellCurrent:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.MaxShellCurrent);
                    break;
                case RunTotalTime:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.RunTotalTime);
                    break;
                case HardWareVersionNumber:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.HardWareVersionNumber);
                    break;
                case FirmwareVersionNumber:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.FirmwareVersionNumber);
                    break;
                case ProtocolVerNumber:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ProtocolVerNumber);
                    break;
                case Current:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.Current);
                    break;
                case ResidualAndMaxPhase:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.Residual);
                    break;
                case RunState:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.RunState);
                    break;
                case RunDate:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.RunDate);
                    break;
                case RunTime:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.RunTime);
                    break;
                case ResidualActionValue:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ResidualActionValue);
                    break;
                case ResidualLimitEventRecord:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ResidualLimitEventRecord);
                    break;
                case ProtectorTripEventRecord:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ProtectorTripEventRecord);
                    break;
                case ResidualCurrentAlarmEventRecord:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ResidualCurrentAlarmEventRecord);
                    break;
                case ProtectorSelfCheckEventRecord:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ProtectorSelfCheckEventRecord);
                    break;
                case ReadControlWordParameterModule:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ReadControlWordParameterModule);
                    break;
                case AutoReclosingTimeRange:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.AutoReclosingTimeRange);
                    break;
                case ReadDeviceModel:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.DeviceModel);
                    break;
                case RatedVoltage:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.RatedVoltage);
                    break;
                case RatedCurrent:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.RatedCurrent);
                    break;
                case ResidualCurrentSettingParameterBlock:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ResidualCurrentSettingParameterBlock);
                    break;
                case VoltageSettingParameterBlock:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.VoltageSettingParameterBlock);
                    break;
                case CurrentSettingParameterBlock:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.CurrentSettingParameterBlock);
                    break;
                case ReadPostalAddress:
                    controlCode = 0x13;
                    length = 0x00;
                    diCode = new byte[]{};
                    address = "AAAAAAAAAAAA";
                    break;
                case WritePostalAddress:
                    controlCode = 0x15;
                    length = 0x06;
                    diCode = new byte[]{};
                    address = "AAAAAAAAAAAA";
                    break;
                case CommunicationBaudRateCharacter:
                    diCode = DICodeLP2007.increase33(DICodeLP2007.CommunicationBaudRateCharacter);
                    break;
                case WriteCommunicationBaudRateCharacter:
                    controlCode = 0x17;
                    length = 0x01;
                    datas = DICodeLP2007.increase33(HexUtils.hexStringToByteArray(HexUtils.binToHex(data.toString(), 1)));
                    diCode = new byte[]{};
                    break;
                case BroadcastTime:
                    controlCode = 0x08;
                    length = 0x06;
                    address = "999999999999";
                    diCode = new byte[]{};
                    datas = new byte[6];
                    Calendar calendar = Calendar.getInstance();
                    datas[0] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.SECOND)));
                    datas[1] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.MINUTE)));
                    datas[2] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.HOUR)));
                    datas[3] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.DAY_OF_MONTH)));
                    datas[4] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.MONTH) + 1));
                    datas[5] = HexUtils.increase33(HexUtils.intToBCDByte(calendar.get(Calendar.YEAR)));
                    wr = WR.W;
                    break;
                case WriteControlWordParameterModule:
                    controlCode = 0x14;
                    length = 0x11;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteControlWordParameterModule);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = new byte[5];
                    break;
                case WriteControlWordParameterModule1:
                    controlCode = 0x14;
                    length = 0x0D;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteControlWordParameterModule1);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.hexStringToByteArray(HexUtils.binToHex(data.toString(), 8)));
                    break;
                case WriteControlWordParameterModule2:
                    controlCode = 0x14;
                    length = 0x0D;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteControlWordParameterModule2);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.hexStringToByteArray(HexUtils.binToHex(data.toString(), 8)));
                    break;
                case WriteControlWordParameterModule3:
                    controlCode = 0x14;
                    length = 0x0D;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteControlWordParameterModule3);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.hexStringToByteArray(HexUtils.binToHex(data.toString(), 8)));
                    break;
                case WriteControlWordParameterModule4:
                    controlCode = 0x14;
                    length = 0x0D;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteControlWordParameterModule4);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.hexStringToByteArray(HexUtils.binToHex(data.toString(), 8)));
                    break;
                case WriteControlWordParameterModule5:
                    controlCode = 0x14;
                    length = 0x0D;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteControlWordParameterModule5);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.hexStringToByteArray(HexUtils.binToHex(data.toString(), 8)));
                    break;
                case WriteDeviceModel:
                    controlCode = 0x14;
                    length = 0x16;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.DeviceModel);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.padLeft(10, (byte) 0xff, HexUtils.reverse((data.toString().getBytes()))));
                    break;
                case WriteUndervoltagesettingvalue:
                    controlCode = 0x14;
                    length = 0x0E;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteUndervoltagesettingvalue);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.str2Bcd(data.toString()));
                    break;
                case WriteOvervoltagesettingvalue:
                    controlCode = 0x14;
                    length = 0x0E;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteOvervoltagesettingvalue);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.str2Bcd(data.toString()));
                    break;
                case Writephasebreakvoltagesettingvalue:
                    controlCode = 0x14;
                    length = 0x0E;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.Writephasebreakvoltagesettingvalue);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.str2Bcd(data.toString()));
                    break;
                case WriteCurrentoverloadwarningsettingvalue:
                    controlCode = 0x14;
                    length = 0x0D;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteCurrentoverloadwarningsettingvalue);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = new byte[]{HexUtils.increase33(HexUtils.intToBCDByte(Integer.parseInt(data.toString())))};
                    break;
                case WriteResidualCurrentAlarmSettingValue:
                    controlCode = 0x14;
                    length = 0x0E;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.WriteResidualCurrentAlarmSettingValue);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(HexUtils.intToBcd100(data));
                    break;
                case ExecuteOFF:
                    controlCode = 0x1C;
                    length = 0x0E;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ExecuteOFF);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    wr = WR.W;
                    datas = DICodeLP2007.increase33(new byte[]{0x00, 0x02});
                    break;
                case ExecuteON:
                    controlCode = 0x1C;
                    length = 0x0E;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.ExecuteON);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    datas = DICodeLP2007.increase33(new byte[]{0x00, 0x02});
                    wr = WR.W;
                    break;
                case SwitchTrip:
                    controlCode = 0x1C;
                    length = 0x0E;
                    diCode = DICodeLP2007.increase33(DICodeLP2007.SwitchTrip);
                    pwd = const_pwd;
                    operCode = const_opercode;
                    datas = DICodeLP2007.increase33(new byte[]{0x00, 0x02});
                    wr = WR.W;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }
}
