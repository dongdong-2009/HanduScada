package main.com.handu.scada.quartz.job;

import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.common.DeviceCacheResult;
import main.com.handu.scada.db.bean.common.DtuCacheResult;
import main.com.handu.scada.enums.DeviceGroup;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.netty.client.dtu.MsgPriority;
import main.com.handu.scada.protocol.base.ProtocolLayerData;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.business.dtu.DtuCommand;
import main.com.handu.scada.utils.LogUtils;
import main.com.handu.scada.utils.StringsUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/01/17.
 */
public class BaseDtuCommand {

    /**
     * 打印并存储任务执行情况
     *
     * @param msg
     */
    protected void printTask(String msg) {
        LogUtils.printTask(msg);
    }

    /**
     * @param type
     * @param device
     * @param cmdType
     */
    private void send(MsgPriority type, DeviceCacheResult device, DeviceCmdTypeEnum cmdType, DeviceTypeEnum deviceType) {
        if (deviceType == null) return;
        //如果是漏保2007或者1997
        if (deviceType.getGroup() == DeviceGroup.LP2007 || deviceType.getGroup() == DeviceGroup.LP1997) {
            ProtocolLayerData data = new ProtocolLayerData();
            //如果是一级漏保发送所有命令
            if (device.getDeviceLevel() == 1) {
                data.isWaitReceive = true;
                data.DTUString = device.getDtuAddress();
                data.deviceTypeEnum = deviceType;
                data.PostalAddress = device.getDeviceAddress();
                data.HasDTUHead = true;
                data.CmdType = cmdType;
                EventManager.getInstance().publish(new DownProtocolEvent(type, data), MsgPriority.NORMAL);
            }
            //如果是其他漏保就只发送广播校时
            else {
                //如果是广播校时才发给其他漏保
                if (cmdType == DeviceCmdTypeEnum.BroadcastTime) {
                    data.isWaitReceive = false;
                    data.DTUString = device.getDtuAddress();
                    data.deviceTypeEnum = deviceType;
                    data.PostalAddress = device.getDeviceAddress();
                    data.HasDTUHead = true;
                    data.CmdType = cmdType;
                    EventManager.getInstance().publish(new DownProtocolEvent(type, data), MsgPriority.NORMAL);
                }
            }
        }
        //其他设备
        else {
            ProtocolLayerData data = new ProtocolLayerData();
            data.isWaitReceive = true;
            data.DTUString = device.getDtuAddress();
            data.deviceTypeEnum = deviceType;
            data.PostalAddress = device.getDeviceAddress();
            data.HasDTUHead = true;
            data.CmdType = cmdType;
            EventManager.getInstance().publish(new DownProtocolEvent(type, data), MsgPriority.NORMAL);
        }
    }

    /**
     * @param type
     * @param typeEnums
     */
    protected void send(MsgPriority type, DeviceTypeEnum deviceType, DeviceCmdTypeEnum... typeEnums) {
        ConcurrentHashMap<String, DeviceCacheResult> cacheResults = MyCacheManager.getDeviceCacheMap();
        if (cacheResults != null) {
            List<DeviceCacheResult> list;
            synchronized (cacheResults) {
                list = cacheResults
                        .entrySet()
                        .stream()
                        .filter(entry -> Objects.equals(entry.getValue().getDeviceTableName().toLowerCase(), deviceType.getTableName()))
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());
            }
            if (list != null) {
                list = list.stream()
                        //过滤掉地址不合格，dtu不在线，设备类型不符的后再发送
                        .filter(e -> MyCacheManager.isDtuOnline(e.getDtuAddress())
                                && StringsUtils.isNotEmptyAndValidLength(e.getDtuAddress(), 8)
                                && StringsUtils.isNotEmptyAndValidLength(e.getDeviceAddress(), 12, 1)
                                && e.getDeviceType() == deviceType.getDeviceType())
                        .collect(Collectors.toList());
                list.forEach(result -> {
                    for (DeviceCmdTypeEnum typeEnum : typeEnums) {
                        send(type, result, typeEnum, deviceType);
                    }
                });
            }
        }
    }

    /**
     * @param cmdTypes
     */
    protected void send(DeviceTypeEnum deviceType, DeviceCmdTypeEnum... cmdTypes) {
        send(MsgPriority.LOW, deviceType, cmdTypes);
    }

    /**
     * 发送给4G模块
     */
    protected void sendTo4g(DeviceCmdTypeEnum cmdType) {
        ConcurrentHashMap<String, DtuCacheResult> dtuCache = MyCacheManager.getDtuCacheMap();
        if (dtuCache != null) {
            List<DtuCacheResult> dtuAddresses;
            synchronized (dtuCache) {
                dtuAddresses = dtuCache
                        .entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());
            }
            if (dtuAddresses != null) {
                dtuAddresses
                        .stream()
                        .filter(e -> e.isDtuIsOnline() && e.getDtuType() == DeviceTypeEnum.DTU4G.getDeviceType())
                        .forEach(dtu -> DtuCommand.getInstance().sendTo4g(cmdType, dtu.getDtuAddress()));
            }
        }
    }

    /**
     * 发送给DTU模块
     */
    protected void sendToDtu(DeviceCmdTypeEnum cmdType) {
        ConcurrentHashMap<String, DtuCacheResult> dtuCache = MyCacheManager.getDtuCacheMap();
        if (dtuCache != null) {
            List<DtuCacheResult> dtuAddresses;
            synchronized (dtuCache) {
                dtuAddresses = dtuCache
                        .entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());
            }
            if (dtuAddresses != null) {
                dtuAddresses
                        .stream()
                        .filter(e -> e.isDtuIsOnline() && e.getDtuType() == DeviceTypeEnum.DTU.getDeviceType())
                        .forEach(dtu -> DtuCommand.getInstance().sendTo4g(cmdType, dtu.getDtuAddress()));
            }
        }
    }
}
