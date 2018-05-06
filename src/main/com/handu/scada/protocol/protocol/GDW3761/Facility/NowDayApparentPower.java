package main.com.handu.scada.protocol.protocol.GDW3761.Facility;

import main.com.handu.scada.protocol.protocol.GDW3761.A.A09;

/**
 * Created by 柳梦 on 2018/04/26.
 * /// <summary>
 * /// 当前视在功率对象，				仅仅会在上行数据中使用
 * /// 当前视在功功率示值				见附录A.9	kVA
 * /// 当前A相视在功率示值				见附录A.9	kVA
 * /// 当前B相视在功率示值				见附录A.9	kVA
 * /// 当前C相视在功率示值				见附录A.9	kVA
 * /// </summary>
 */
public class NowDayApparentPower {

    private int phaseNumber = 3;
    public A09 NowDayTotalApparentPower;
    public A09[] NowDayPhaseApparentPower;
    private A09 NowDayAPhaseApparentPower;
    private A09 NowDayBPhaseApparentPower;
    private A09 NowDayCPhaseApparentPower;
    public int length;

    public NowDayApparentPower(byte[] bytes) {
        NowDayPowerBase power = new NowDayPowerBase(bytes);
        phaseNumber = power.phaseNumber;
        NowDayTotalApparentPower = power.total;

        NowDayTotalApparentPower.setItemName("NowDayTotalApparentPower");
        NowDayTotalApparentPower.setName("当前视在总功功率示值");
        NowDayTotalApparentPower.setUnit("KVA");
        NowDayPhaseApparentPower = power.phase;
        NowDayAPhaseApparentPower = NowDayPhaseApparentPower[0];
        NowDayAPhaseApparentPower.setItemName("NowDayAPhaseApparentPower");
        NowDayAPhaseApparentPower.setName("当前A相视在功率示值");
        NowDayAPhaseApparentPower.setUnit("KVA");
        NowDayBPhaseApparentPower = NowDayPhaseApparentPower[1];
        NowDayBPhaseApparentPower.setItemName("NowDayBPhaseApparentPower");
        NowDayBPhaseApparentPower.setName("当前B相视在功率示值");
        NowDayBPhaseApparentPower.setUnit("KVA");
        NowDayCPhaseApparentPower = NowDayPhaseApparentPower[2];
        NowDayCPhaseApparentPower.setItemName("NowDayCPhaseApparentPower");
        NowDayCPhaseApparentPower.setName("当前C相视在功率示值");
        NowDayCPhaseApparentPower.setUnit("KVA");
    }

    @Override
    public String toString() {
        return "NowDayApparentPower{" +
                "NowDayTotalApparentPower=" + NowDayTotalApparentPower +
                ", NowDayAPhaseApparentPower=" + NowDayAPhaseApparentPower +
                ", NowDayBPhaseApparentPower=" + NowDayBPhaseApparentPower +
                ", NowDayCPhaseApparentPower=" + NowDayCPhaseApparentPower +
                '}';
    }
}
