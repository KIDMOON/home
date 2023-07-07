package com.example.home.electronic_port.service;/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

import lombok.Data;

import java.util.Map;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/1 12:10 上午
 * @since 1.0
 **/
@Data
public class Param {

    private String _method;

    private Map<String, Object> args;

    private Long _id;

}
