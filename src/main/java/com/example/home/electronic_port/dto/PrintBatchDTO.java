/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.dto;

import lombok.Data;

import java.util.List;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/25 16:02
 * @since 1.0
 **/
@Data
public class PrintBatchDTO {

    private String a;

    private String b;

    private String iEPort;

    private String manualNo;

    private String iEDate;

    private String dDate;

    private String ownerName;

    private String trafMode;

    private String trafName;

    private String billNo;

    private String tradeName;

    private String tradeMode;

    private String cutMode;

    private String payWay;

    private String licenseNo;

    private String tradeCountry;

    private String destinatePort;

    private String districtCode;

    private String apprNo;

    private String transMode;

    private String feeRate;

    private String insurRate;

    private String otherRate;

    private String contrNo;

    private String packNo;

    private String wrapType;

    private String grossWt;

    private String netWt;

    private String contaId;

    private String certMark;

    private String noteS;

    private List<PrintGoodDTO> goods;
}
