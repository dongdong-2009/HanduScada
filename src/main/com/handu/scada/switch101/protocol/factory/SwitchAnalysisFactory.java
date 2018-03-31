package main.com.handu.scada.switch101.protocol.factory;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.switch101.protocol.analysis.IAnalysis;
import main.com.handu.scada.switch101.protocol.bean.BaseData;
import main.com.handu.scada.switch101.protocol.enums.Ti;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class SwitchAnalysisFactory {

    private IAnalysis analysis;
    private BaseData baseData;

    private SwitchAnalysisFactory(BaseData baseData) throws InstantiationException, IllegalAccessException {
        this.baseData = baseData;
        init();
    }

    public BaseData analysis() {
        if (analysis != null && baseData != null) return analysis.analysis(baseData);
        return null;
    }

    private void init() throws IllegalAccessException, InstantiationException {
        if (baseData != null) {
            byte[] data = baseData.getCommandData();
            byte ti = data[7];
            Ti tiType = Ti.getTI(ti);
            if (tiType != null) {
                baseData.setTI(tiType);
                baseData.setDataType(tiType.getDataType());
                if (tiType.getDataType().getClazz() != null) {
                    try {
                        Class<?> aClass = tiType.getDataType().getClazz();
                        // 首先准备一个Class[]做为ctor的参数类型
                        Class[] type = new Class[]{BaseData.class};
                        // 调用pType为变量的getConstructor()，获得一个专属ctor
                        Constructor ctor = aClass.getConstructor(type);
                        // 准备一个Object[] 做为ctor实参值
                        Object[] obj = new Object[]{baseData};
                        // 调用上述专属ctor的newInstance()
                        analysis = (IAnalysis) ctor.newInstance(obj);
                    } catch (NoSuchMethodException | InvocationTargetException e) {
                        ExceptionHandler.handle(e);
                    }
                }
            }
        }
    }

    public static SwitchAnalysisFactory getInstance(BaseData baseData) throws IllegalAccessException, InstantiationException {
        return new SwitchAnalysisFactory(baseData);
    }
}
