/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import com.example.home.controller.RestResult;
import com.example.home.dto.TableDTO;
import com.example.home.electronic_port.dto.CmcodeSerachDTO;
import com.example.home.electronic_port.service.CmcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/3/24 11:45 上午
 * @since 1.0
 **/
@RestController
@RequestMapping("/app/api/cmcode")
public class CmcodeController {

    @Autowired
    private CmcodeService cmcodeService;

    @PostMapping("/table")
    @ResponseBody
    public RestResult getCmcodeTable(@RequestBody CmcodeSerachDTO dto) {
        TableDTO returnDto = cmcodeService.getCmcodeTable(dto);
        return new RestResult().setData(returnDto);
    }

}
