/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.interceptor;

import com.example.home.annotation.Action;
import com.example.home.annotation.Admin;
import com.example.home.annotation.Login;
import com.example.home.controller.RestResult;
import com.example.home.electronic_port.constant.Constant;
import com.example.home.exception.NotLoginException;
import com.example.home.exception.ServiceException;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 4:34 下午
 * @since 1.0
 **/
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            // 获取相关annotation
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Login login = method.getAnnotation(Login.class);

            // 默认所有接口都需要登陆，除非明确标注不需要登陆
            if (login != null && login.action() == Action.Skip) {
                return true;
            }
            AnnotatedElementUtils.findAllMergedAnnotations(method,Login.class);
            // code为空视为未登录
            String code = SecurityContext.getCurrentLoginCode();
            if (code == null) {
                throw new NotLoginException();
            }
            Admin admin = method.getAnnotation(Admin.class);
            if (admin != null && !Constant.ADMIN_ID.equals(code)) {
                throw new ServiceException(RestResult.STATUS_ERROR, "无权限");
            }
        }
        return true;
    }

}
