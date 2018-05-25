package main.com.handu.scada.protocol101.protocol.factory;

import main.com.handu.scada.protocol101.protocol.bean.Protocol101BaseData;
import main.com.handu.scada.protocol101.device101.analysis.IAnalysis;
import main.com.handu.scada.protocol101.device101.analysis.impl.DownAnalysis;

/**
 * Created by 柳梦 on 2018/05/11.
 * 101
 */
public class Protocol101DownAnalysisFactory {

    private IAnalysis analysis;

    public static Protocol101DownAnalysisFactory getInstance() {
        return new Protocol101DownAnalysisFactory();
    }

    public Protocol101BaseData analysis(Protocol101BaseData data) throws Exception {
        if (data.getDeviceType() == null) return null;
        switch (data.getDeviceType()) {
            case SWITCH:
                analysis = new DownAnalysis(data);
                break;
        }
        if (analysis != null) return analysis.analysis();
        return null;
    }
}
