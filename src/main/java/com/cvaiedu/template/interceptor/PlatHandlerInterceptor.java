package com.cvaiedu.template.interceptor;

import com.cvaiedu.template.annotation.AuthIgnore;
import com.cvaiedu.template.exception.FrameworkException;
import com.cvaiedu.template.result.ResultEnum;
import com.cvaiedu.template.util.RedisKeys;
import com.cvaiedu.template.util.RedisUtils;
import com.cvaiedu.template.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlatHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 认证
//        AuthIgnore annotation;
//        if (handler instanceof HandlerMethod) {
//            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
//        } else {
//            return true;
//        }
//        // 如果有@IgnoreAuth注解，则不验证token
//        if (annotation != null) {
//            return true;
//        }
//        // 获取用户凭证
//        String token = tokenUtils.getToken();
//        // 凭证为空
//        if (StringUtils.isEmpty(token)) {
//            throw new FrameworkException(ResultEnum.ERROR_401.getCode(), ResultEnum.ERROR_401.getMsg());
//        }
//        // 判断redis中是否存在用户，存在则延迟时间
//        Boolean aBoolean = redisUtils.hasKey(RedisKeys.getOnlineUserKey(token), RedisUtils.DEFAULT_EXPIRE);
//        if (!aBoolean) {
//            throw new FrameworkException(ResultEnum.ERROR_401.getCode(), ResultEnum.ERROR_401.getMsg());
//        }
        return true;
    }
}
