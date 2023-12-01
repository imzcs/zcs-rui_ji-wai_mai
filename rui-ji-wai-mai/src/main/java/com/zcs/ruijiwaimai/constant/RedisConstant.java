package com.zcs.ruijiwaimai.constant;

public class RedisConstant {
    public static final String LOGIN_CODE_KEY_PREFIX = "ruiji:login:code:";

    public static final Long LOGIN_CODE_KEY_TTL = 5L;
    public static final String LOGIN_TOKEN_KEY_PREFIX = "ruiji:login:token:";
    public static final Long LOGIN_TOKEN_KEY_TTL = 30L;
}
