package main.com.handu.scada.utils;

import java.util.stream.IntStream;

/**
 * Created by 柳梦 on 2017/12/24.
 */
public class StringsUtils {

    /**
     * 左补齐
     *
     * @param object    原对象
     * @param strLength 总长度
     * @param padStr    补齐字符串
     * @return
     */
    public static String padLeft(Object object, int strLength, String padStr) {
        String str = String.valueOf(object);
        int strLen = str.length();
        StringBuffer sb;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append(padStr).append(str);//左补
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    /**
     * 右补齐
     *
     * @param object    原对象
     * @param strLength 总长度
     * @param padStr    补齐字符串
     * @return
     */
    public static String padRight(Object object, int strLength, String padStr) {
        String str = String.valueOf(object);
        int strLen = str.length();
        StringBuffer sb;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append(str).append(padStr);//右补
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }

    /**
     * 判断字符串为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0 || str == "";
    }

    /**
     * @param str
     * @return
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串不为空并且长度验证
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isNotEmptyAndValidLength(CharSequence str, int... length) {
        return isNotEmpty(str) && IntStream.of(length).anyMatch(x -> x == str.length());
    }
}
