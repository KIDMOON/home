/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.home.electronic_port.entity.CommodityCodeDO;
import com.example.home.electronic_port.service.CommodityCodeService;
import com.example.home.mapper.CommodityCodeMapper;
import org.springframework.stereotype.Service;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/3/24 2:52 下午
 * @since 1.0
 **/
@Service
public class CommodityCodeServiceImpl extends ServiceImpl<CommodityCodeMapper, CommodityCodeDO> implements CommodityCodeService {
}
