package main.com.handu.scada.protocol.protocol.GDW3761.A;

import main.com.handu.scada.protocol.protocol.GDW3761.A00Base;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/04/26.
 * 表A.6　数据格式05定义
 * 字节名称	字节格式
 * D7	D6	D5	D4	D3	D2	D1	D0
 * BYTE 1	BCD码个位	BCD码十分位
 * BYTE 2	S	BCD码百位	BCD码十位
 */
public class A05 extends A00Base {

    public static final int LENGTH = 2;


    @Override
    protected void encode() {
        double v = ((double) value) * 1000;
        v = v * 10;
        int valueInt = (int) (v % 10000);
        if (valueInt > 0) {
            code[1] = (byte) (((valueInt / 1000) << 4 + ((valueInt / 100)) % 10) & 0x7f);
            code[0] = HexUtils.intToBCDByte(valueInt);
        } else {
            valueInt = -valueInt;
            code[1] = HexUtils.intToByte(0x80 + (((valueInt / 1000) << 4 + ((valueInt / 100) % 10)) & 0x7f));
            code[0] = HexUtils.intToBCDByte(valueInt);
        }
    }

    @Override
    protected void decode() {
        boolean isBCD = true;
        for (int i = 0; i < LENGTH - 1; i++) {
            if (!HexUtils.isBCD(code[i])) {
                isBCD = false;
                break;
            }
        }
        if (isBCD) {
            double d = HexUtils.bcdByteToInt(code[0]) / 10d
                    + (code[1] & 0x70 >> 4) * 100 + (code[1] & 0x0f) * 10d;
            if ((code[1] & 0x80) != 0) {
                value = -d;
            } else {
                value = d;
            }
        } else {
            value = MaxDoubleVal;
        }
    }
}
