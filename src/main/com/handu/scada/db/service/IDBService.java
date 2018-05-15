package main.com.handu.scada.db.service;

import main.com.handu.scada.business.device.DeviceData;
import main.com.handu.scada.db.mapper.common.CommonMapper;

import java.util.List;

/**
 * Created by 柳梦 on 2018/05/14.
 * 入库接口
 */
public interface IDBService {

    /**
     * 提交设备数据
     *
     * @param commonMapper
     * @param dataList
     * @return
     */
    int submit(CommonMapper commonMapper, List<DeviceData> dataList);

}
