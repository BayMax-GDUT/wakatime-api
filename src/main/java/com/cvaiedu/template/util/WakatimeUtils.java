package com.cvaiedu.template.util;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Map;

@Component
public class WakatimeUtils {

    @Value("${wakatime.client_id}")
    private String clientId;

    @Value("${wakatime.client_secret}")
    private String clientSecret;

    @Value("${wakatime.api_key}")
    private String apiKey;

    @Value("${wakatime.base_url}")
    private String baseUrl;

    private static final String AllTimeSinceTodayUri = "/api/v1/users/current/all_time_since_today";

    // 使用apiKey作为凭证时的前缀
    private static final String apiKeyAuthorizationPrefix = "Basic ";

    public static String getApiKeyAuthorization(String apiKey) {
        if (apiKey == null || apiKey.trim().length() == 0) {
            throw new NullPointerException("apiKey不能为空");
        }
        String s = Base64.getEncoder().encodeToString(apiKey.getBytes());
        return apiKeyAuthorizationPrefix + s;
    }

    /**
     * 自帐户创建以来记录的总时间，甚至对免费帐户可用。
     * @param parameters
     * project - String - optional - Shows the total time for a project, since project created。
     * @return
     */
    public JSONObject AllTimeSinceToday(Map<String, Object> parameters) {
        return HttpRequestUtils.doGet(baseUrl + AllTimeSinceTodayUri, parameters);
    }

    /**
     * get请求
     * @param url
     * @param parameters
     * @return
     */
    public static JSONObject doGet(String url, Map<String, Object> parameters) {
        String body = HttpRequest.get(url + parametersString(parameters)).timeout(20000).execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }

    /**
     * 将参数组合成url后面的字符串
     * @param parameters
     * @return
     */
    public static final String parametersString(Map<String, Object> parameters) {
        if (parameters == null || parameters.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String s : parameters.keySet()) {
            sb.append("?" + s + "=" + String.valueOf(parameters.get(s)));
        }
        return sb.toString();
    }

}
