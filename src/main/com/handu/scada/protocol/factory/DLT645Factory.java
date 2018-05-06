package main.com.handu.scada.protocol.factory;

import main.com.handu.scada.protocol.base.DownStreamDLT645;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.ProtocolTypes;
import main.com.handu.scada.protocol.protocol.DLT645.LP1997.DownStreamDLT6451997;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DownStreamDLT6452007;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public class DLT645Factory {

    public static DownStreamDLT645 getInstance(ProtocolTypes type, DeviceCmdTypeEnum cmdType) {
        if (cmdType == null) return null;
        switch (type) {
            case DLTLP6452007:
                return new DownStreamDLT6452007(cmdType);
            case DLTLP6451997:
                return new DownStreamDLT6451997(cmdType);
            default:
                break;
        }
        return null;
    }

    public static DownStreamDLT645 getInstance(ProtocolTypes type, DeviceCmdTypeEnum cmdType, Object data) {
        if (cmdType == null) return null;
        switch (type) {
            case DLTLP6452007:
                return new DownStreamDLT6452007(cmdType, data);
            case DLTLP6451997:
                return null;
            default:
                break;
        }
        return null;
    }
}
