/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.repuest_dto;

import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/12/28 下午4:52
 * @since 1.0
 * <p>
 * <p>
 * modelAndView.addObject("random", document.getElementById("random").attr("value"));
 * modelAndView.addObject("serverDate", document.getElementById("serverDate").attr("value"));
 * modelAndView.addObject("userPin", document.getElementById("userPin").attr("value"));
 * modelAndView.addObject("it", document.getElementById("lt").attr("value"));
 * modelAndView.addObject("execution", document.getElementById("execution").attr("value"));
 * modelAndView.addObject("swLoginFlag", document.getElementById("swLoginFlag").attr("value"));
 **/
@Data
public class LoginParamDTO {

    private String random;
    private String serverDate;
    private String userPin;
    private String it;
    private String execution;
    private String swLoginFlag;
}
