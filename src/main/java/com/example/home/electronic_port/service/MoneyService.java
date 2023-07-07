/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service;

import com.alicp.jetcache.anno.Cached;
import com.example.home.electronic_port.entity.MoneyDO;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/6/5 上午11:37
 * @since 1.0
 **/
public interface MoneyService {

    @Cached(name = "userCache.", timeUnit = TimeUnit.MINUTES, expire = 3600)
    List<MoneyDO> findAllMoney();
}
