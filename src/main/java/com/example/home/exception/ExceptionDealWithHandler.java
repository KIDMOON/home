/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.exception;

import com.example.home.controller.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:46 下午
 * @since 1.0
 **/
@ControllerAdvice
public class ExceptionDealWithHandler {

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public RestResult jsonErrorHandler(HttpServletRequest request, HttpServletResponse response, ServiceException ex)  {
        RestResult result = new RestResult().toFail().setStatus(RestResult.STATUS_ERROR);
        result.setCode(ex.getCode());
        result.setStatus(ex.getStatus());
        result.setMessage(ex.getMessage());
        return result;
    }


    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public void jsonErrorHandlerNotLoginException(HttpServletRequest request, HttpServletResponse response, NotLoginException ex) throws IOException {
        response.sendError(401);
    }


    @ExceptionHandler(value = NotCookieException.class)
    @ResponseBody
    public void jsonErrorHandlerNotCookieException(HttpServletRequest request, HttpServletResponse response, NotCookieException ex) throws IOException {
        response.sendError(402);
    }
}
