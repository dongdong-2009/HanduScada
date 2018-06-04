package main.com.handu.scada.protocol101.protocol.factory;

import main.com.handu.scada.protocol101.device101.analysis.IAnalysis;
import main.com.handu.scada.protocol101.device101.analysis.down.impl.DownAnalysis;
import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;

/**
 * Created by 柳梦 on 2018/05/11.
 * 101
 */
public class Protocol101DownAnalysisFactory {

    public static Protocol101DownAnalysisFactory getInstance() {
        return new Protocol101DownAnalysisFactory();
    }

    public Protocol101Data analysis(Protocol101Data data) throws Exception {
        if (data.getDeviceType() == null) return null;
        switch (data.getDeviceType()) {
            default:
                IAnalysis analysis = new DownAnalysis(data);
                return analysis.analysis();
        }
    }
}
