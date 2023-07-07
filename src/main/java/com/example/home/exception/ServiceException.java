/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.exception;

import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:44 下午
 * @since 1.0
 **/
@Data
public class ServiceException extends RuntimeException {
    private String code;
    private String status;
    private String message;

    public ServiceException(String code, String status, String message) {
        super(message);
        this.code = code;
        this.status = status;
        this.message = message;
    }


    public ServiceException(String status, String message) {
        super(message);
        this.code = "300";
        this.status = status;
        this.message = message;
    }


}
