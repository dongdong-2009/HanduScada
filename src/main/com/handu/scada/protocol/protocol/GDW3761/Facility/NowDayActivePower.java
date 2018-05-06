package main.com.handu.scada.protocol.protocol.GDW3761.Facility;

import main.com.handu.scada.protocol.protocol.GDW3761.A.A09;

/**
 * Created by 柳梦 on 2018/04/26.
 * <p>
 * /// <summary>
 * /// 当前有功功率对象，				仅仅会在上行数据中使用
 * /// 当前总有功功率示值				见附录A.9	kW
 * /// 当前A相有功有功功率示值			见附录A.9	kW
 * /// 当前B相有功有功功率示值			见附录A.9	kW
 * /// 当前C相有功有功功率示值			见附录A.9	kW
 * /// </summary>
 */
public class NowDayActivePower {

    public A09 NowDayTotalActivePower;
    public A09[] NowDayPhaseActivePower;
    private A09 NowDayAPhaseActivePower;
    private A09 NowDayBPhaseActivePower;
    private A09 NowDayCPhaseActivePower;

    public NowDayActivePower(byte[] bytes) {
        NowDayPowerBase power = new NowDayPowerBase(bytes);
        NowDayTotalActivePower = power.total;
        NowDayTotalActivePower.setItemName("NowDayTotalActivePower");
        NowDayTotalActivePower.setName("当前总有功功率示值");
        NowDayTotalActivePower.setUnit("KW");
        NowDayPhaseActivePower = power.phase;
        NowDayAPhaseActivePower = NowDayPhaseActivePower[0];
        NowDayAPhaseActivePower.setItemName("NowDayAPhaseActivePower");
        NowDayAPhaseActivePower.setName("当前A相有功有功功率示值");
        NowDayAPhaseActivePower.setUnit("KW");
        NowDayBPhaseActivePower = NowDayPhaseActivePower[1];
        NowDayBPhaseActivePower.setItemName("NowDayBPhaseActivePower");
        NowDayBPhaseActivePower.setName("当前B相有功有功功率示值");
        NowDayBPhaseActivePower.setUnit("KW");
        NowDayCPhaseActivePower = NowDayPhaseActivePower[2];
        NowDayCPhaseActivePower.setItemName("NowDayCPhaseActivePower");
        NowDayCPhaseActivePower.setName("当前C相有功有功功率示值");
        NowDayCPhaseActivePower.setUnit("KW");
    }

    @Override
    public String toString() {
        return "NowDayActivePower{" +
                "NowDayTotalActivePower=" + NowDayTotalActivePower +
                ", NowDayAPhaseActivePower=" + NowDayAPhaseActivePower +
                ", NowDayBPhaseActivePower=" + NowDayBPhaseActivePower +
                ", NowDayCPhaseActivePower=" + NowDayCPhaseActivePower +
                '}';
    }
}

