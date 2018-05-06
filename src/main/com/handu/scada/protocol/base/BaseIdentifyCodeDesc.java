package main.com.handu.scada.protocol.base;

import main.com.handu.scada.protocol.enums.AnswerControlCodeEnum;
import main.com.handu.scada.protocol.enums.CodeSysEnum;
import main.com.handu.scada.protocol.enums.DeviceCmdTypeEnum;
import main.com.handu.scada.protocol.protocol.Data.DataAttr;

import java.util.Date;
import java.util.List;

public abstract class BaseIdentifyCodeDesc {

    public boolean result = true;
    public String name;
    public String unit;
    public int length;
    public String address;
    public byte[] DiCode;
    public byte[] data;
    public byte[] sourceData;
    //一级漏保数据
    public List<DataAttr> values;
    //二级漏保数据
    public List<List<DataAttr>> secondValues;
    public BaseDataAnalyze dataAnalyze;
    public AnswerControlCodeEnum controlCode;
    public DeviceCmdTypeEnum cmdType;
    public CodeSysEnum codeSystem;
    public Date dTime;

    /**
     * 开始解析
     *
     * @return
     */
    public abstract boolean parse();

    /**
     * 是否正常应答
     *
     * @return
     */
    public abstract boolean isCorrectAnswer(byte controlCode);

}
