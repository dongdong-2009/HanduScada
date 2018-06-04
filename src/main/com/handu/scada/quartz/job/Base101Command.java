package main.com.handu.scada.quartz.job;

import main.com.handu.scada.cache.MyCacheManager;
import main.com.handu.scada.db.bean.common.Device101CacheResult;
import main.com.handu.scada.enums.DeviceTypeEnum;
import main.com.handu.scada.event.EventManager;
import main.com.handu.scada.event.events.DownProtocolEvent;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.Protocol101CmdEnum;
import main.com.handu.scada.utils.StringsUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 柳梦 on 2018/05/22.
 */
public class Base101Command {


    /**
     * @param cmdEnums       操作类型
     * @param deviceTypeEnum 设备类型
     */
    protected void send(DeviceTypeEnum deviceTypeEnum, Protocol101CmdEnum... cmdEnums) {
        ConcurrentHashMap<String, Device101CacheResult> cacheMap = MyCacheManager.getDevice101CacheMap();
        if (cacheMap != null) {
            List<Device101CacheResult> list;
            synchronized (cacheMap) {
                list = cacheMap.entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .filter(e -> e.isOnline()
                                && StringsUtils.isNotEmpty(e.getDeviceAddress())
                                && e.getDeviceType() == deviceTypeEnum.getDeviceType()
                                && Objects.equals(e.getDeviceTableName().toLowerCase(), deviceTypeEnum.getTableName())
                        )
                        .collect(Collectors.toList());
            }
            if (list != null) {
                list.forEach(e -> {
                    for (Protocol101CmdEnum cmdEnum : cmdEnums) {
                        Protocol101Data data = new Protocol101Data();
                        data.setCmdType(cmdEnum);
                        data.setDeviceType(deviceTypeEnum);
                        data.setAddress(e.getDeviceAddress());
                        EventManager.getInstance().publish(new DownProtocolEvent(data));
                    }
                });
            }
        }
    }
}
