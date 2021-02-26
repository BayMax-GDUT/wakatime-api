package com.cvaiedu.template.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HttpRequestUtils {

    public static JSONObject doGet(String url, Map<String, Object> parameters) {
        String body = HttpRequest.get(url + parametersString(parameters)).timeout(20000).execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }

    public static final String parametersString(Map<String, Object> parameters) {
        if (parameters == null || parameters.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String s : parameters.keySet()) {
            sb.append("?" + s + "=" + String.valueOf(parameters.get(s)));
        }
        return sb.toString();
    }
}
