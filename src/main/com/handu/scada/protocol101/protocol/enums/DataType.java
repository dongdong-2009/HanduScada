package main.com.handu.scada.protocol101.protocol.enums;

import main.com.handu.scada.protocol101.device101.analysis.up.impl.*;

/**
 * Created by 柳梦 on 2018/03/15.
 */
public enum DataType {
    NONE,
    CV("读参数和定值", null, ConstantValueUpAnalysis.class),
    FILE("故障录波文件读取", null, FileServiceUpAnalysis.class),
    YX("遥信", "RemoteSignal.json", YXUpAnalysis.class),
    YC("遥测", "RemoteMeasurement.json", YCUpAnalysis.class),
    YK("遥控", "", YKUpAnalysis.class),
    SOE("SOE事件", "SOE.json", SOEUpAnalysis.class);

    private String name;
    private String jsonName;
    private Class clazz;

    DataType(String name, String jsonName, Class clazz) {
        this.name = name;
        this.jsonName = jsonName;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    DataType() {
    }

    public String getName() {
        return name;
    }

    public String getJsonName() {
        return jsonName;
    }
}
