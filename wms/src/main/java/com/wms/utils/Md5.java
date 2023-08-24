package com.wms.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5
 *
 * @author woo
 * @date 2022/10/11
 */
@Component
public class Md5 {
    private static final String salt = "ymy521rl";
    public  static  String re_md5 = "";
    public  String Md5(String password ) {
        //这是固定salt

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] b = md.digest();
            int i;
            //Stringbuffer数据类型提供了修改的函数并且修改不会建立新对象
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    //尾端插入字符
                    buf.append("0");
                buf.append(Integer.toHexString(i));//16进制转换并字符化
            }
            re_md5 = buf.toString();
            re_md5 = ""+salt.charAt(0)+salt.charAt(3) + re_md5 + salt.charAt(3) +salt.charAt(2)+salt.charAt(1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static void main(String[] args) {
        Md5 md5 = new Md5();
        String s = "1234";
        System.out.println(md5.Md5(md5.Md5(s)));
    }

}
