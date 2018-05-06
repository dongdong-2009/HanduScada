package main.com.handu.scada.protocol.protocol.GDW3761.A;

import main.com.handu.scada.protocol.protocol.GDW3761.A00Base;
import main.com.handu.scada.utils.DateUtils;
import main.com.handu.scada.utils.HexUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 柳梦 on 2018/04/26.
 * 字节名称	字节格式
 * D7	D6	D5	D4	D3	D2	D1	D0
 * 分	BCD码十位	    BCD码个位
 * 时	BCD码十位	    BCD码个位
 * 日	BCD码十位	    BCD码个位
 * 月	BCD码十位	    BCD码个位
 * 年	BCD码十位	    BCD码个位
 */
public class A15 extends A00Base {

    public static final int LENGTH = 5;

    @Override

    protected void encode() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) value);
        code[0] = HexUtils.intToBCDByte(calendar.get(Calendar.MINUTE));
        code[1] = HexUtils.intToBCDByte(calendar.get(Calendar.HOUR));
        code[2] = HexUtils.intToBCDByte(calendar.get(Calendar.DAY_OF_MONTH));
        code[3] = HexUtils.intToBCDByte(calendar.get(Calendar.MONTH));
        code[4] = HexUtils.intToBCDByte(calendar.get(Calendar.YEAR));
    }

    @Override
    protected void decode() {
        Calendar calendar = Calendar.getInstance();
        int minute = HexUtils.bcdByteToInt(code[0]);
        int hour = HexUtils.bcdByteToInt(code[1]);
        int day = HexUtils.bcdByteToInt(code[2]);
        int month = HexUtils.bcdByteToInt(code[3]);
        int year = HexUtils.bcdByteToInt(code[4]) + 2000;
        calendar.set(year, month - 1, day, hour, minute, 0);
        value = DateUtils.dateToStr(calendar.getTime());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
