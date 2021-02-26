package com.cvaiedu.template.exception;

import com.amazonaws.AmazonServiceException;
import com.cvaiedu.template.result.Result;
import com.cvaiedu.template.result.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


/**
 * controller 增强器
 *
 * @author sam
 * @since 2017/7/17
 */
@RestControllerAdvice
public class ControllerAdvice {
    private static Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception ex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        if (ex instanceof FrameworkException) {
            FrameworkException unionPlatException = (FrameworkException) ex;
            log.error("【自定义异常】", ex);
            return Result.error(unionPlatException.getCode(), unionPlatException.getMessage());
        } else if (ex instanceof AmazonServiceException) {
            log.error("【文件上传到京东云失败】", ex);
            return Result.error(ResultEnum.ERROR_500.getCode(), "文件上传到京东云失败");
        } else if (ex instanceof IOException) {
            log.error("【文件上传失败】", ex);
            return Result.error(ResultEnum.ERROR_500.getCode(), "文件上传失败");
        } else {
            log.error("【系统异常】", ex);
            return Result.error();
        }
    }

}