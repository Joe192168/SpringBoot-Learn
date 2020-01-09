package com.joe.oauth.shiro.utils;

import java.util.UUID;

public class Constant {
    public static final String JWT_ID = UUID.randomUUID().toString(); //生成的token


    public static final String JWT_SECRET = "fssFSS";//加密密文
    public static final int JWT_TTL = 2000/*60*60*1000*/;  //过期时间 3600000
}