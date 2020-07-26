package com.itheima.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Aaron
 * @date 2020/7/25 - 16:28
 * 用于将之前的密码加密
 */
public class BCryptPasswordEncoderUtils {

    public static String encodePassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println(BCryptPasswordEncoderUtils.encodePassword("6013"));
    }
}
