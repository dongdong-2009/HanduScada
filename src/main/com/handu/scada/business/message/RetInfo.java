package main.com.handu.scada.business.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by 柳梦 on 2018/02/08.
 */

@XmlRootElement(name = "RetInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class RetInfo {

    private String ErrorCode;
    private String ErrorInfo;
    private IdList IdList;


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

    public RetInfo.IdList getIdList() {
        return IdList;
    }

    public void setIdList(RetInfo.IdList idList) {
        IdList = idList;
    }

    public static class IdList {

        private List<MSGID> MSGID;

        public List<RetInfo.MSGID> getMSGID() {
            return MSGID;
        }

        public void setMSGID(List<RetInfo.MSGID> MSGID) {
            this.MSGID = MSGID;
        }
    }

    public static class MSGID {

        private String MSG_ID;

        public String getMSG_ID() {
            return MSG_ID;
        }

        public void setMSG_ID(String MSG_ID) {
            this.MSG_ID = MSG_ID;
        }
    }

}
