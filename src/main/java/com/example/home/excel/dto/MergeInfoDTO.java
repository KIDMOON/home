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
 * @date 2021/4/30 12:22 下午
 * @since 1.0
 **/
@Data
public class MergeInfoDTO {

    private Integer firstColumnIndex;
    private Integer lastColumnIndex;
    private Integer firstRowIndex;
    private Integer lastRowIndex;

    public MergeInfoDTO() {
    }

    public MergeInfoDTO(Integer firstColumnIndex, Integer lastColumnIndex, Integer firstRowIndex, Integer lastRowIndex) {
        this.firstColumnIndex = firstColumnIndex;
        this.lastColumnIndex = lastColumnIndex;
        this.firstRowIndex = firstRowIndex;
        this.lastRowIndex = lastRowIndex;
    }
}
