package main.com.handu.scada.protocol101.faultRecord;

import java.util.List;

/**
 * Created by 柳梦 on 2018/06/01.
 */

public class FaultRecordInfo {

    private String deviceAddress;
    private List<FaultRecordFile> files;

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public List<FaultRecordFile> getFiles() {
        return files;
    }

    public FaultRecordInfo(String deviceAddress, List<FaultRecordFile> files) {
        this.deviceAddress = deviceAddress;
        this.files = files;
    }
}