package com.cvaiedu.template.aspect;

import cn.hutool.core.util.URLUtil;
import com.cvaiedu.template.event.LogEvent;
import com.cvaiedu.template.util.HttpContextUtils;
import com.cvaiedu.template.util.IPUtils;
import com.cvaiedu.template.annotation.SysLog;
import com.cvaiedu.template.constant.Constant;
import com.cvaiedu.template.util.SpringContextHolder;
import com.cvaiedu.template.util.TokenUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统日志，切面处理类
 *
 * @author Dell
 */
@Aspect
public class SysLogAspect {

    @Autowired
    private TokenUtils tokenUtils;

    private static ApplicationContext applicationContext = null;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.cvaiedu.template.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //日志信息放入Log实体
        Log log = saveSysLog(point, time);
        //异步存日志
        SpringContextHolder.publishEvent(new LogEvent(log));
        return result;
    }


    private Log saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log sysLog = new Log();
        SysLog syslog = method.getAnnotation(SysLog.class);
        //注解上的描述
        sysLog.setOperation(syslog.value());
        sysLog.setModule(syslog.module());
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求的参数
        sysLog.setMethod(className + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        logger.debug("[类名]:{},[方法]:{},[参数]:{}", className, methodName, args);
        //用户名
        Integer accountCode = tokenUtils.getOnlineUser().getAccountCode();
        String name = tokenUtils.getOnlineUser().getName();
        Long organizationId = tokenUtils.getOnlineUser().getOrganizationId();
        sysLog.setAccountCode(accountCode);
        sysLog.setRealName(name);
        sysLog.setCjyOrganizationId(organizationId);
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        sysLog.setUserAgent(request.getHeader("user-agent"));
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setTime(time + "");
        sysLog.setType(Constant.STATUS_NORMAL.toString());
        sysLog.setCreateTime(new Date());
        return sysLog;
    }
}
