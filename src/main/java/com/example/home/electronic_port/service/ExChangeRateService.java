/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.home.electronic_port.entity.ExchangeRateDO;
import com.example.home.electronic_port.request.ExChangeRateSaveRequest;
import com.example.home.electronic_port.request.ExChangeRateSearchRequest;
import com.example.home.electronic_port.vo.ExChangeRateVO;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/3/23 下午5:50
 * @since 1.0
 **/
public interface ExChangeRateService extends IService<ExchangeRateDO> {

    ExChangeRateVO findExChangeRate(ExChangeRateSearchRequest exChangeRateSearchRequest);

    void save(ExChangeRateSaveRequest exChangeRateSaveRequest);
}
