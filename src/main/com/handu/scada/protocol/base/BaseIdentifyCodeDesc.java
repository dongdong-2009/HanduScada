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
    public byte[] Dicode;
    public byte[] data;
    public byte[] sourcedata;
    //一级漏保数据
    public List<DataAttr> values;
    //二级漏保数据
    public List<List<DataAttr>> secondValues;
    public BaseDataAnalyze dataanalyze;
    public AnswerControlCodeEnum controlCode;
    public DeviceCmdTypeEnum cmdtype;
    public CodeSysEnum codesystem;
    public Date dtime;

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
