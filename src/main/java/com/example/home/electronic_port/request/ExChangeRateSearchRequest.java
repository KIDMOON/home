/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.request;

import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/4/10 下午4:21
 * @since 1.0
 **/
@Data
public class ExChangeRateSearchRequest {

    private Integer month;

    private Integer year;

    private String moneyId;
}
