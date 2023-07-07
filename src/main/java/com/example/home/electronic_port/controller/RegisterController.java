/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.example.home.annotation.Action;
import com.example.home.annotation.Login;
import com.example.home.controller.RestResult;
import com.example.home.entity.UserDO;
import com.example.home.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/1/3 6:41 下午
 * @since 1.0
 **/
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @SaIgnore
    @PostMapping("/save")
    public RestResult saveRegister(@RequestBody UserDO dto) {
        registerService.saveRegister(dto);
        return new RestResult();
    }
}
