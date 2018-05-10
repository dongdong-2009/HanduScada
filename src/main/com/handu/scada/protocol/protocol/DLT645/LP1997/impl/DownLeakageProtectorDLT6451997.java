package main.com.handu.scada.protocol.protocol.DLT645.LP1997.impl;

import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol.DtuDownParse;
import main.com.handu.scada.protocol.base.BaseDLT645;
import main.com.handu.scada.protocol.base.DownStreamDLT645;
import main.com.handu.scada.protocol.base.MediaData;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.ProtocolTypes;
import main.com.handu.scada.protocol.factory.DLT645Factory;

/**
 * Created by 柳梦 on 2018/04/24.
 */
@DtuDownParse
public class DownLeakageProtectorDLT6451997 extends BaseDLT645 {

    private DownLeakageProtectorDLT6451997() {
    }

    @Override
    public MediaData sendCommand(ProtocolLayerData p) {
        try {
            protocolLayerData = p;
            //如果不是1997漏保
            if (protocolLayerData.deviceTypeEnum.getGroup() != DeviceGroup.LP1997) return null;
            if (protocolLayerData.CmdType == null) return null;
            MediaData data = new MediaData();
            data.DTUString = protocolLayerData.DTUString;
            data.PostalAddress = protocolLayerData.PostalAddress;
            data.HasDTUHead = protocolLayerData.HasDTUHead;
            DownStreamDLT645 ds;
            switch (protocolLayerData.CmdType) {
                case RunState:
                case ProtectorTripEventRecord:
                case ReadControlWordParameterModule:
                case ReadClock:
                case BroadcastTime:
                case ExecuteON:
                case ExecuteOFF:
                case SwitchTrip:
                case ReadPostalAddress:
                case VoltageCurrentAndResidualCurrent:
                    ds = DLT645Factory.getInstance(ProtocolTypes.DLTLP6451997, protocolLayerData.CmdType);
                    ds.address = data.PostalAddress;
                    ds.cmdByte = data.CommandData;
                    ds.GetBytes();
                    data.CommandData = ds.cmdByte;
                    data.MsgName = ds.msgName;
                    return data;
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
        return null;
    }
}
