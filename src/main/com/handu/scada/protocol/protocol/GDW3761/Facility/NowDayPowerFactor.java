package main.com.handu.scada.protocol.protocol.GDW3761.Facility;

import main.com.handu.scada.protocol.protocol.GDW3761.A.A05;

import java.util.Arrays;

/**
 * Created by 柳梦 on 2018/04/26.
 * /// <summary>
 * /// 当前功率因数对象，				仅仅会在上行数据中使用
 * /// 当前总功率因数示值				    见附录A.5	%
 * /// 当前A相功率因数示值				见附录A.5	%
 * /// 当前B相功率因数示值				见附录A.5	%
 * /// 当前C相功率因数示值				见附录A.5	%
 * /// </summary>
 */
public class NowDayPowerFactor {

    private int phaseNumber = 3;
    public A05 NowDayTotalPowerFactor;
    public A05[] NowDayPhasePowerFactor;
    private A05 NowDayAPhasePowerFactor;
    private A05 NowDayBPhasePowerFactor;
    private A05 NowDayCPhasePowerFactor;

    public int length;

    public NowDayPowerFactor(byte[] bytes) {

        if ((phaseNumber + 1) * A05.LENGTH == bytes.length) {
            byte[] buffer = Arrays.copyOf(bytes, A05.LENGTH);
            NowDayTotalPowerFactor = new A05();
            NowDayTotalPowerFactor.setDataOfAFN(buffer);
            NowDayTotalPowerFactor.setItemName("NowDayTotalPowerFactor");
            NowDayTotalPowerFactor.setName("当前总功率因数示值");
            NowDayTotalPowerFactor.setUnit("%");
            NowDayPhasePowerFactor = new A05[phaseNumber];
            length = A05.LENGTH * (phaseNumber + 1);
            for (int i = 0; i <= (phaseNumber - 1); i++) {
                System.arraycopy(bytes, (i + 1) * A05.LENGTH, buffer, 0, A05.LENGTH);
                A05 a05 = new A05();
                a05.setDataOfAFN(buffer);
                NowDayPhasePowerFactor[i] = a05;
            }

            NowDayAPhasePowerFactor = NowDayPhasePowerFactor[0];
            NowDayAPhasePowerFactor.setItemName("NowDayAPhasePowerFactor");
            NowDayAPhasePowerFactor.setName("当前A相功率因数示值");
            NowDayAPhasePowerFactor.setUnit("%");

            NowDayBPhasePowerFactor = NowDayPhasePowerFactor[1];
            NowDayBPhasePowerFactor.setItemName("NowDayBPhasePowerFactor");
            NowDayBPhasePowerFactor.setName("当前B相功率因数示值");
            NowDayBPhasePowerFactor.setUnit("%");

            NowDayCPhasePowerFactor = NowDayPhasePowerFactor[2];
            NowDayCPhasePowerFactor.setItemName("NowDayCPhasePowerFactor");
            NowDayCPhasePowerFactor.setName("当前C相功率因数示值");
            NowDayCPhasePowerFactor.setUnit("%");
        }
    }

    @Override
    public String toString() {
        return "NowDayPowerFactor{" +
                "NowDayTotalPowerFactor=" + NowDayTotalPowerFactor +
                ", NowDayAPhasePowerFactor=" + NowDayAPhasePowerFactor +
                ", NowDayBPhasePowerFactor=" + NowDayBPhasePowerFactor +
                ", NowDayCPhasePowerFactor=" + NowDayCPhasePowerFactor +
                '}';
    }
}
