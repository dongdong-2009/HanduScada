package main.com.handu.scada.protocol.protocol.DLT645.LP1997;

import main.com.handu.scada.protocol.base.BaseIdentifyCodeDesc;
import main.com.handu.scada.protocol.enums.AnswerControlCodeEnum;
import main.com.handu.scada.protocol.enums.CodeSysEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;

/**
 * Created by 柳梦 on 2018/04/24.
 */
public class IdentifyCodeDesc extends BaseIdentifyCodeDesc {

    public IdentifyCodeDesc() {
        dataAnalyze = new DataAnalyze();
    }

    @Override
    public boolean parse() {
        if (controlCode != null && (controlCode.getValue() == AnswerControlCodeEnum.ReadDataQLLP1997.getValue()
                || this.controlCode == AnswerControlCodeEnum.WriteDataQLLP1997)) {
            if (DICodeLP1997.equal(DiCode, DICodeLP1997.VoltageCurrentAndResidualCurrent, true)) {
                this.unit = "V,A,mA";
                this.length = 15;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.VoltageCurrentAndResidualCurrent;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.ReadClock, true)) {
                this.unit = "";
                this.length = 8;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.ReadClock;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.BroadcastTime, true)) {
                this.unit = "V,A,mA";
                this.length = 15;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.BroadcastTime;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.ReadPostalAddress, true)) {
                this.unit = "";
                this.length = 7;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.ReadPostalAddress;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.WritePostalAddress, true)) {
                this.unit = "V,A,mA";
                this.length = 15;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.WritePostalAddress;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.ExecuteON, true)) {
                this.unit = "V,A,mA";
                this.length = 15;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.ExecuteON;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.ExecuteOFF, true)) {
                this.unit = "V,A,mA";
                this.length = 15;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.ExecuteOFF;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.SwitchTrip, true)) {
                this.unit = "V,A,mA";
                this.length = 15;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.SwitchTrip;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.RunState, true)) {
                this.unit = "";
                this.length = 7;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.RunState;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.ReadControlWordParameterModule, true)) {
                this.unit = "";
                this.length = this.data.length;
                this.codeSystem = CodeSysEnum.NONE;
                this.cmdType = DeviceCmdTypeEnum.ReadControlWordParameterModule;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.WriteControlWordParameterModule, true)) {
                this.unit = "V,A,mA";
                this.length = 15;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.WriteControlWordParameterModule;
                this.values = dataAnalyze.getData(this);
            } else if (DICodeLP1997.equal(DiCode, DICodeLP1997.ProtectorTripEventRecord, true)) {
                this.unit = "年月日时分秒";
                this.length = 7;
                this.codeSystem = CodeSysEnum.BCD;
                this.cmdType = DeviceCmdTypeEnum.ProtectorTripEventRecord;
                this.values = dataAnalyze.getData(this);
            } else {
                result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public boolean isCorrectAnswer(byte controlCode) {
        //是否正常应答
        if (controlCode == (byte) 0x81 || controlCode == (byte) 0x84) {
            this.controlCode = AnswerControlCodeEnum.getControlCode(controlCode);
            if (this.controlCode != null) {
                byte a = (byte) this.controlCode.getValue();
                return a == controlCode;
            }
        }
        return false;
    }
}
