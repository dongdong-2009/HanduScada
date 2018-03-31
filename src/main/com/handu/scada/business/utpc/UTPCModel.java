package main.com.handu.scada.business.utpc;

import java.util.Date;

/**
 * Created by 柳梦 on 2018/01/02.
 */
public class UTPCModel {

    public static final double UTPC = 0.25;
    public static final double VOLTAGE = 220;
    public static final double VOLTAGE_RATE = 0.1;

    private String deviceId;
    private double maxUtpc;
    private Date beginTime;
    private Date endTime;
    private int duration;
    private String phase;

    public String getDeviceId() {
        return deviceId;
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

    private int minU;

}
