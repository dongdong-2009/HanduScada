import main.com.handu.scada.utils.AesUtils;
import main.com.handu.scada.utils.UUIDUtils;

/**
 * Created by 柳梦 on 2017/12/15.
 */
public class Test {


    @org.junit.Test
    public void test() throws InterruptedException {
//
//        byte h = (byte) 0x81;
//        byte l = 0x54;
//        int s = (0x01<<8)+l;
//        System.err.println(s);
//
//        h = HexUtils.getIntHigh(s);
//        l= HexUtils.getIntLow(s);
//        System.err.println(h);
////        System.err.println(l);
//
//
//        System.err.println((0xff & 0x7f) << 8);
//
//        int b =Integer.parseInt("4");
//
//        System.err.println((float) b);

        System.err.println(UUIDUtils.getUUId());



    }


    /**
     * 解密
     *
     * @param str
     */
    private void dec(String str) {
        System.err.println(AesUtils.decrypt(str));
    }

    /**
     * 加密
     *
     * @param str
     */
    private void enc(String str) {
        System.err.println(AesUtils.encrypt(str));
    }
}
