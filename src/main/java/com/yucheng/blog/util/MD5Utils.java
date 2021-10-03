package com.yucheng.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author YuCheng
 * @date 2021/9/22 - 下午 03:20
 */

public class MD5Utils {

    public static String code(String str) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer();
            for(int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if(i < 0)
                    i +=256;
                if(i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            //16位加密
            //return buf.toString().substring(8, 24);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
