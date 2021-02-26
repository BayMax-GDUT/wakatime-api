package com.cvaiedu.template.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统常量进行统一管理
 */
public abstract class Constant {

    private Constant() {
    }

    public static final String TOKEN = "Authorization"; // 从请求头中获取到token

    public static final Long ADMIN_ID = 1L; // 超级管理员ID,主要用于权限处理中，超级管理员拥有一切权限

    public static final Integer ADMIN_ORGANIZATION_TYPE = 1; // 平台管理员的机构类型

    public static final String ADMIN_ORGANIZATION_NAME = "平台管理组"; // 平台管理员的机构类型

    public static final Long ADMIN_ORGANIZATION_ID = 1L; // 平台的机构id

    public static final String SALT = "cvaiedu"; // 盐

    /**
     * 状态正常
     */
    public static final Integer STATUS_NORMAL = 1;

    /**
     * 状态异常（包括禁用，停止等）
     */
    public static final Integer STATUS_ERROR = 0;


    /**
     * 已删除
     */
    public static final Integer DEL_TAG_DEL = 0;

    /**
     * 正常
     */
    public static final Integer DEL_TAG_NORMAL = 1;

}