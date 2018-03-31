package main.com.handu.scada.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;

/**
 * 作者：柳梦 2016/9/20 14:38
 * 邮箱：mobyq@qq.com
 * 说明: AES加密工具
 */
public class AesUtils {

    private static final String DEFAULT_KEY = "Hdkj@ll.0035;";//用该字符串生成默认秘钥
    private static final String SHA1PRNG = "SHA1PRNG";//SHA1PRNG强随机种子算法
    private static final String HEX = "!@#$%^&*12345678";//用这些字符生成随机秘钥
    private static final String AES = "AES"; //AES 加密
    private static final String CIPHER_MODE = "AES/CBC/PKCS5Padding";//AES是加密方式，CBC是工作模式，PKCS5Padding是填充模式

    /**
     * @param key        秘钥
     * @param encryptStr 加密字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String key, String encryptStr) {
        try {
            byte[] result = encrypt(key, encryptStr.getBytes());
            return parseByte2HexStr(result);
        } catch (Exception ignored) {
        }
        return "";
    }

    /**
     * @param encryptStr 加密字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String encryptStr) {
        return encrypt(defaultKey(), encryptStr);
    }

    private static byte[] encrypt(String key, byte[] clear) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        return cipher.doFinal(clear);
    }

    /**
     * @param key        秘钥
     * @param decryptStr 解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String key, String decryptStr) {
        try {
            byte[] enc = parseHexStr2Byte(decryptStr);
            byte[] result = decrypt(key, enc);
            return new String(result);
        } catch (Exception ignored) {
        }
        return "";
    }

    /**
     * @param decryptStr 解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String decryptStr) {
        return decrypt(defaultKey(), decryptStr);
    }

    private static byte[] decrypt(String key, byte[] decryptStr) throws Exception {
        byte[] raw = getRawKey(key.getBytes());
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        return cipher.doFinal(decryptStr);
    }

    /**
     * 生成随机数，可以当做动态的密钥
     * 加密和解密的密钥必须一致，不然将不能解密
     */
    public static String generateKey() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] key = new byte[20];
            secureRandom.nextBytes(key);
            return toHex(key);
        } catch (NoSuchAlgorithmException ignored) {
        }
        return "";
    }

    /**
     * 生成默认的key
     *
     * @return
     */
    public static String defaultKey() {
        return encrypt(DEFAULT_KEY, DEFAULT_KEY);
    }

    public static String defaultKey(String key) {
        return encrypt(key, DEFAULT_KEY);
    }

    /**
     * 对密钥进行处理
     */
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        //for android
        SecureRandom sr;
        sr = SecureRandom.getInstance(SHA1PRNG);
        // for Java
        // secureRandom = SecureRandom.getInstance(SHA1PRNG);
        sr.setSeed(seed);
        keyGenerator.init(128, sr); //256 bits or 128 bits,192bits
        //AES中128位密钥版本有10个加密循环，192比特密钥版本有12个加密循环，256比特密钥版本则有14个加密循环。
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 二进制转字符
     */
    private static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (byte aBuf : buf) {
            appendHex(result, aBuf);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (byte aBuf : buf) {
            String hex = Integer.toHexString(aBuf & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    private static class CryptoProvider extends Provider {

        private static final long serialVersionUID = 1L;

        /**
         * Creates a Provider and puts parameters
         */
        CryptoProvider() {
            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
            put("SecureRandom.SHA1PRNG",
                    "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }
}
