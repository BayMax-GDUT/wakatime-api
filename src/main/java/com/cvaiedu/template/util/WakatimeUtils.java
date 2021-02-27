package com.cvaiedu.template.util;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.PAData;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Map;

@Component
public class WakatimeUtils {

    // baseUrl
    private static final String WAKATIMEBASEURL = "https://wakatime.com";

    private static final String ALLTIMESINCETODAYURI = "/api/v1/users/current/all_time_since_today";

    private static final String DATADUMPSURI = "/api/v1/users/current/data_dumps";

    private static final String DURATIONSURI = "/api/v1/users/current/durations";

    private static final String GOALSURI = "/api/v1/users/current/goals";

    private static final String EDITORSURI = "/api/v1/editors";

    private static final String STATSURI = "/api/v1/users/current/stats/";

    // 使用apiKey作为凭证时的前缀
    private static final String APIKEYAUTHORIZATIONPREFIX = "Basic ";
    // API Key
    private static final String APIKEY = "46c218b6-d136-437b-b63e-4ec431c60dc3";

    private static final String APIKEYAUTHORIZATION;

    static {
        if (APIKEY == null || APIKEY.trim().length() == 0) {
            throw new NullPointerException("apiKey不能为空");
        }
        String s = Base64.getEncoder().encodeToString(APIKEY.getBytes());
        APIKEYAUTHORIZATION = APIKEYAUTHORIZATIONPREFIX + s;
    }

    /**
     * 自帐户创建以来记录的总时间，甚至对免费帐户可用。
     * @param parameters
     * project - String - 可选 - 显示项目创建以来的总时间。
     * @return
     */
    public static JSONObject allTimeSinceToday(Map<String, Object> parameters) {
        return doGet(WAKATIMEBASEURL + ALLTIMESINCETODAYURI, parameters);
    }

    /**
     *列出用户导出的数据。还可以在https://wakatime.com/settings上创建数据导出，
     * 它包含自用户帐户创建以来以JSON格式作为每日摘要的所有用户编码统计数据。
     * @return
     */
    public static JSONObject dataDumps() {
        return doGet(WAKATIMEBASEURL + DATADUMPSURI, null);
    }

    /**
     * 给定一天的用户编码活动，作为一组持续时间。持续时间是心跳的只读表示，通过将多个心跳连接在一起创建，
     * 当它们彼此在15分钟内。默认的15分钟可以改变您的帐户的超时优先级。
     * @param parameters
     * date - Date - 必填 - 要求的日期;在用户所在时区，时间将从上午12点返回到晚上11点59分。
     * project - String - 可选 - 只显示此项目的持续时间
     * brances - String - 可选 - 只显示这些分支的持续时间;逗号分隔的分支名称列表。
     * timeout - Integer - 可选 - 将心跳加入持续时间时使用的超时首选项。默认为用户的超时值。有关更多信息，请参阅FAQ。
     * writes-only - Boolean - 可选 - writes_only偏好。默认为用户的writes_only设置。
     * timezone - String - 可选 - 给定日期的时区。默认为用户的时区。
     * slice-by - String - 可选 - 可选的主键，在切片持续时间时使用。默认为“实体”。可以是“实体”、“语言”、“依赖项”、“操作系统”、“编辑器”、“类别”或“机器”。
     * @return
     */
    public static JSONObject durations(Map<String, Object> parameters) {
        return doGet(WAKATIMEBASEURL + DURATIONSURI, parameters);
    }

    /**
     * WakaTime IDE插件列表，最新的插件版本，以及在WakaTime图表中使用的颜色。
     * @param parameters
     * unreleased - Boolean - 可选 - 包括未发布的插件。
     * @return
     */
    public static JSONObject editors(Map<String, Object> parameters) {
        return doGet(WAKATIMEBASEURL + EDITORSURI, parameters);
    }

    /**
     * 列出用户的目标。
     * @return
     */
    public static JSONObject goals() {
        return doGet(WAKATIMEBASEURL + GOALSURI, null);
    }

    public static JSONObject stats(Map<String, Object> parameters) {
        Object range = parameters.get("range");
        if (range != null && range instanceof String) {
            String rangeStr = (String) range;
            parameters.remove("range");
            return doGet(WAKATIMEBASEURL + STATSURI + "/" + rangeStr, parameters);
        }
        throw new IllegalArgumentException("wrong date range");

    }



    /**
     * get请求
     * @param url
     * @param parameters
     * @return
     */
    public static JSONObject doGet(String url, Map<String, Object> parameters) {
        String body = HttpRequest.get(url + parametersString(parameters)).header("Authorization", APIKEYAUTHORIZATION).timeout(20000).execute().body();
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
