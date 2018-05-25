package main.com.handu.scada.quartz.job;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101BaseData;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;

/**
 * Created by 柳梦 on 2018/05/22.
 */
public class Base101Command {

    protected void send(Protocol101CmdEnum cmdEnum, DeviceTypeEnum deviceTypeEnum) {
        Protocol101BaseData data = new Protocol101BaseData();
        data.setCmdType(cmdEnum);
        data.setDeviceType(deviceTypeEnum);
        data.setAddress("686");
        EventManager.getInstance().publish(new DownProtocolEvent(data));
    }
}
