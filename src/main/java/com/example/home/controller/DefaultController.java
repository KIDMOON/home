/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.controller;

import com.example.home.annotation.Action;
import com.example.home.annotation.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description goes here.
 *
// * @author kid.bian
// * @date 2020/11/2 11:00 上午
 * @since 1.0
 **/
@Controller
public class DefaultController {

    @Login(action = Action.Skip)
    @GetMapping("/")
    public void gotoIndexPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}
