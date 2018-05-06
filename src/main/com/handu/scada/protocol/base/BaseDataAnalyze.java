package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.protocol.DLT645.LP2007.DltControlWord;
import main.com.handu.scada.protocol.protocol.DLT645.TripEventRecord;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;

import java.util.List;

public abstract class BaseDataAnalyze {

    public List<DataAttr> dataAttrs;
    public String[] itemNames;
    public String[] itemCnNames;
    public String itemName;
    public String itemCnName;
    public Object value;
    public DataAttr dataAttr;

    public int BCD1;
    public int BCD2;
    public int BCD3;
    public int BCD4;
    public int BCD5;
    public int BCD6;

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
