/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.http;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.home.electronic_port.service.impl.SynchronizeDataServiceImpl.RDTIME;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/12/13 下午4:05
 * @since 1.0
 **/
public class HttpUtil {

    public static final OkHttpClient OKHTTPCLIENT = new OkHttpClient().newBuilder().cookieJar(new HttpCookieJar()).build();

    public static String get_Request(String url, Map<String, String> headMap) throws IOException {
        Map<String, String> map = new HashMap<>(16);
        map.putAll(HeadMap.User_Agent);
        map.putAll(headMap);
        Request request = new Request.Builder()
                .url(url)
                .get().headers(Headers.of(map))
                .build();
        Call call = HttpUtil.OKHTTPCLIENT.newCall(request);
        Response response = call.execute();
        String content = response.body().string();
        return content;
    }


    public static Response get_RequestResponse(String url, Map<String, String> headMap) throws IOException {
        Map<String, String> map = new HashMap<>(16);
        headMap.putAll(HeadMap.User_Agent);
        map.putAll(headMap);
        Request request = new Request.Builder()
                .url(url)
                .get().headers(Headers.of(map))
                .build();
        Call call = HttpUtil.OKHTTPCLIENT.newCall(request);
        Response response = call.execute();
        return response;
    }


    public static String post_Request_no_Body(String url, Map<String, String> headMap) throws IOException {
        Map<String, String> map = new HashMap<>(16);
        map.putAll(HeadMap.User_Agent);
        map.putAll(headMap);
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(HeadMap.JSON_TYPE, "")).headers(Headers.of(map))
                .build();
        Call call = HttpUtil.OKHTTPCLIENT.newCall(request);
        Response response = call.execute();
        String content = response.body().string();
        return content;
    }

    public static String post_Request(String url, Object body, String rdTime) throws IOException {
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(HeadMap.JSON_TYPE, JSON.toJSONString(body));
        Request request3 = new Request.Builder()
                .url(url)
                .post(requestBody).headers(Headers.of(HeadMap.User_Agent)).addHeader(RDTIME, rdTime)
                .build();
        Call call3 = HttpUtil.OKHTTPCLIENT.newCall(request3);
        Response response3 = call3.execute();
        String content = response3.body().string();
        System.out.println(content);
        return content;
    }


    public static String post_Request_sign(String url, Object queryBillRequest, String rdTime, String sign) throws IOException {
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(HeadMap.JSON_TYPE, JSON.toJSONString(queryBillRequest));
        Request request3 = new Request.Builder()
                .url(url)
                .post(requestBody).headers(Headers.of(HeadMap.User_Agent)).addHeader(RDTIME, rdTime).addHeader("sign", sign).addHeader("isVerify", "1")
                .build();
        Call call3 = HttpUtil.OKHTTPCLIENT.newCall(request3);
        Response response3 = call3.execute();
        String content = response3.body().string();
        System.out.println(content);
        return content;
    }


}
