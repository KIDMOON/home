/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import com.example.home.annotation.Action;
import com.example.home.annotation.Login;
import com.example.home.controller.RestResult;
import com.example.home.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 系统设置 初始化
 *
 * @author klaus.jin
 * @date 2021/3/24 10:05 上午
 * @since 1.0
 **/
@RestController
@RequestMapping("/app/api/init")
public class InitController {

    @Autowired
    private InitService initService;

    @PostMapping("/cmcode")
    public RestResult saveRegister() throws ParseException {
        initService.initCmCode();
        return new RestResult();
    }
}
