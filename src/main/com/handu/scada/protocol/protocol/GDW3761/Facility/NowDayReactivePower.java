package main.com.handu.scada.protocol.protocol.GDW3761.Facility;

import main.com.handu.scada.protocol.protocol.GDW3761.A.A09;

/**
 * Created by 柳梦 on 2018/04/26.
 * <p>
 * /// <summary>
 * /// 当前无功功率对象，				仅仅会在上行数据中使用
 * /// 当前总无功功率示值				见附录A.9	kW
 * /// 当前A相无功有功功率示值			见附录A.9	kW
 * /// 当前B相无功有功功率示值			见附录A.9	kW
 * /// 当前C相无功有功功率示值			见附录A.9	kW
 * /// </summary>
 */
public class NowDayReactivePower {

    public A09 NowDayTotalReactivePower;
    public A09[] NowDayPhaseReactivePower;
    private A09 NowDayAPhaseReactivePower;
    private A09 NowDayBPhaseReactivePower;
    private A09 NowDayCPhaseReactivePower;

    public NowDayReactivePower(byte[] bytes) {

        NowDayPowerBase power = new NowDayPowerBase(bytes);
        NowDayTotalReactivePower = power.total;
        NowDayTotalReactivePower.setItemName("NowDayTotalReactivePower");
        NowDayTotalReactivePower.setName("当前总无功功率示值");
        NowDayTotalReactivePower.setUnit("KW");
        NowDayPhaseReactivePower = power.phase;
        NowDayAPhaseReactivePower = NowDayPhaseReactivePower[0];
        NowDayAPhaseReactivePower.setItemName("NowDayAPhaseReactivePower");
        NowDayAPhaseReactivePower.setName("当前A相无功有功功率示值");
        NowDayAPhaseReactivePower.setUnit("KW");
        NowDayBPhaseReactivePower = NowDayPhaseReactivePower[1];
        NowDayBPhaseReactivePower.setItemName("NowDayBPhaseReactivePower");
        NowDayBPhaseReactivePower.setName("当前B相无功有功功率示值");
        NowDayBPhaseReactivePower.setUnit("KW");
        NowDayCPhaseReactivePower = NowDayPhaseReactivePower[2];
        NowDayCPhaseReactivePower.setItemName("NowDayCPhaseReactivePower");
        NowDayCPhaseReactivePower.setName("当前C相无功有功功率示值");
        NowDayCPhaseReactivePower.setUnit("KW");
    }

    @Override
    public String toString() {
        return "NowDayReactivePower{" +
                "NowDayTotalReactivePower=" + NowDayTotalReactivePower +
                ", NowDayAPhaseReactivePower=" + NowDayAPhaseReactivePower +
                ", NowDayBPhaseReactivePower=" + NowDayBPhaseReactivePower +
                ", NowDayCPhaseReactivePower=" + NowDayCPhaseReactivePower +
                '}';
    }
}

