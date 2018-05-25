package main.com.handu.scada.business.protocol101;

import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.quartz.job.Base101Command;

/**
 * Created by 柳梦 on 2018/05/22.
 */
public class Protocol101Command extends Base101Command {

    private static Protocol101Command singleton;

    public static Protocol101Command getInstance() {
        if (singleton == null) {
            synchronized (Protocol101Command.class) {
                if (singleton == null) {
                    singleton = new Protocol101Command();
                }
            }
        }
        return singleton;
    }

    public boolean sendByValue(int value) {
        switch (value) {
            case 1009:
                send(Protocol101CmdEnum.ALL_CALL, DeviceTypeEnum.SWITCH);
                return true;
            default:
                return false;
        }
    }
}
