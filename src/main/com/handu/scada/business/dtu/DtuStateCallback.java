package main.com.handu.scada.business.dtu;

import main.com.handu.scada.MainServer;
import main.com.handu.scada.cache.MyCacheManager;
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
        if (dtuAddress == null) return;
        if (type == MsgType.LOGIN) {
            LogUtils.info("dtu login:clientId----" + clientId + "---dtuAddress " + dtuAddress + "---dtuNum=" + DtuChannelManager.getDtuMapCount(), true);
        } else if (type == MsgType.HEARTBEAT) {
            LogUtils.info("dtu heartbeat:clientId----" + clientId + "---dtuAddress " + dtuAddress);
        }
        MyCacheManager.updateDtuOnlineState(dtuAddress, true);
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        //如果收到心跳和登录则更新dtu上线时间
        ProtocolLayerData protocolLayerData = new ProtocolLayerData();
        protocolLayerData.clientId = clientId;
        protocolLayerData.dtuAddress = dtuAddress;
        protocolLayerData.CmdType = type == MsgType.LOGIN ? DeviceCmdTypeEnum.DTU_LOGIN : DeviceCmdTypeEnum.DTU_HEARTBEAT;
        EventManager.getInstance().publish(new DBEvent(protocolLayerData));
    }

    @Override
    public void offline(String clientId, String dtuAddress) {
        if (dtuAddress == null) return;
        LogUtils.error("dtu offline:clientId----" + clientId + "---dtuAddress " + dtuAddress + "---dtuNum=" + DtuChannelManager.getDtuMapCount(), true);
        MyCacheManager.updateDtuOnlineState(dtuAddress, false);
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        ProtocolLayerData protocolLayerData = new ProtocolLayerData();
        protocolLayerData.clientId = clientId;
        protocolLayerData.dtuAddress = dtuAddress;
        protocolLayerData.CmdType = DeviceCmdTypeEnum.DTU_OFF_LINE;
        EventManager.getInstance().publish(new DBEvent(protocolLayerData));
    }
}
