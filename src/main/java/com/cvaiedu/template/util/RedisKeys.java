package com.cvaiedu.template.util;

/**
 * Redis所有Keys
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

    /**
     * 图形验证码redisKey
     *
     * @param key
     * @return
     */
    public static String getCaptchaKey(String key) {
        return "threeClazz:buss:captcha:" + key;
    }

    /**
     * 当前用户,使用token为key
     *
     * @param token
     * @return
     */
    public static String getOnlineUserKey(String token) {
        return "threeClazz:framework:online:user:" + token;
    }

    /**
     * 创教云token
     *
     * @param appKey
     * @return
     */
    public static String getCjyPlatFormTokenKey(String appKey) {
        return "threeClazz:cjy-platform-token:" + appKey;
    }
}
