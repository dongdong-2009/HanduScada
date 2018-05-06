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
    //漏保三项不平衡
    LP_UTPC,
    //台表三相不平衡
    HM_UTPC,
    //漏保低电压
    LP_LOW_VOLTAGE,
    //台表低电压
    HM_LOW_VOLTAGE,
    //台表重载过载
    HM_OVERLOAD,
    //告警
    ALARM,
    //集中器状态
    CONCENTRATOR_STATE,
    //控制字
    CONTROL_WORD,
    //二级漏保档案创建
    SECOND_LP_RECORD_CREATE,
    //台区总表afn0c25数据
    HM_AFN0C25,
}
