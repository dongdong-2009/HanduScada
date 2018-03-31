package main.com.handu.scada.switch101.protocol.enums;

import main.com.handu.scada.switch101.protocol.analysis.impl.SOEAnalysis;
import main.com.handu.scada.switch101.protocol.analysis.impl.YCAnalysis;
import main.com.handu.scada.switch101.protocol.analysis.impl.YKAnalysis;
import main.com.handu.scada.switch101.protocol.analysis.impl.YXAnalysis;

/**
 * Created by 柳梦 on 2018/03/15.
 */
public enum DataType {
    NONE,
    YX("遥信", "RemoteSignal.json", YXAnalysis.class),
    YC("遥测", "RemoteMeasurement.json", YCAnalysis.class),
    YK("遥控", "", YKAnalysis.class),
    SOE("SOE事件", "SOE.json", SOEAnalysis.class);

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
