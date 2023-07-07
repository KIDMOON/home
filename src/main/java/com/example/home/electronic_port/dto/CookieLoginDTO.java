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
 * @author kid.bian
 * @date 2021/1/9 下午4:03
 * @since 1.0
 **/
@Data
public class CookieLoginDTO {
    private String dominate;
    private String t1;
    private String tps;
    private String icCard;
    private String certNo;
    private String signData;
    private String random;
    private String serverDate;
    private String userPin;
    private String lt;
    private String execution;
    private String swLoginFlag;
    private String lpid;
    private String _eventId;
}
