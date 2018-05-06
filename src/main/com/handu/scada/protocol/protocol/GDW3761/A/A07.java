package main.com.handu.scada.protocol.protocol.GDW3761.A;

import main.com.handu.scada.protocol.protocol.GDW3761.A00Base;
import main.com.handu.scada.utils.HexUtils;

/**
 * Created by 柳梦 on 2018/04/26.
 * 表A.8　数据格式07定义
 * 字节名称	字节格式
 * D7	D6	D5	D4	D3	D2	D1	D0
 * BYTE 1	BCD个位	BCD十分位
 * BYTE 2	BCD百位	BCD十位
 */
public class A07 extends A00Base {

    public static final int LENGTH = 2;

    @Override
    protected void encode() {
        double v = ((double) value) * 1000;
        code[1] = HexUtils.intToBCDByte((int) ((v * 10) / 100));
        code[0] = HexUtils.intToBCDByte((int) (v * 10));
    }

    @Override
    protected void decode() {
        boolean isBCD = true;
        for (int i = 0; i < LENGTH; i++) {
            if (!HexUtils.isBCD(code[i])) {
                isBCD = false;
                break;
            }
        }
        if (isBCD) {
            value = HexUtils.bcdByteToInt(code[0]) / 10d + HexUtils.bcdByteToInt(code[1]) * 10d;
        } else {
            value = MaxDoubleVal;
        }
    }
}
