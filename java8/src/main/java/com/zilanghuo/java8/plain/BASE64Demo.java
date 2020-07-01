package com.zilanghuo.java8.plain;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by laiwufa on 2019-10-24
 */
public class BASE64Demo {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static void main(String[] args) throws Exception {
        String data = BASE64Demo.encryptBASE64("[B@58ceff1".getBytes());
        System.out.println("加密前：" + data);

        byte[] byteArray = BASE64Demo.decryptBASE64(data);
        System.out.println("解密后：" + new String(byteArray));

    }
}