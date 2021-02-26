package com.cvaiedu.template.annotation;

import java.lang.annotation.*;

/**
 * api接口，可忽略Token验证（免登陆）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
