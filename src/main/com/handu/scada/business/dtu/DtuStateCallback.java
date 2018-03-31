package main.com.handu.scada.business.dtu;

import main.com.handu.scada.MainServer;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DBEvent;
import main.com.handu.scada.netty.listener.DtuStateCallbackListener;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.netty.server.dtu.DtuChannelManager;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.utils.LogUtils;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public class DtuStateCallback implements DtuStateCallbackListener {

    @Override
    public void online(String clientId, String dtuAddress, MsgType type) {
        if (type == MsgType.LOGIN) {
            LogUtils.info("dtu login:clientId----" + clientId + "---dtuId " + dtuAddress + "---dtuNum=" + DtuChannelManager.getDtuMapCount(), true);
        } else if (type == MsgType.HEARTBEAT) {
            LogUtils.info("dtu heartbeat:clientId----" + clientId + "---dtuId " + dtuAddress);
            return;
        } else if (type == MsgType.ONLINE) {
            LogUtils.info("dtu online:clientId----" + clientId, true);
            return;
        }
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        if (dtuAddress == null) return;
        ProtocolLayerData protocolLayerData = new ProtocolLayerData();
        protocolLayerData.clientId = clientId;
        protocolLayerData.dtuAddress = dtuAddress;
        protocolLayerData.CmdType = DeviceCmdTypeEnum.DTU_LOGIN;
        EventManager.getInstance().publish(new DBEvent(protocolLayerData));
    }

    @Override
    public void offline(String clientId, String dtuAddress) {
        LogUtils.error("dtu offline:clientId----" + clientId + "---dtuId " + dtuAddress, true);
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        if (dtuAddress == null) return;
        ProtocolLayerData protocolLayerData = new ProtocolLayerData();
        protocolLayerData.clientId = clientId;
        protocolLayerData.dtuAddress = dtuAddress;
        protocolLayerData.CmdType = DeviceCmdTypeEnum.DTU_OFF_LINE;
        EventManager.getInstance().publish(new DBEvent(protocolLayerData));
    }
}
