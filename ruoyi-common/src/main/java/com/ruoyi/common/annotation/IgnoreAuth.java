package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * 忽略权限认证注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {
}
