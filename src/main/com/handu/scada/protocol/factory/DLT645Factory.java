package main.com.handu.scada.protocol.factory;

import main.com.handu.scada.protocol.base.DownStreamDLT645;
import main.com.handu.scada.protocol.enums.ProtocolTypes;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;

/**
 * Created by 柳梦 on 2017/12/26.
 */
public class DLT645Factory {

    public static DownStreamDLT645 getInstance(ProtocolTypes type, DeviceCmdTypeEnum cmdType) {
        switch (type) {
            case DLTLP6452007:
                if (cmdType == null) return null;
                return new DownStreamDLT6452007(cmdType);
            default:
                break;
        }
        return null;
    }

    public static DownStreamDLT645 getInstance(ProtocolTypes type, DeviceCmdTypeEnum cmdType, Object data) {
        switch (type) {
            case DLTLP6452007:
                if (cmdType == null) return null;
                return new DownStreamDLT6452007(cmdType, data);
            default:
                break;
        }
        return null;
    }
}
