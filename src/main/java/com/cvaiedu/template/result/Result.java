package com.cvaiedu.template.result;


import java.util.HashMap;


/**
 * 返回数据
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", ResultEnum.SUCCESS.getCode());
        put("msg", "success");
        put("data", null);
    }

    public static Result error() {
        return error(ResultEnum.ERROR_500.getCode(), "系统错误，请联系管理员");
    }

    public static Result error(String msg) {
        return error(ResultEnum.ERROR_500.getCode(), msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object object) {
        Result r = new Result();
        r.put("data", object);
        return r;
    }
}
