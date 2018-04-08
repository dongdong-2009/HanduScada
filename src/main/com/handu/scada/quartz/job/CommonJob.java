package main.com.handu.scada.quartz.job;

import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.common.DeviceDtuCacheResult;
import main.com.handu.scada.enums.TableEnum;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.enums.DeviceTypeEnum;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 柳梦 on 2018/01/17.
 */
public class CommonJob {

    /**
     * 是否启用
     */
    protected boolean enable = true;

    /**
     * @param type
     * @param cacheResult
     * @param typeEnum
     */
    private void send(MsgPriority type, DeviceDtuCacheResult cacheResult, DeviceCmdTypeEnum typeEnum, DeviceTypeEnum deviceTypeEnum) {
        DeviceTypeEnum deviceCmdType_ = DeviceTypeEnum.getDeviceTypeByTableName(cacheResult.getDeviceTableName().toLowerCase());
        //如果是漏保
        if (deviceCmdType_ != null && Objects.equals(deviceCmdType_.getTableName().toLowerCase(), TableEnum.Device_Rcd.getTableName().toLowerCase())) {
            deviceTypeEnum = DeviceTypeEnum.getDeviceTypeByLevel(cacheResult.getLevel());
            if (deviceTypeEnum != null) {
                ProtocolLayerData data = new ProtocolLayerData();
                //如果是广播校时则发送给每个漏保
                if (typeEnum == DeviceCmdTypeEnum.BroadcastTime) {
                    data.isWaitReceive = false;
                    data.DTUString = cacheResult.getDtuAddress();
                    data.deviceTypeEnum = deviceTypeEnum;
                    data.PostalAddress = cacheResult.getDeviceAddress();
                    data.HasDTUHead = true;
                    data.CmdType = typeEnum;
                    EventManager.getInstance().publish(new DownProtocolEvent(type, data), MsgPriority.HIGH);
                } else {
                    //如果是一级漏保就发送其他命令
                    if (cacheResult.getLevel() == DeviceTypeEnum.LP.getLevel()) {
                        data.isWaitReceive = true;
                        data.DTUString = cacheResult.getDtuAddress();
                        data.deviceTypeEnum = deviceTypeEnum;
                        data.PostalAddress = cacheResult.getDeviceAddress();
                        data.HasDTUHead = true;
                        data.CmdType = typeEnum;
                        EventManager.getInstance().publish(new DownProtocolEvent(type, data), MsgPriority.HIGH);
                    }
                }
            }
        } else {
            ProtocolLayerData data = new ProtocolLayerData();
            data.isWaitReceive = true;
            data.DTUString = cacheResult.getDtuAddress();
            data.deviceTypeEnum = deviceTypeEnum;
            data.PostalAddress = cacheResult.getDeviceAddress();
            data.HasDTUHead = true;
            data.CmdType = typeEnum;
            EventManager.getInstance().publish(new DownProtocolEvent(type, data), MsgPriority.HIGH);
        }
    }

    /**
     * @param cacheResult
     * @param typeEnum
     */
    protected void send(DeviceDtuCacheResult cacheResult, DeviceCmdTypeEnum typeEnum, DeviceTypeEnum deviceTypeEnum) {
        send(MsgPriority.LOW, cacheResult, typeEnum, deviceTypeEnum);
    }

    /**
     * @param type
     * @param typeEnums
     */
    protected void send(MsgPriority type, DeviceTypeEnum deviceTypeEnum, DeviceCmdTypeEnum... typeEnums) {
        ConcurrentHashMap<String, DeviceDtuCacheResult> cacheResults = MyCacheManager.getInstance().getDeviceDtuCache();
        if (cacheResults != null) {
            synchronized (cacheResults) {
                cacheResults.entrySet().forEach(c -> {
                    DeviceDtuCacheResult result = c.getValue();
                    if (Objects.equals(result.getDeviceTableName().toLowerCase(), deviceTypeEnum.getTableName())) {
                        for (DeviceCmdTypeEnum typeEnum : typeEnums) {
                            send(type, result, typeEnum, deviceTypeEnum);
                        }
                    }
                });
            }
        }
    }

    /**
     * @param typeEnums
     */
    protected void send(DeviceTypeEnum deviceTypeEnum, DeviceCmdTypeEnum... typeEnums) {
        send(MsgPriority.LOW, deviceTypeEnum, typeEnums);
    }
}
