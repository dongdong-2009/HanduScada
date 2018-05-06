package main.com.handu.scada.db.bean.common;

import main.com.handu.scada.cache.CacheCmdType;

/**
 * Created by 柳梦 on 2018/04/20.
 */
public class DtuCacheResult {

    private String dtuId;
    private String dtuAddress;
    private String dtuName;
    private int dtuPort;
    private boolean dtuIsOnline;
    private String terminalId;
    private int dtuType;
    private CacheCmdType cmdType = CacheCmdType.DEFAULT;


    public int getDtuType() {
        return dtuType;
    }

    public void setDtuType(int dtuType) {
        this.dtuType = dtuType;
    }

    public CacheCmdType getCmdType() {
        return cmdType;
    }

    public void setCmdType(CacheCmdType cmdType) {
        this.cmdType = cmdType;
    }

    public String getDtuId() {
        return dtuId;
    }

    public void setDtuId(String dtuId) {
        this.dtuId = dtuId;
    }

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    public String getDtuName() {
        return dtuName;
    }

    public void setDtuName(String dtuName) {
        this.dtuName = dtuName;
    }

    public int getDtuPort() {
        return dtuPort;
    }

    public void setDtuPort(int dtuPort) {
        this.dtuPort = dtuPort;
    }

    public boolean isDtuIsOnline() {
        return dtuIsOnline;
    }

    public void setDtuIsOnline(boolean dtuIsOnline) {
        this.dtuIsOnline = dtuIsOnline;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
