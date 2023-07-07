/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.annotation;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;

import java.lang.annotation.*;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 2:33 下午
 * @since 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@SaIgnore
@Documented
public @interface Login {

    /**
     * 执行动作
     * {@link Action}
     */
    Action action() default Action.Normal;

}
