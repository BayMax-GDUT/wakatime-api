package com.cvaiedu.template.util;

import org.apache.commons.lang.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * 签名工具类
 */
public class AuthUtils {

    /**
     * 单点登录签名，需要使用到appSecret
     *
     * @param organizationType
     * @param organizationId
     * @param accountCode
     * @param appKey
     * @param appSecret
     * @param timestamp
     * @return
     */
    public static String generateAuthSignature(Integer organizationType, Long organizationId, Integer accountCode, String appKey,
                                               String appSecret, String timestamp) {
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //公共参数
        parameters.put("organizationType", organizationType);
        parameters.put("organizationId", organizationId);
        parameters.put("accountCode", accountCode);
        parameters.put("appKey", appKey);
        parameters.put("timestamp", timestamp);
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<>(parameters);
        // 遍历排序的字典,并拼接"key=value"格式
        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString().trim();
            if (!StringUtils.isEmpty(value))
                sb.append("&").append(key).append("=").append(value);
        }
        String stringA = sb.toString().replaceFirst("&", "");
        String stringSignTemp = stringA + "&" + "appSecret=" + appSecret;
        //将签名使用MD5加密并全部字母变为大写
        String signValue = md5(stringSignTemp).toUpperCase();
        return signValue;
    }

    /**
     * 普通接口参数签名,不需要使用到appSecret
     *
     * @param parameters
     * @return signature
     */
    public static String generateAuthSignature(Map<String, Object> parameters) {
        StringBuilder sb = new StringBuilder();
        //公共参数
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<>(parameters);
        // 遍历排序的字典,并拼接"key=value"格式
        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
            String key = entry.getKey();
            if ("sign".equals(key)) {
                continue;
            }
            String value = entry.getValue().toString().trim();
            if (!StringUtils.isEmpty(value))
                sb.append("&").append(key).append("=").append(value);
        }
        String stringA = sb.toString().replaceFirst("&", "");

        String stringSignTemp = stringA;
        System.out.println("加密前的字符串：       " + stringSignTemp);
        //将签名使用MD5加密并全部字母变为大写
        String signValue = md5(stringSignTemp).toUpperCase();
        return signValue;
    }

    /**
     * md5加密
     *
     * @param str
     * @return
     */
    private static String md5(String str) {
        MessageDigest md = null;
        StringBuilder buffer = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] byteData = md.digest();
            buffer = new StringBuilder();
            for (byte byteDatum : byteData) {
                buffer.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception ignored) {
        }

        return null == buffer ? "" : buffer.toString();
    }
}
