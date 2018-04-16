package main.com.handu.scada.business.device;

/**
 * Created by 柳梦 on 2018/04/03.
 */
public enum DataType {
    //遥信实时数据
    YX_REAL,
    //遥测实时数据
    YC_REAL,
    //遥测历史剩余
    YC_HISTORY,
    //遥信历史数据
    YX_HISTORY,
    //三项不平衡
    UTPC,
    //低电压
    LOW_VOLTAGE,
    //告警
    ALARM,
    //集中器状态
    CONCENTRATOR_STATE,
    //控制字
    CONTROL_WORD
}
