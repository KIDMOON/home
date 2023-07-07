/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import com.example.home.electronic_port.entity.CommodityCodeDO;
import com.example.home.electronic_port.service.CommodityCodeService;
import com.example.home.mapper.CmcodeMapper;
import com.example.home.mapper.CommodityCodeMapper;
import com.example.home.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/3/24 10:06 上午
 * @since 1.0
 **/
@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private CommodityCodeMapper commodityCodeMapper;
    @Autowired
    private CmcodeMapper cmcodeMapper;
    @Autowired
    private CommodityCodeService commodityCodeService;

    @Override
    public void initCmCode() throws ParseException {
        //清楚 用于计算的商品代码表
        commodityCodeMapper.deleteAll();
        List<CommodityCodeDO> list = cmcodeMapper.selectForInit(DateUtil.getFirstDay("2018"));
        commodityCodeService.saveBatch(list);
    }
}
