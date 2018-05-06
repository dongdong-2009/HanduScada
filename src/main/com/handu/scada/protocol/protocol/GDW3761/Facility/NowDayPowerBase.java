package main.com.handu.scada.protocol.protocol.GDW3761.Facility;

import main.com.handu.scada.protocol.protocol.GDW3761.A.A09;

import java.util.Arrays;

/**
 * Created by 柳梦 on 2018/04/26.
 * /// <summary>
 * /// 当前功率对象，					仅仅会在上行数据中使用
 * /// 当前总功率示值					见附录A.9	kW
 * /// 当前A相功率示值					见附录A.9	kW
 * /// 当前B相功率示值					见附录A.9	kW
 * /// 当前C相功率示值					见附录A.9	kW
 * /// </summary>
 */
public class NowDayPowerBase {

    public final int phaseNumber = 3;
    public A09 total;
    public A09[] phase;
    public int length;


    public NowDayPowerBase(byte[] bytes) {
        if ((phaseNumber + 1) * A09.LENGTH == bytes.length) {
            byte[] buffer = Arrays.copyOf(bytes, A09.LENGTH);
            total = new A09();
            total.setDataOfAFN(buffer);
            phase = new A09[phaseNumber];
            length = A09.LENGTH * (phaseNumber + 1);
            for (int i = 0; i <= (phaseNumber - 1); i++) {
                System.arraycopy(bytes, (i + 1) * A09.LENGTH, buffer, 0, A09.LENGTH);
                A09 a09 = new A09();
                a09.setDataOfAFN(buffer);
                phase[i] = a09;
            }
        }
    }
}
