package com.yucheng.blog.util;

import java.security.SecureRandom;

/**
 * @author YuCheng
 * @date 2021/9/26 - 下午 09:35
 */
public class CipherTest {

    public static void main(String[] args) {

        String key = "TravelAndEatBlog"; // 16位元組對稱式金鑰
        byte[] iv = new byte[128 / 8];   // 初始向量

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        String password = "j123";
        String encrypt = "";
        String decrypt = "";

        for (int i = 0; i < iv.length; i++) {
            System.out.println(iv[i]);
        }

        try {
            //加密
            encrypt = CipherUtils.encryptString(key, password, iv);
            //解密
            decrypt = CipherUtils.decryptString(key, encrypt, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("原文: " + password);
        System.out.println("加密: " + encrypt);
        System.out.println("解密: " + decrypt);

    }
}
