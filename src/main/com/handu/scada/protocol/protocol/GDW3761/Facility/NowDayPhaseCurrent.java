package main.com.handu.scada.protocol.protocol.GDW3761.Facility;

import main.com.handu.scada.protocol.protocol.GDW3761.A.A25;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by 柳梦 on 2018/04/26.
 * /// <summary>
 * /// 当前A相电流数示值				见附录A.25	A
 * /// 当前B相电流数示值				见附录A.25	A
 * /// 当前C相电流数示值				见附录A.25	A
 * /// </summary>
 */
public class NowDayPhaseCurrent {

    private int phaseNumber = 3;
    public A25[] PhaseCurrent;
    public A25 NowDayAPhaseCurrent;
    private A25 NowDayBPhaseCurrent;
    private A25 NowDayCPhaseCurrent;
    public A25 NowDayResidualCurrent;
    public A25 UTPC;
    public int length;

    public NowDayPhaseCurrent(byte[] bytes) {
        if ((phaseNumber + 1) * A25.LENGTH == bytes.length) {
            PhaseCurrent = new A25[phaseNumber];
            byte[] buffer = new byte[A25.LENGTH];
            length = A25.LENGTH * (phaseNumber);

            for (int i = 0; i <= (phaseNumber - 1); i++) {
                System.arraycopy(bytes, (i) * A25.LENGTH, buffer, 0, A25.LENGTH);
                A25 a25 = new A25();
                a25.setDataOfAFN(buffer);
                PhaseCurrent[i] = a25;
            }
            System.arraycopy(bytes, phaseNumber * A25.LENGTH, buffer, 0, A25.LENGTH);
            A25 a25 = new A25();
            a25.setDataOfAFN(buffer);
            NowDayResidualCurrent = a25;
            NowDayResidualCurrent.setItemName("IO");
            NowDayResidualCurrent.setName("当前零序电流数示值");
            NowDayResidualCurrent.setUnit("A");
            NowDayAPhaseCurrent = PhaseCurrent[0];
            NowDayAPhaseCurrent.setItemName("IA");
            NowDayAPhaseCurrent.setName("当前A相电流数示值");
            NowDayAPhaseCurrent.setUnit("A");
            NowDayBPhaseCurrent = PhaseCurrent[1];
            NowDayBPhaseCurrent.setItemName("IB");
            NowDayBPhaseCurrent.setName("当前B相电流数示值");
            NowDayBPhaseCurrent.setUnit("A");
            NowDayCPhaseCurrent = PhaseCurrent[2];
            NowDayCPhaseCurrent.setItemName("IC");
            NowDayCPhaseCurrent.setName("当前C相电流数示值");
            NowDayCPhaseCurrent.setUnit("A");

            UTPC = new A25();
            UTPC.setItemName("UTPC");
            UTPC.setName("电流三项不平衡");
            UTPC.setUnit("%");
            double max = 0;
            double min = 0;
            double IA = (double) NowDayAPhaseCurrent.getValue();
            double IB = (double) NowDayBPhaseCurrent.getValue();
            double IC = (double) NowDayCPhaseCurrent.getValue();
            Optional<Double> maxT = Stream.of(IA, IB, IC).max((o1, o2) -> o1 < o2 ? -1 : Objects.equals(o1, o2) ? 0 : 1);
            if (maxT.isPresent()) {
                max = maxT.get();
                if (max == 0) {
                    UTPC.setValue(0d);
                    return;
                }
            }
            Optional<Double> minT = Stream.of(IA, IB, IC).max((o1, o2) -> o1 > o2 ? -1 : Objects.equals(o1, o2) ? 0 : 1);
            if (minT.isPresent()) {
                min = minT.get();
                if (min == 0) {
                    UTPC.setValue(100d);
                    return;
                }
            }
            double utpc = ((max - min) / max * 1d);
            UTPC.setValue(utpc);
        }
    }

    @Override
    public String toString() {
        return "NowDayPhaseCurrent{" +
                "NowDayAPhaseCurrent=" + NowDayAPhaseCurrent +
                ", NowDayBPhaseCurrent=" + NowDayBPhaseCurrent +
                ", NowDayCPhaseCurrent=" + NowDayCPhaseCurrent +
                ", NowDayResidualCurrent=" + NowDayResidualCurrent +
                '}';
    }
}
