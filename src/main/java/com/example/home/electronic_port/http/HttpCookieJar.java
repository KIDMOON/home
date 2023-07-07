/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.http;

import com.example.home.interceptor.SecurityContext;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/12/4 下午12:21
 * @since 1.0
 **/
public class HttpCookieJar implements CookieJar {

    public static final Map<String, List<Cookie>> COOKIESTORE = new ConcurrentHashMap<>(256);

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        String code = SecurityContext.getCurrentLoginCode();
        COOKIESTORE.put(code, list);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        String code = SecurityContext.getCurrentLoginCode();
        List<Cookie> cookies = COOKIESTORE.get(code);
        return cookies != null ? cookies : new ArrayList<>();
    }
}
