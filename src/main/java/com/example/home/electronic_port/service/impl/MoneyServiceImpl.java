/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.home.electronic_port.entity.MoneyDO;
import com.example.home.electronic_port.service.MoneyService;
import com.example.home.mapper.MoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/6/5 上午11:37
 * @since 1.0
 **/
@Service
public class MoneyServiceImpl implements MoneyService {

    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public List<MoneyDO> findAllMoney() {
        return moneyMapper.selectList(new QueryWrapper<>());
    }
}