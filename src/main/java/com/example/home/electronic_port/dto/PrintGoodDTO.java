/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.dto;

import lombok.Data;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/25 16:23
 * @since 1.0
 **/
@Data
public class PrintGoodDTO {

    private String gNo;

    private String codeT;

    private String gName;

    private String gModel;

    private String qty1;

    private String unit1;

    private String qty2;

    private String unit2;

    private String gQty;

    private String gUnit;

    private String originCountry;

    private String declPrice;

    private String declTotal;

    private String tradeCurr;

    private String dutyMode;
}
