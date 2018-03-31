package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.TripEventRecord;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DltControlWord;

import java.util.List;

public abstract class BaseDataAnalyze {

    public String address;
    public DeviceCmdTypeEnum cmdType;
    public TripEventRecord tripEventRecord;
    public DltControlWord controlWord;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DeviceCmdTypeEnum getCmdType() {
        return cmdType;
    }

    public void setCmdType(DeviceCmdTypeEnum cmdType) {
        this.cmdType = cmdType;
    }

    public TripEventRecord getTripEventRecord() {
        return tripEventRecord;
    }

    public void setTripEventRecord(TripEventRecord tripEventRecord) {
        this.tripEventRecord = tripEventRecord;
    }

    public abstract List<DataAttr> getData(BaseIdentifyCodeDesc item);
}
