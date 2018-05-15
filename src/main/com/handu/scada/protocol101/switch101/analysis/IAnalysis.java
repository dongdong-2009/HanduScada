package main.com.handu.scada.protocol101.switch101.analysis;

import main.com.handu.scada.protocol101.protocol.bean.BaseData;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public interface IAnalysis {
    default BaseData analysis(BaseData baseData) throws Exception {
        return null;
    }

    default BaseData analysis() throws Exception {
        return null;
    }
}
