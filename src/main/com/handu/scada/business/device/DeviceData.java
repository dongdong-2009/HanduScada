package main.com.handu.scada.business.device;

import main.com.handu.scada.db.service.IDBService;

/**
 * Created by 柳梦 on 2018/04/03.
 * 设备数据
 */
public class DeviceData {

    private Object data;
    private Class<? extends IDBService> clazz;

    public DeviceData(Object data, Class<? extends IDBService> clazz) {
        this.data = data;
        this.clazz = clazz;
    }

    public Class<? extends IDBService> getClazz() {
        return clazz;
    }

    public Object getData() {
        return data;
    }
}
