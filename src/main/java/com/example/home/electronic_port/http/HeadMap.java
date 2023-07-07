/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.http;

import cn.hutool.core.map.MapUtil;
import okhttp3.MediaType;

import java.util.Map;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/12/13 下午3:57
 * @since 1.0
 **/
public class HeadMap {

    public static final Map<String,String> User_Agent=MapUtil.of("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");


    public static final MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");
}
