package main.com.handu.scada.protocol101.protocol.factory;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.protocol101.device101.analysis.IAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;
import main.com.handu.scada.protocol101.protocol.enums.Ti;

import java.lang.reflect.Constructor;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public class Protocol101UpAnalysisFactory {

    private IAnalysis analysis;
    private Protocol101Data baseData;

    private Protocol101UpAnalysisFactory(Protocol101Data baseData) throws InstantiationException, IllegalAccessException {
        this.baseData = baseData;
        init();
    }

    public Protocol101Data analysis() throws Exception {
        if (analysis != null && baseData != null) return analysis.analysis();
        return null;
    }

    private void init() throws IllegalAccessException, InstantiationException {
        try {
            if (baseData != null) {
                byte[] data = baseData.getCommandData();
                byte ti = data[7];
                Ti tiType = Ti.getTI(ti);
                if (tiType != null) {
                    baseData.setTI(tiType);
                    baseData.setDataType(tiType.getDataType());
                    if (tiType.getDataType().getClazz() != null) {
                        Class<?> aClass = tiType.getDataType().getClazz();
                        // 首先准备一个Class[]做为ctor的参数类型
                        Class[] type = new Class[]{Protocol101Data.class};
                        // 调用pType为变量的getConstructor()，获得一个专属ctor
                        Constructor ctor = aClass.getConstructor(type);
                        // 准备一个Object[] 做为ctor实参值
                        Object[] obj = new Object[]{baseData};
                        // 调用上述专属ctor的newInstance()
                        analysis = (IAnalysis) ctor.newInstance(obj);
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    public static Protocol101UpAnalysisFactory getInstance(Protocol101Data baseData) throws IllegalAccessException, InstantiationException {
        return new Protocol101UpAnalysisFactory(baseData);
    }
}
