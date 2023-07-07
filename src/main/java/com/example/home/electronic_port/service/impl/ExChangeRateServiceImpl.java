/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.home.electronic_port.constant.Constant;
import com.example.home.electronic_port.covert.ExChangeRateCovert;
import com.example.home.electronic_port.entity.ExchangeRateDO;
import com.example.home.electronic_port.request.ExChangeRateSaveRequest;
import com.example.home.electronic_port.request.ExChangeRateSearchRequest;
import com.example.home.electronic_port.service.ExChangeRateService;
import com.example.home.electronic_port.vo.ExChangeRateVO;
import com.example.home.entity.BasicEntity;
import com.example.home.interceptor.SecurityContext;
import com.example.home.mapper.ExchangeRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/4/10 下午4:16
 * @since 1.0
 **/
@Service
public class ExChangeRateServiceImpl extends ServiceImpl<ExchangeRateMapper, ExchangeRateDO> implements ExChangeRateService {

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Override
    public ExChangeRateVO findExChangeRate(ExChangeRateSearchRequest exChangeRateSearchRequest) {
        ExchangeRateDO exchangeRateDO = exchangeRateMapper.selectOne(new QueryWrapper<ExchangeRateDO>().lambda().eq(ExchangeRateDO::getMonth, exChangeRateSearchRequest.getMonth()).eq(ExchangeRateDO::getYear, exChangeRateSearchRequest.getYear()).eq(ExchangeRateDO::getMoneyId, exChangeRateSearchRequest.getMoneyId()).eq(BasicEntity::getBeLong, SecurityContext.getCurrentLoginCode()));
        if (exchangeRateDO == null) {
            exchangeRateDO = exchangeRateMapper.selectOne(new QueryWrapper<ExchangeRateDO>().lambda().eq(ExchangeRateDO::getMonth, exChangeRateSearchRequest.getMonth()).eq(ExchangeRateDO::getYear, exChangeRateSearchRequest.getYear()).eq(ExchangeRateDO::getMoneyId, exChangeRateSearchRequest.getMoneyId()).eq(BasicEntity::getBeLong, Constant.ADMIN_ID));
        }
        if (exchangeRateDO == null) {
            ExchangeRateDO rateDO = new ExchangeRateDO();
            rateDO.setBeLong(SecurityContext.getCurrentLoginCode());
            rateDO.setMoneyId(exChangeRateSearchRequest.getMoneyId());
            rateDO.setYear(exChangeRateSearchRequest.getYear());
            rateDO.setMonth(exChangeRateSearchRequest.getMonth());
            exchangeRateMapper.insert(rateDO);
            exchangeRateDO = rateDO;
        }
        return ExChangeRateCovert.INSTANCE.toConvertVO(exchangeRateDO);
    }

    @Override
    public void save(ExChangeRateSaveRequest exChangeRateSaveRequest) {
        ExchangeRateDO exchangeRateDO = exchangeRateMapper.selectOne(new QueryWrapper<ExchangeRateDO>().lambda().eq(ExchangeRateDO::getMonth, exChangeRateSaveRequest.getMonth()).eq(ExchangeRateDO::getYear, exChangeRateSaveRequest.getYear()).eq(ExchangeRateDO::getMoneyId, exChangeRateSaveRequest.getMoneyId()).eq(BasicEntity::getBeLong, SecurityContext.getCurrentLoginCode()));
        if (exchangeRateDO == null) {
            exchangeRateDO = new ExchangeRateDO();
        }
        BeanUtil.copyProperties(exChangeRateSaveRequest, exchangeRateDO);
        saveOrUpdate(exchangeRateDO);
    }

}
