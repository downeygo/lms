package com.imen.lms.core.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @autor LIGANG
 * @data 2018/10/7 15:34
 * @description
 */
public class MD5Util {
    //密码加密
    public static String passwordEncrypt(String password, String salt) {
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        return md5Hash.toString();
    }
}
