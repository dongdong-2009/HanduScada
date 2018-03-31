package main.com.handu.scada.db.bean.common;

import java.io.Serializable;

/**
 * Created by 柳梦 on 2017/12/27.
 */
public class DeviceDtuCacheResult implements Serializable {

    private String dtuId;
    private String deviceId;
    private String deviceTableName;
    private String name;
    private String deviceAddress;
    private String terminalId;
    private int protocolType;
    private String dtuAddress;
    private String daId;
    private String level;
    private double ua;
    private double ub;
    private double uc;
    private double ia;
    private double ib;
    private double ic;
    private double io;

    public int getLevel() {
        try {
            return Integer.parseInt(level);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDtuId() {
        return dtuId;
    }

    public void setDtuId(String dtuId) {
        this.dtuId = dtuId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceTableName() {
        return deviceTableName;
    }

    public void setDeviceTableName(String deviceTableName) {
        this.deviceTableName = deviceTableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    public String getDaId() {
        return daId;
    }

    public void setDaId(String daId) {
        this.daId = daId;
    }

    public double getUa() {
        return ua;
    }

    public void setUa(double ua) {
        this.ua = ua;
    }

    public double getUb() {
        return ub;
    }

    public void setUb(double ub) {
        this.ub = ub;
    }

    public double getUc() {
        return uc;
    }

    public void setUc(double uc) {
        this.uc = uc;
    }

    public double getIa() {
        return ia;
    }

    public void setIa(double ia) {
        this.ia = ia;
    }

    public double getIb() {
        return ib;
    }

    public void setIb(double ib) {
        this.ib = ib;
    }

    public double getIc() {
        return ic;
    }

    public void setIc(double ic) {
        this.ic = ic;
    }

    public double getIo() {
        return io;
    }

    public void setIo(double io) {
        this.io = io;
    }
}
