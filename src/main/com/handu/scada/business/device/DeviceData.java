package main.com.handu.scada.business.device;

/**
 * Created by 柳梦 on 2018/04/03.
 * 设备数据
 */
public class DeviceData {

    private Object data;
    private DataType dataType;

    public DeviceData(DataType dataType, Object data) {
        this.dataType = dataType;
        this.data = data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Object getData() {
        return data;
    }
}
