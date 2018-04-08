package main.com.handu.scada.business.dtu;

import java.util.Date;

/**
 * Created by 柳梦 on 2018/04/03.
 */
public class DtuStateResult {

    private DtuState state;
    private String dtuAddress;
    private String dtuId;
    private Date time;

    public DtuState getState() {
        return state;
    }

    public void setState(DtuState state) {
        this.state = state;
    }

    public String getDtuAddress() {
        return dtuAddress;
    }

    public void setDtuAddress(String dtuAddress) {
        this.dtuAddress = dtuAddress;
    }

    public DtuStateResult(DtuState state, String dtuAddress, Date time) {
        this.state = state;
        this.dtuAddress = dtuAddress;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDtuId() {
        return dtuId;
    }

    public void setDtuId(String dtuId) {
        this.dtuId = dtuId;
    }
}
