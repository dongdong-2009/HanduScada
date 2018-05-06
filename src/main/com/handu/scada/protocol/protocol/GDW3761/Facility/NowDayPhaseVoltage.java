package main.com.handu.scada.protocol.protocol.GDW3761.Facility;

import main.com.handu.scada.protocol.protocol.GDW3761.A.A07;
import main.com.handu.scada.protocol.protocol.GDW3761.A.A25;

/**
 * Created by 柳梦 on 2018/04/26.
 * /// <summary>
 * /// 当前A相电压数示值				见附录A.07	V
 * /// 当前B相电压数示值				见附录A.07	V
 * /// 当前C相电压数示值				见附录A.07	V
 * /// </summary>
 */
public class NowDayPhaseVoltage {

    private int phaseNumber = 3;
    public A07[] PhaseVoltage;
    private A07 NowDayAPhaseVoltage;
    private A07 NowDayBPhaseVoltage;
    private A07 NowDayCPhaseVoltage;

    public int length;

    public NowDayPhaseVoltage(byte[] bytes) {
        if (phaseNumber * A07.LENGTH == bytes.length) {
            byte[] buffer = new byte[A07.LENGTH];
            PhaseVoltage = new A07[phaseNumber];
            length = A25.LENGTH * (phaseNumber);
            for (int i = 0; i <= (phaseNumber - 1); i++) {
                System.arraycopy(bytes, (i) * A07.LENGTH, buffer, 0, A07.LENGTH);
                A07 a07 = new A07();
                a07.setDataOfAFN(buffer);
                PhaseVoltage[i] = a07;
            }
            NowDayAPhaseVoltage = PhaseVoltage[0];
            NowDayAPhaseVoltage.setItemName("UA");
            NowDayAPhaseVoltage.setName("当前A相电压数示值");
            NowDayAPhaseVoltage.setUnit("V");
            NowDayBPhaseVoltage = PhaseVoltage[1];
            NowDayBPhaseVoltage.setItemName("UB");
            NowDayBPhaseVoltage.setName("当前B相电压数示值");
            NowDayBPhaseVoltage.setUnit("V");
            NowDayCPhaseVoltage = PhaseVoltage[2];
            NowDayCPhaseVoltage.setItemName("UC");
            NowDayCPhaseVoltage.setName("当前C相电压数示值");
            NowDayCPhaseVoltage.setUnit("V");
        }
    }

    @Override
    public String toString() {
        return "NowDayPhaseVoltage{" +
                "NowDayAPhaseVoltage=" + NowDayAPhaseVoltage +
                ", NowDayBPhaseVoltage=" + NowDayBPhaseVoltage +
                ", NowDayCPhaseVoltage=" + NowDayCPhaseVoltage +
                '}';
    }
}
