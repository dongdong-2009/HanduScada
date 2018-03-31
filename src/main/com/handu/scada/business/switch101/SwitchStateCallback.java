package main.com.handu.scada.business.switch101;

import main.com.handu.scada.MainServer;
import main.com.handu.scada.netty.listener.SwitchStateCallbackListener;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.utils.LogUtils;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public class SwitchStateCallback implements SwitchStateCallbackListener {

    @Override
    public void online(String connectionId, String deviceAddress, MsgType type) {
        if (type == MsgType.LOGIN) {
            LogUtils.info("switch login:connectionId----" + connectionId + "---deviceAddress " + deviceAddress, true);
        } else if (type == MsgType.HEARTBEAT) {
            LogUtils.info("switch heartbeat:connectionId----" + connectionId + "---deviceAddress " + deviceAddress);
        } else if (type == MsgType.ONLINE) {
            LogUtils.info("switch online:connectionId----" + connectionId, true);
        } else if (type == MsgType.CONNECTION) {
            LogUtils.info("switch connection:connectionId----" + connectionId, true);
        }
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        if (deviceAddress == null) return;
//        ProtocolLayerData protocolLayerData = new ProtocolLayerData();
//        protocolLayerData.clientId = connectionId;
//        protocolLayerData.dtuAddress = deviceAddress;
//        protocolLayerData.CmdType = DeviceCmdTypeEnum.SWITCH_ON_LINE;
//        EventManager.getInstance().publish(new DBEvent(protocolLayerData));
    }

    @Override
    public void offline(String connectionId, String deviceAddress) {
        LogUtils.error("switch offline:connectionId----" + connectionId + "---deviceAddress " + deviceAddress, true);
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        if (deviceAddress == null) return;
//        ProtocolLayerData protocolLayerData = new ProtocolLayerData();
//        protocolLayerData.clientId = connectionId;
//        protocolLayerData.dtuAddress = deviceAddress;
//        protocolLayerData.CmdType = DeviceCmdTypeEnum.SWITCH_ON_LINE;
//        EventManager.getInstance().publish(new DBEvent(protocolLayerData));
    }
}
