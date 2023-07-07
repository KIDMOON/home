/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service;

import com.example.home.electronic_port.dto.ExcelRequest;
import com.example.home.electronic_port.excel.CustomsDeclarationDownloadData;

import java.util.List;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/1 3:52 下午
 * @since 1.0
 **/
public interface ExcelService {

    List<CustomsDeclarationDownloadData> getExcelList(ExcelRequest excelRequest);
}
