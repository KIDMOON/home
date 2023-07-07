/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.controller;


import lombok.Getter;
import lombok.Setter;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:01 下午
 * @since 1.0
 **/
@Setter
@Getter
public class RestResult {

    public static final String CODE_200 = "200";
    public static final String CODE_400 = "400";
    public static final String CODE_500 = "500";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_WARNING = "warning";
    public static final String STATUS_ERROR = "error";

    private boolean success = true;
    private String code = "200";
    private String status = "success";
    private String message = "";
    private Object data = null;

    public RestResult() {
    }

    public RestResult(String code) {
        this.code = code;
    }

    public RestResult(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public RestResult(String code, String status, String message, Object data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestResult toSuccess() {
        this.success = true;
        return this;
    }

    public RestResult toFail() {
        this.success = false;
        return this;
    }

    public RestResult setData(Object data) {
        this.data = data;
        return this;
    }

    public RestResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public RestResult setStatus(String status) {
        this.status = status;
        return this;
    }

    public RestResult setCode(String code) {
        this.code = code;
        return this;
    }
}
