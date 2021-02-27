package com.cvaiedu.template.event;

import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author tangyi
 * @date 2019/3/12 23:58
 */
public class LogEvent extends ApplicationEvent {
    public LogEvent(Object source) {
        super(source);
    }
//    public LogEvent(Log source) {
//        super(source);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>log发送");
//    }
}
