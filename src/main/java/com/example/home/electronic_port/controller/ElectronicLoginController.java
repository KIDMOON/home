/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import com.alibaba.fastjson.JSON;
import com.example.home.controller.RestResult;
import com.example.home.dto.SessionDTO;
import com.example.home.electronic_port.dto.CookieLoginDTO;
import com.example.home.electronic_port.http.HeadMap;
import com.example.home.electronic_port.http.HttpUtil;
import com.example.home.electronic_port.http.UrlConstant;
import com.example.home.exception.ServiceException;
import com.example.home.repuest_dto.LoginParamDTO;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 电子口岸登陆细节
 *
 * @author kid.bian
 * @date 2020/12/28 下午4:57
 * @since 1.0
 **/
@RestController
public class ElectronicLoginController {

    @GetMapping(value = "/login_param")
    @ResponseBody
    public RestResult gotoLoginPage() throws IOException {
        Request request = new Request.Builder()
                .url(UrlConstant.LOGIN_URL).headers(Headers.of(HeadMap.User_Agent))
                .get()
                .build();
        Call call = HttpUtil.OKHTTPCLIENT.newCall(request);
        Response response = call.execute();
        Document document = Jsoup.parse(response.body().string());
        LoginParamDTO loginParamDTO = new LoginParamDTO();
        loginParamDTO.setRandom(document.getElementById("random").attr("value"));
        loginParamDTO.setServerDate(document.getElementById("serverDate").attr("value"));
        loginParamDTO.setUserPin(document.getElementById("userPin").attr("value"));
        loginParamDTO.setIt(document.getElementById("lt").attr("value"));
        loginParamDTO.setExecution(document.getElementById("execution").attr("value"));
        loginParamDTO.setSwLoginFlag(document.getElementById("swLoginFlag").attr("value"));
        return new RestResult().setData(loginParamDTO);
    }


    @PostMapping(value = "/login_token")
    @ResponseBody
    public RestResult loginToken(HttpServletRequest request,
                                 @RequestBody CookieLoginDTO cookieLoginDTO
    ) {
        Map<String, String> headMap = new HashMap<>(16);
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String headerName = e.nextElement();
            System.out.println(headerName + ":" + request.getHeader(headerName));
            if (headerName.equals("host")) {
                continue;
            }
            headMap.put(headerName, request.getHeader(headerName));
        }
        FormBody formBody = new FormBody.Builder()
                .add("dominate", cookieLoginDTO.getDominate())
                .add("t1", cookieLoginDTO.getT1())
                .add("tps", cookieLoginDTO.getTps())
                .add("icCard", cookieLoginDTO.getIcCard())
                .add("certNo", cookieLoginDTO.getCertNo())
                .add("signData", cookieLoginDTO.getSignData())
                .add("random", cookieLoginDTO.getRandom())
                .add("serverDate", cookieLoginDTO.getServerDate())
                .add("userPin", cookieLoginDTO.getUserPin())
                .add("lt", cookieLoginDTO.getLt())
                .add("execution", cookieLoginDTO.getExecution())
                .add("swLoginFlag", cookieLoginDTO.getSwLoginFlag())
                .add("lpid", cookieLoginDTO.getLpid())
                .add("_eventId", cookieLoginDTO.get_eventId())
                .build();
        headMap.putAll(HeadMap.User_Agent);

        try {
            Request request1 = new Request.Builder()
                    .url(UrlConstant.LOGIN_URL_POST)
                    .post(formBody).headers(Headers.of(headMap))
                    .build();
            Call call = HttpUtil.OKHTTPCLIENT.newCall(request1);
            Response response = call.execute();
            System.out.println(response.body().string());
            String body = HttpUtil.post_Request_no_Body(UrlConstant.GET_SESSION, HeadMap.User_Agent);
            SessionDTO sessionDTO = JSON.parseObject(body, SessionDTO.class);
        } catch (Exception e1) {
            throw new ServiceException(RestResult.STATUS_ERROR, "登陆系统失败");
        }
        return new RestResult();
    }


}
