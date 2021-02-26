package com.cvaiedu.template.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 异步监听日志事件
 *
 * @author tangyi
 * @date 2019/3/12 23:59
 */
public class LogListener {

    @Autowired
    private LogService logService;

    public LogListener(LogService logService) {
        this.logService = logService;
    }

    /**
     * 异步记录日志
     *
     * @param event event
     */
    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        Log log = (Log) event.getSource();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>log监听");
        //保存系统日志
        logService.save(log);
    }

}
