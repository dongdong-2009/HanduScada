package main.com.handu.scada.utils;

import java.math.BigInteger;

public class HexUtils {


    public static byte intToByte(int x) {
        return (byte) x;
    }

    public static int byteToInt(byte b) {
        //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }

    public static int bcdByteToInt(byte b) {
        int temp;
        if (b < 0) {
            temp = 256 + b;
        } else {
            temp = b;
        }
        return temp / 16 * 10 + temp % 16;
    }

    /// <summary>
    /// 将整形数转换为单字节BCD码，
    /// BCD码格式为
    /// |D7|D6|D5|D4 |D3|D2|D1|D0|
    /// |  BCD码10位 |  BCD码个位 |
    /// 比如输入的整形数值为73，返回的值byte值为0x73
    /// </summary>
    /// <param name="number">输入整形数必须小于等于99, 否则，仅仅转换十位和个位部分。</param>
    /// <returns>byte</returns>
    public static byte intToBCDByte(int number) {
        int value;
        if (number >= 100) {
            value = number % 100;
        } else {
            value = number;
        }
        return (byte) (value / 10 * 16 + value % 10);
    }

    /**
     * 加33
     *
     * @param inputByte
     * @return
     */
    public static byte increase33(byte inputByte) {
        return (byte) (inputByte + 0x33);
    }

    /**
     * 减33
     *
     * @param inputByte
     * @return
     */
    public static byte decrease33(byte inputByte) {
        return (byte) (inputByte - 0x33);
    }


    /**
     * ascii转int
     *
     * @return
     */
    public static int byteAsciiToInt(byte b) {
        char c = (char) b;
        return Integer.parseInt(String.valueOf(c));
    }

    public static String HexArrayToASCII(byte[] hexs) {
        String lin = "";
        for (byte hex : hexs) {
            lin = lin + hex + " ";
        }
        String[] ss = lin.trim().split(" ");
        char[] c = new char[ss.length];
        int a;
        for (int i = 0; i < c.length; i++) {
            a = Integer.parseInt(ss[i]);
            c[i] = (char) a;
        }
        return new String(c);
    }

    /**
     * 去掉前导字节ff，fe
     *
     * @param bytes
     * @return
     */
    public static byte[] cleanFEAndFF(byte[] bytes) {
        int index = 0;
        int firstFEFlag = 0;
        int lastFEFlag = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == (byte) 0xfe && lastFEFlag == 0 && i < (bytes.length - 2)) {
                firstFEFlag = 1;
            } else {
                if (firstFEFlag == 1) {
                    lastFEFlag = 1;
                }
                index++;
            }
        }
        byte[] outputBuff = new byte[index];
        firstFEFlag = 0;
        lastFEFlag = 0;
        int j = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == (byte) 0xFE && lastFEFlag == 0 && i < (bytes.length - 2)) {
                firstFEFlag = 1;
            } else {
                if (firstFEFlag == 1) {
                    lastFEFlag = 1;
                }
                outputBuff[j] = bytes[i];
                j++;
            }
        }

        byte[] _outputBuff;
        if (outputBuff.length > 1 && outputBuff[0] == 0x00 && outputBuff[1] == 0x68) {
            _outputBuff = new byte[outputBuff.length - 1];
            System.arraycopy(outputBuff, 1, _outputBuff, 0, outputBuff.length - 1);
        } else if (outputBuff.length > 1 && outputBuff[0] == 0x66 && outputBuff[1] == 0x00 && outputBuff[2] == 0x68) {
            _outputBuff = new byte[outputBuff.length - 2];
            System.arraycopy(outputBuff, 2, _outputBuff, 0, outputBuff.length - 2);
        } else if (outputBuff.length > 1 && outputBuff[0] == (byte) 0xFF && outputBuff[1] == 0x68) {
            _outputBuff = new byte[outputBuff.length - 1];
            System.arraycopy(outputBuff, 1, _outputBuff, 0, outputBuff.length - 1);
        } else {
            _outputBuff = outputBuff;
        }
        return _outputBuff;
    }

    /**
     * 翻转
     *
     * @param array
     * @return
     */
    public static byte[] reverse(byte[] array) {
        byte[] new_array = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            // 反转后数组的第一个元素等于源数组的最后一个元素：
            new_array[i] = array[array.length - i - 1];
        }
        return new_array;
    }

    //10进制转16进制
    public static String intToHex(int n) {
        char[] ch = new char[20];
        int nIndex = 0;
        while (true) {
            int m = n / 16;
            int k = n % 16;
            if (k == 15)
                ch[nIndex] = 'F';
            else if (k == 14)
                ch[nIndex] = 'E';
            else if (k == 13)
                ch[nIndex] = 'D';
            else if (k == 12)
                ch[nIndex] = 'C';
            else if (k == 11)
                ch[nIndex] = 'B';
            else if (k == 10)
                ch[nIndex] = 'A';
            else
                ch[nIndex] = (char) ('0' + k);
            nIndex++;
            if (m == 0)
                break;
            n = m;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ch, 0, nIndex);
        sb.reverse();
        String strHex = "0x";
        strHex += sb.toString();
        return strHex;
    }

    //16进制转10进制
    public static int HexToInt(String strHex) {
        int nResult = 0;
        if (!IsHex(strHex))
            return nResult;
        String str = strHex.toUpperCase();
        if (str.length() > 2) {
            if (str.charAt(0) == '0' && str.charAt(1) == 'X') {
                str = str.substring(2);
            }
        }
        int nLen = str.length();
        for (int i = 0; i < nLen; ++i) {
            char ch = str.charAt(nLen - i - 1);
            try {
                nResult += (GetHex(ch) * GetPower(16, i));
            } catch (Exception ignored) {
            }
        }
        return nResult;
    }

    //16进制转java有符号byte
    public static byte HexToByte(String strHex) {
        return (byte) HexToInt(strHex);
    }

    //计算16进制对应的数值
    public static int GetHex(char ch) throws Exception {
        if (ch >= '0' && ch <= '9')
            return (int) (ch - '0');
        if (ch >= 'a' && ch <= 'f')
            return (int) (ch - 'a' + 10);
        if (ch >= 'A' && ch <= 'F')
            return (int) (ch - 'A' + 10);
        throw new Exception("updateError param");
    }

    //计算幂
    public static int GetPower(int nValue, int nCount) {
        if (nCount < 0)
            try {
                throw new Exception("nCount can't small than 1!");
            } catch (Exception ignored) {
            }
        if (nCount == 0)
            return 1;
        int nSum = 1;
        for (int i = 0; i < nCount; ++i) {
            nSum = nSum * nValue;
        }
        return nSum;
    }

    //判断是否是16进制数
    public static boolean IsHex(String strHex) {
        int i = 0;
        if (strHex.length() > 2) {
            if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
                i = 2;
            }
        }
        for (; i < strHex.length(); ++i) {
            char ch = strHex.charAt(i);
            if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F') || (ch >= 'a' && ch <= 'f'))
                continue;
            return false;
        }
        return true;
    }

    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStrToByteArray(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }

    /**
     * 字节数组转为普通字符串（ASCII对应的字符）
     *
     * @param bytes byte[]
     * @return String
     */
    public static String byteToASCIIString(byte[] bytes) {
        String result = "";
        char temp;
        for (byte aByte : bytes) {
            temp = (char) aByte;
            result += temp;
        }
        return result;
    }

    public static byte getCheckCode(byte[] bytes) {
        byte cs = 0x00;
        for (int i = 0; i < bytes.length - 2; i++)//有10+Length个字节需要进行校验
        {
            cs += bytes[i];//透传的帧从第5个字节开始
        }
        return cs;
    }

    /**
     * 翻转获取漏保地址byte数组
     *
     * @param str
     * @return
     */
    public static byte[] stringToBytes(String str) {
        int j = 0;
        int length = str.length();
        if (length % 2 == 0) {
            j = (length / 2) - 1;
        }
        byte[] bytes = new byte[j + 1];
        for (int i = 1; i < length; i++) {
            if (i % 2 == 1) {
                String s = String.valueOf(str.charAt(i - 1)) + String.valueOf(str.charAt(i));
                byte b = HexUtils.HexToByte("0x" + s);
                bytes[j] = b;
                j--;
            }
        }
        return bytes;
    }

    // <summary>
    // 16进制字符串转换为二进制数组
    // 字符串每个字节之间都应该有空格，
    //大多数的串口通讯资料上面的16进制都是字节之间都是用空格来分割的。
    //返回一个二进制字符串
    public static byte[] hexStringToByteArray(String hexStr) {
        String[] array = hexStr.split(" ");
        byte[] buff = new byte[array.length];
        for (int i = 0; i < buff.length; i++) {
            buff[i] = HexToByte(array[i]);
        }
        return buff;
    }

    /// <summary>
    /// 二进制转换为十六进制，定长转换
    /// </summary>
    /// <param name="x"></param>
    /// <param name="iLength"></param>
    /// <returns></returns>
    public static String binToHex(String x, int length) {
        StringBuilder sb = new StringBuilder();
        int iCount = 0;
        iCount = x.length() / length;
        if (x.length() % length > 0) {
            iCount += 1;
        }
        int X = 0;
        for (int i = 0; i < iCount; i++) {
            if ((i + 1) * length > x.length()) {
                X = Integer.parseInt(x.substring(i * length, (x.length() - length)));
            } else {
                X = Integer.parseInt(x.substring(i * length, length));
            }
            int j = 0;
            int a, b = 0;
            while (X > 0) {
                a = X % 10;
                X = X / 10;
                b = b + a * pow(2, j);
                j++;
            }
            //前补0
            sb.append(intToHex(b));
        }
        return sb.toString();
    }

    public static int pow(int x, int y) {
        int i = 1;
        if (y == 0)
            return 1;
        while (i < y) {
            x = x * x;
            i++;
        }
        return x;
    }

    public static byte[] padLeft(int len, byte b, byte[] src) {
        byte[] temp = new byte[len];
        if (src == null) return null;

        if (src.length == len) return src;
        else {
            System.arraycopy(src, 0, temp, (len - src.length), src.length);
        }
        for (int i = 0; i < (len - src.length); i++) {
            temp[i] = b;
        }
        return temp;
    }

    /**
     * 字符串转bcd码
     *
     * @param asc
     * @return
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    /**
     * @功能: BCD码转为10进制串(阿拉伯数据)
     * @参数: BCD码
     * @结果: 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuilder temp = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            temp.append((byte) ((aByte & 0xf0) >>> 4));
            temp.append((byte) (aByte & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
                .toString().substring(1) : temp.toString();
    }

    /**
     * 16进制不足位数补0
     *
     * @param str
     * @param len
     * @return
     */
    public static String hexPadLeft(String str, int len) {
        String pad = "0000000000000000";
        return len > str.length() && len <= 16 && len >= 0 ? pad.substring(0, len - str.length()) + str : str;
    }

    public static byte[] intToBcd100(Object obj) {
        if (obj == null) return null;
        double val = Double.parseDouble(obj.toString());
        byte[] result = new byte[2];
        result[0] = intToBCDByte((byte) (val % 100));
        result[1] = intToBCDByte((byte) (val / 100));
        return result;
    }

    /**
     * 将缓冲区的字节数组转换为16进制
     *
     * @param value 缓冲字节
     * @param pos   开始字节
     * @param flag  偏移量
     * @return
     */
    public static String byteArrayToHexStr(byte[] value, int pos, int flag) {
        StringBuilder sb = new StringBuilder();
        int MaxLen;
        if ((pos + flag) > value.length) {
            MaxLen = value.length;
        } else {
            MaxLen = pos + flag;
        }
        for (int i = pos; i < MaxLen; i++) {
            sb.append(value[i]);
        }
        return sb.toString();
    }

    /**
     * 获取byte高四位
     *
     * @param data
     * @return
     */
    public static int getByteHigh4(byte data) {
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    /**
     * 获取byte低四位
     *
     * @param data
     * @return
     */
    public static int getByteLow4(byte data) {
        int low;
        low = (data & 0x0f);
        return low;
    }

    /**
     * 判断是否为BCD码
     *
     * @param b
     * @return
     */
    public static boolean isBCD(byte b) {
        int h = getByteHigh4(b);
        int l = getByteLow4(b);
        return h >= 0 && h <= 9 && l >= 0 && l <= 9;
    }

    /**
     * 根据byte获取bit
     *
     * @param b
     * @return
     */
    public static byte getBitFromByte(int di, byte b) {
        return (byte) ((b >> di) & 0x01);
    }

    /**
     * 获取2位int的高位 0xffff
     *
     * @param i
     * @return
     */
    public static byte getIntHigh(int i) {
        return (byte) (i / 256);
    }

    /**
     * 获取2位int的低位 0xfffff
     *
     * @param i
     * @return
     */
    public static byte getIntLow(int i) {
        return (byte) (i % 256);
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    public static byte[] getBitArrayFromByte(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    /**
     * 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b) & 0x1);
    }

    /**
     * 二进制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String temp;
        for (byte aB : b) {
            temp = (Integer.toHexString(aB & 0XFF));
            if (temp.length() == 1)
                hs = hs + "0" + temp;
            else
                hs = hs + temp;
        }
        return hs;
    }

    /**
     * 字符串转二进制
     *
     * @param str
     * @return
     */
    public static byte[] hex2byte(String str) {
        if (str == null) return null;
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1) return null;
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将四位byte转float
     *
     * @param bytes
     * @return
     */
    public static float intBitsToFloat(byte[] bytes) {
        if (bytes.length != 4) return 0f;
        return Float.intBitsToFloat(Integer.parseInt(byte2hex(bytes), 16));
    }
}