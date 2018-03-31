package main.com.handu.scada.switch101.protocol.analysis;

import main.com.handu.scada.switch101.protocol.bean.BaseData;

/**
 * Created by 柳梦 on 2018/03/16.
 */
public interface IAnalysis {
    BaseData analysis(BaseData baseData);
}
