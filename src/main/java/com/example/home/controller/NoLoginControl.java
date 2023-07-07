/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.kisso.SSOHelper;
import com.example.home.annotation.Action;
import com.example.home.annotation.Login;
import com.example.home.dto.LoginDTO;
import com.example.home.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 5:21 下午
 * @since 1.0
 **/
@Controller
public class NoLoginControl {

    @Autowired
    private SecurityService securityService;

    @SaIgnore
    @PostMapping(value = "/login_main")
    @ResponseBody
    public RestResult loginMain(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDTO loginDTO) {
        //登陆
        securityService.login(request, response, loginDTO);
        return new RestResult();
    }

    @SaIgnore
    @PostMapping(value = "/login_out")
    @ResponseBody
    public RestResult loginOut() {
        //清空原登陆信息
        StpUtil.getLoginId();

        StpUtil.logout();
        return new RestResult();
    }
}
