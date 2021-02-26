package com.cvaiedu.template.annotation;

import java.lang.annotation.*;

/**
 * 自定义授权注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresPermissions {

    String value() default "";

}
