package main.com.handu.scada.protocol.base;

import main.com.handu.scada.utils.HexUtils;

public class BaseDICode {

    /**
     * 减33
     *
     * @param arr
     * @return
     */
    public static byte[] decrease33(byte[] arr) {
        byte[] temp = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = HexUtils.decrease33(arr[i]);
        }
        return temp;
    }


    /**
     * @param arrA 比较源对象
     * @param arrB 比较目的对象
     * @return
     */
    public static boolean equal(byte[] arrA, byte[] arrB) {
        if (arrA == null || arrB == null)
            return false;
        if (arrA.length != arrB.length)
            return false;
        if (arrA.getClass() != arrB.getClass())
            return false;

        boolean result = false;
        for (int i = 0; i < arrA.length; i++) {
            if (arrA[i] == arrB[i]) {
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * @param arrA       比较源对象
     * @param arrB       比较目的对象
     * @param decrease33 是否减0x33
     * @return
     */
    public static boolean equal(byte[] arrA, byte[] arrB, boolean decrease33) {
        if (decrease33) {
            arrA = decrease33(arrA);
        }
        return equal(arrA, arrB);
    }

    /**
     * 加33
     *
     * @param arr
     * @return
     */
    public static byte[] increase33(byte[] arr) {
        byte[] temp = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = HexUtils.increase33(arr[i]);
        }
        return temp;
    }
}
