package main.com.handu.scada.business.utpc;

import java.util.Date;

/**
 * Created by 柳梦 on 2018/01/02.
 */
public class AnalyzeRecordModel {

    private String deviceId;
    private String dtuAddress;

    //三相不平衡
    private double maxUtpc;
    //相
    private String phase;
    //低电压
    private int minU;
    //过载重载
    private double overload;

    private Date beginTime;
    private Date endTime;
    private int duration;

    public String getDeviceId() {
        return deviceId;
    }

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    public double getOverload() {
        return overload;
    }

    public void setOverload(double overload) {
        this.overload = overload;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getMaxUtpc() {
        return maxUtpc;
    }

    public void setMaxUtpc(double maxUtpc) {
        this.maxUtpc = maxUtpc;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public int getMinU() {
        return minU;
    }

    public void setMinU(int minU) {
        this.minU = minU;
    }
}
