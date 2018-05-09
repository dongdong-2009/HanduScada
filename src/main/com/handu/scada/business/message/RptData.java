package main.com.handu.scada.business.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by 柳梦 on 2018/02/26.
 */

@XmlRootElement(name = "RPTDATA")
@XmlAccessorType(XmlAccessType.FIELD)
public class RptData {

    private String ErrorCode;
    private String ErrorInfo;
    private String State;
    private String RetCount;
    private RPTList RPTList;

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        ErrorInfo = errorInfo;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getRetCount() {
        return RetCount;
    }

    public void setRetCount(String retCount) {
        RetCount = retCount;
    }

    public RptData.RPTList getRPTList() {
        return RPTList;
    }

    public void setRPTList(RptData.RPTList RPTList) {
        this.RPTList = RPTList;
    }

    public static class RPTList {

        List<RPTSubData> RPTSubData;

        public List<RptData.RPTSubData> getRPTSubData() {
            return RPTSubData;
        }

        public void setRPTSubData(List<RptData.RPTSubData> RPTSubData) {
            this.RPTSubData = RPTSubData;
        }
    }

    public static class RPTSubData {
        private String MSG_ID;
        private String BS_MSG_ID;
        private String STATUS_ID;
        private String ERR_ID;
        private String ERR_CONTENT;
        private String ACCTIME;
        private String PHONENUM;

        public String getMSG_ID() {
            return MSG_ID;
        }

        public void setMSG_ID(String MSG_ID) {
            this.MSG_ID = MSG_ID;
        }

        public String getBS_MSG_ID() {
            return BS_MSG_ID;
        }

        public void setBS_MSG_ID(String BS_MSG_ID) {
            this.BS_MSG_ID = BS_MSG_ID;
        }

        public String getSTATUS_ID() {
            return STATUS_ID;
        }

        public void setSTATUS_ID(String STATUS_ID) {
            this.STATUS_ID = STATUS_ID;
        }

        public String getERR_ID() {
            return ERR_ID;
        }

        public void setERR_ID(String ERR_ID) {
            this.ERR_ID = ERR_ID;
        }

        public String getERR_CONTENT() {
            return ERR_CONTENT;
        }

        public void setERR_CONTENT(String ERR_CONTENT) {
            this.ERR_CONTENT = ERR_CONTENT;
        }

        public String getACCTIME() {
            return ACCTIME;
        }

        public void setACCTIME(String ACCTIME) {
            this.ACCTIME = ACCTIME;
        }

        public String getPHONENUM() {
            return PHONENUM;
        }

        public void setPHONENUM(String PHONENUM) {
            this.PHONENUM = PHONENUM;
        }
    }
}
