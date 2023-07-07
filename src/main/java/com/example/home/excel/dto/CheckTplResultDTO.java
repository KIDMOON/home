/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.excel.dto;

import lombok.Data;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/5/6 10:27 上午
 * @since 1.0
 **/
@Data
public class CheckTplResultDTO {

    /**
     * 导入数据是否验证通过
     */
    private Boolean pass;

    /**
     * 验证不通过，生成包含错误信息excel  模型对象信息
     */
    SheetExportModelDTO sheetExportModelDTO;

    /**
     * 需要返回的数据
     */
    Object data;

    /**
     * 是否填充信息，填充在具体的excel，而不是写
     */
    private Boolean fill;
}
