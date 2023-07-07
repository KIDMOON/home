/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.kisso.SSOHelper;
import com.example.home.controller.RestResult;
import com.example.home.dto.SessionDTO;
import com.example.home.electronic_port.dto.UserDTO;
import com.example.home.electronic_port.service.SessionService;
import com.example.home.electronic_port.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息登陆之类的
 *
 * @author kid.bian
 * @date 2020/12/28 下午5:13
 * @since 1.0
 **/
@RestController
@RequestMapping("/app/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "/login_user_info")
    @ResponseBody
    public RestResult getUserInfo() {
        return new RestResult().setData(userService.findLoginUserVO());
    }


    @PostMapping(value = "update/user")
    @ResponseBody
    public RestResult getUserInfo(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new RestResult().setMessage("修改成功");
    }

    @PostMapping(value = "/login_out")
    @ResponseBody
    public RestResult loginMain(
            HttpServletRequest request,
            HttpServletResponse response) {
        //清空原登陆信息
        SSOHelper.clearLogin(request, response);
        //登陆
        return new RestResult().setData(true);
    }

    @GetMapping("/session")
    public RestResult getSession() {
        SessionDTO sessionDTO = sessionService.getSession();
        return new RestResult().setData(sessionDTO);
    }

}
