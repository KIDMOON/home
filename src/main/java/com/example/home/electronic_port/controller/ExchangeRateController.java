/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import com.example.home.controller.RestResult;
import com.example.home.electronic_port.request.ExChangeRateSaveRequest;
import com.example.home.electronic_port.request.ExChangeRateSearchRequest;
import com.example.home.electronic_port.service.ExChangeRateService;
import com.example.home.electronic_port.vo.ExChangeRateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 汇率
 *
 * @author kid.bian
 * @date 2021/4/10 下午4:09
 * @since 1.0
 **/
@RestController
@RequestMapping("/app/api/exChangeRate")
public class ExchangeRateController {

    @Autowired
    private ExChangeRateService exChangeRateService;

    @PostMapping(value = "/detail")
    @ResponseBody
    public RestResult findExChangeRate(@RequestBody ExChangeRateSearchRequest exChangeRateSearchRequest) {
        ExChangeRateVO exChangeRateVO = exChangeRateService.findExChangeRate(exChangeRateSearchRequest);
        return new RestResult().setData(exChangeRateVO);
    }


    @PostMapping(value = "/save")
    @ResponseBody
    public RestResult saveExChangeRate(@RequestBody ExChangeRateSaveRequest exChangeRateSaveRequest) {
        exChangeRateService.save(exChangeRateSaveRequest);
        return new RestResult().setMessage("保存成功");
    }


}
