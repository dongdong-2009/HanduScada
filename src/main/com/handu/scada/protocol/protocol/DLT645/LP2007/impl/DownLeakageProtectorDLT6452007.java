package main.com.handu.scada.protocol.protocol.DLT645.LP2007.impl;

import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuDownParse;
import main.com.handu.scada.protocol.base.BaseDLT645;
import main.com.handu.scada.protocol.base.DownStreamDLT645;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.ProtocolTypes;
import main.com.handu.scada.protocol.factory.DLT645Factory;
import main.com.handu.scada.utils.StringsUtils;

import static main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum.RunState;

/**
 * Created by 柳梦 on 2017/12/24.
 */

@DtuDownParse
public class DownLeakageProtectorDLT6452007 extends BaseDLT645 {

    private DownLeakageProtectorDLT6452007() {
    }

    @Override
    public MediaData sendCommand(ProtocolLayerData p) {
        try {
            this.protocolLayerData = p;
            //如果不是漏保
            if (protocolLayerData.deviceTypeEnum.getGroup() != DeviceGroup.LP2007) {
                return null;
            }
            if (protocolLayerData.CmdType == null) return null;
            MediaData data = new MediaData();
            data.DTUString = protocolLayerData.DTUString;
            data.PostalAddress = protocolLayerData.PostalAddress;
            data.HasDTUHead = protocolLayerData.HasDTUHead;
            DownStreamDLT645 ds;
            switch (protocolLayerData.CmdType) {
                case ALL_CALL:
                    ds = DLT645Factory.getInstance(ProtocolTypes.DLTLP6452007, RunState);
                    if (ds != null) {
                        ds.address = data.PostalAddress;
                        ds.dtuAddress = data.DTUString;
                        ds.GetBytes();
                        data.CommandData = ds.cmdByte;
                        data.MsgName = "APP终端召测：" + ds.msgName;
                        return data;
                    }
                    return null;
                case RunState:
                case Current:
                case ResidualAndMaxPhase:
                case AutoReclosingTimeRange:
                case ReadDeviceModel:
                case RatedCurrent:
                case RatedVoltage:
                case ResidualActionValue:
                case RunDate:
                case RunTime:
                case ProtectorTripEventRecord:
                case Voltage:
                case CurrentSettingParameterBlock:
                case ResidualCurrentSettingParameterBlock:
                case VoltageSettingParameterBlock:
                case ReadControlWordParameterModule:
                case CommunicationBaudRateCharacter:
                case ExecuteOFF:
                case ExecuteON:
                case SwitchTrip:
                case MaxRC:
                case MinRC:
                case MaxAC:
                case MaxBC:
                case MaxCC:
                case MinAC:
                case MinBC:
                case MinCC:
                case MaxAV:
                case MaxBV:
                case MaxCV:
                case MinAV:
                case MinBV:
                case MinCV:
                case DeviceNumber:
                case AAC:
                case TripTimes:
                case ProduceDate:
                case ExitResidualModule:
                case MaxShellCurrent:
                case RunTotalTime:
                case HardWareVersionNumber:
                case FirmwareVersionNumber:
                case ProtocolVerNumber:
                    ds = DLT645Factory.getInstance(ProtocolTypes.DLTLP6452007, protocolLayerData.CmdType);
                    if (ds != null) {
                        ds.address = data.PostalAddress;
                        ds.dtuAddress = data.DTUString;
                        ds.GetBytes();
                        data.CommandData = ds.cmdByte;
                        data.MsgName = ds.msgName;
                        return data;
                    }
                    return null;
                case WritePostalAddress:
                case WriteCommunicationBaudRateCharacter:
                case WriteControlWordParameterModule:
                case WriteControlWordParameterModule1:
                case WriteControlWordParameterModule2:
                case WriteControlWordParameterModule3:
                case WriteControlWordParameterModule4:
                case WriteControlWordParameterModule5:
                case WriteCurrentoverloadwarningsettingvalue:
                case WriteOvervoltagesettingvalue:
                case Writephasebreakvoltagesettingvalue:
                case WriteUndervoltagesettingvalue:
                case WriteResidualCurrentAlarmSettingValue:
                    if (!StringsUtils.isEmpty(protocolLayerData.strData)) {
                        ds = DLT645Factory.getInstance(ProtocolTypes.DLTLP6452007, protocolLayerData.CmdType, protocolLayerData.strData);
                        if (ds != null) {
                            ds.address = data.PostalAddress;
                            ds.dtuAddress = data.DTUString;
                            ds.GetBytes();
                            data.CommandData = ds.cmdByte;
                            data.MsgName = ds.msgName;
                            return data;
                        }
                    }
                    return null;
                case ReadPostalAddress:
                    ds = DLT645Factory.getInstance(ProtocolTypes.DLTLP6452007, protocolLayerData.CmdType);
                    if (ds != null) {
                        ds.dtuAddress = data.DTUString;
                        ds.GetBytes();
                        data.CommandData = ds.cmdByte;
                        data.MsgName = ds.msgName;
                        return data;
                    }
                    return null;
                case BroadcastTime:
                    ds = DLT645Factory.getInstance(ProtocolTypes.DLTLP6452007, protocolLayerData.CmdType);
                    if (ds != null) {
                        ds.dtuAddress = data.DTUString;
                        ds.GetBytes();
                        data.CommandData = ds.cmdByte;
                        data.MsgName = ds.msgName;
                        return data;
                    }
                    return null;
                default:
                    break;
            }
        } catch (Exception e) {
            ExceptionHandler.print(e);
        }
        return null;
    }
}
