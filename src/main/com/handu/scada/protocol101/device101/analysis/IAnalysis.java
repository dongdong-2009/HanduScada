package main.com.handu.scada.protocol101.device101.analysis;

import main.com.handu.scada.protocol101.protocol.bean.Protocol101Data;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public interface IAnalysis {

    default Protocol101Data analysis() {
        return null;
    }

    default boolean isAnalysis() {
        return true;
    }
}
