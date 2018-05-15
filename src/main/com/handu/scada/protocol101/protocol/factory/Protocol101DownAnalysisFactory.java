package main.com.handu.scada.protocol101.protocol.factory;

import main.com.handu.scada.protocol101.protocol.bean.BaseData;
import main.com.handu.scada.protocol101.switch101.analysis.IAnalysis;
import main.com.handu.scada.protocol101.switch101.analysis.impl.DownAnalysis;

/**
 * Created by 柳梦 on 2018/05/11.
 * 101
 */
public class Protocol101DownAnalysisFactory {

    private IAnalysis analysis;

    public static Protocol101DownAnalysisFactory getInstance() {
        return new Protocol101DownAnalysisFactory();
    }

    public BaseData analysis(BaseData data) throws Exception {
        switch (data.getDeviceType()) {
            case SWITCH:
                analysis = new DownAnalysis(data);
                break;
        }
        if (analysis != null) return analysis.analysis();
        return null;
    }
}
