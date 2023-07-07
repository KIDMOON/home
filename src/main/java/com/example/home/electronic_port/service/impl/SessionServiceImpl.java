/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.home.dto.SessionDTO;
import com.example.home.electronic_port.http.HeadMap;
import com.example.home.electronic_port.http.HttpUtil;
import com.example.home.electronic_port.http.UrlConstant;
import com.example.home.electronic_port.service.SessionService;
import com.example.home.exception.NotCookieException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/9 下午2:05
 * @since 1.0
 **/
@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public SessionDTO getSession() {
        try {
            String body = HttpUtil.post_Request_no_Body(UrlConstant.GET_SESSION, HeadMap.User_Agent);
            SessionDTO sessionDTO = JSON.parseObject(body, SessionDTO.class);
            return sessionDTO;
        } catch (Exception e) {
            throw new NotCookieException();
        }
    }
}
