/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import java.text.ParseException;

/**
 * 系统设置 初始化
 *
 * @author klaus.jin
 * @date 2021/3/24 10:06 上午
 * @since 1.0
 **/
public interface InitService {

    void initCmCode() throws ParseException;
}
