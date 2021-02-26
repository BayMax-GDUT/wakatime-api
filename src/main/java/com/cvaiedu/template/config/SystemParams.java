package com.cvaiedu.template.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class SystemParams {

    /**
     * 超级验证码
     */
    @Value("${system-params.super-captcha}")
    private String superCaptcha;

}