/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import cn.hutool.core.io.IoUtil;
import com.example.home.controller.RestResult;
import com.example.home.dto.SearchBaseDTO;
import com.example.home.dto.TableDTO;
import com.example.home.electronic_port.dto.ExcelRequest;
import com.example.home.electronic_port.dto.PrintBatchDTO;
import com.example.home.electronic_port.dto.TimeRequest;
import com.example.home.electronic_port.http.HttpUtil;
import com.example.home.electronic_port.http.UrlConstant;
import com.example.home.electronic_port.response.CustomsDeclarationViewDetailVO;
import com.example.home.electronic_port.service.CustomsDeclarationService;
import com.example.home.electronic_port.service.SynchronizeDataService;
import okhttp3.Headers;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 报关单详情
 *
 * @author kid.bian
 * @date 2021/1/9 上午11:13
 * @since 1.0
 **/
@RestController
@RequestMapping("/app/api/data")
public class DataApiController {

    @Autowired
    private SynchronizeDataService synchronizeDataService;
    @Autowired
    private CustomsDeclarationService customsDeclarationService;

    @PostMapping(value = "/synchronize")
    @ResponseBody
    public RestResult synchronizeData(@RequestBody TimeRequest timeRequest) throws IOException {
        synchronizeDataService.synchronizeData(timeRequest);
        return new RestResult();
    }

    @PostMapping("/search/table/customsDeclarationDO")
    public RestResult searchCustomsDeclarationDODTO(@RequestBody SearchBaseDTO searchBaseDTO) {
        TableDTO tableDTO = customsDeclarationService.searchCustomsDeclarationDODTO(searchBaseDTO);
        return new RestResult().setData(tableDTO);
    }

    @GetMapping("/search/detail/{entryId}")
    public RestResult searchCustomsDeclarationDODTO(@PathVariable("entryId") String entryId) {
        CustomsDeclarationViewDetailVO customsDeclarationViewDetailVO = customsDeclarationService.findCustomsDeclarationViewDetailResponse(entryId);
        return new RestResult().setData(customsDeclarationViewDetailVO);
    }

    /**
     * 打印接口
     *
     * @param entryId
     * @param response
     * @throws IOException
     */
    @GetMapping("/print/{entryId}")
    public void printEntryId(@PathVariable("entryId") String entryId, HttpServletResponse response) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(entryId).append(",1");
        Response requestResponse = HttpUtil.get_RequestResponse(UrlConstant.PRINT.replace("{item}", stringBuffer.toString()), new HashMap<>(16));
        Headers headers = requestResponse.headers();
        response.setContentType("application/pdf;charset=UTF-8");
        for (String name : headers.names()) {
            response.setHeader(name, headers.get(name));
        }
        customsDeclarationService.printAddOne(entryId);
        IoUtil.copy(requestResponse.body().byteStream(), response.getOutputStream());
        IoUtil.close(response.getOutputStream());
    }


    /**
     * 打印接口
     *
     * @param entryId
     * @throws IOException
     */
    @GetMapping("/send/tax/{entryId}")
    public RestResult sendTax(@PathVariable("entryId") String entryId) throws IOException, InterruptedException {
        synchronizeDataService.sendTax(entryId);
        return new RestResult();
    }

    @PostMapping("/print/batch")
    public RestResult printBatch(@RequestBody ExcelRequest excelRequest) {
        return new RestResult().setData(synchronizeDataService.printBatch(excelRequest));
    }
}
