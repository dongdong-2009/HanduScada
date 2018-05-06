package main.com.handu.scada.protocol.protocol.GDW3761.A;

import main.com.handu.scada.protocol.protocol.GDW3761.A00Base;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/04/26.
 * 表A.26　数据格式25定义
 * 字节名称	字节格式
 * D7	D6	D5	D4	D3	D2	D1	D0
 * BYTE 1	BCD码百分位	BCD码千分位
 * BYTE 2	BCD码个位	BCD码十分位
 * BYTE 3	S	BCD码百位	BCD码十位
 */
public class A25 extends A00Base {

    public static final int LENGTH = 3;

    @Override
    protected void encode() {
        double v = ((double) value) * 1000;
        int valueInt = (int) (v % 1000000);
        if (valueInt > 0) {
            code[2] = (byte) (((valueInt / 100000) << 4 + ((valueInt / 10000)) % 10) & 0x7f);
            code[1] = HexUtils.intToBCDByte((valueInt / 100));
            code[0] = HexUtils.intToBCDByte(valueInt);
        } else {
            valueInt = -valueInt;
            code[2] = (byte) (0x80 + (((valueInt / 100000) << 4 + ((valueInt / 10000) % 10)) & 0x7f));
            code[1] = HexUtils.intToBCDByte((valueInt / 100));
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
            double d = HexUtils.bcdByteToInt(code[0]) / 1000d
                    + HexUtils.bcdByteToInt(code[1]) / 10d
                    + (code[2] & 0x70 >> 4) * 100 + (code[2] & 0x0f) * 10;
            if ((code[2] & 0x80) != 0) {
                value = -d;
            } else {
                value = d;
            }
        } else {
            value = MaxDoubleVal;
        }
    }
}
