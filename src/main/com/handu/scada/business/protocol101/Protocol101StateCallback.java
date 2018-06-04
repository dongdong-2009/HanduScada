package main.com.handu.scada.business.protocol101;

import main.com.handu.scada.MainServer;
import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DBEvent;
import main.com.handu.scada.netty.listener.ProtocolStateCallbackListener;
import main.com.handu.scada.netty.server.MsgType;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.utils.LogUtils;

/**
 * Created by 柳梦 on 2018/01/05.
 */
public class Protocol101StateCallback implements ProtocolStateCallbackListener {

    @Override
    public void online(String connectionId, String ip, String port, String deviceAddress, MsgType type) {
        if (type == MsgType.LOGIN) {
            LogUtils.info("device101 login:clientId----" + connectionId + "---address " + deviceAddress, true);
        } else if (type == MsgType.CONNECTION_SUCCESS) {
            LogUtils.info("device101 connection success:clientId----" + "---address " + deviceAddress);
            return;
        } else if (type == MsgType.ONLINE) {
            LogUtils.info("device101 online:clientId----" + connectionId + "---address " + deviceAddress, true);
        }
        if (deviceAddress == null) return;
        MyCacheManager.updateDevice101OnlineState(deviceAddress, true);
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        Protocol101Data data = new Protocol101Data();
        data.setIp(ip);
        data.setPort(port);
        data.setAddress(deviceAddress);
        data.setCmdType(Protocol101CmdEnum.PROTOCOL101_ON_LINE);
        EventManager.getInstance().publish(new DBEvent(data));
    }

    @Override
    public void offline(String connectionId, String ip, String port, String deviceAddress) {
        LogUtils.error("device101 offline:clientId----" + connectionId + "---address " + deviceAddress, true);
        if (deviceAddress == null) return;
        MyCacheManager.updateDevice101OnlineState(deviceAddress, false);
        if (MainServer.START_TYPE == MainServer.START_NO_SQL) return;
        Protocol101Data data = new Protocol101Data();
        data.setIp(ip);
        data.setPort(port);
        data.setAddress(deviceAddress);
        data.setCmdType(Protocol101CmdEnum.PROTOCOL101_OFF_LINE);
        EventManager.getInstance().publish(new DBEvent(data));
    }
}
