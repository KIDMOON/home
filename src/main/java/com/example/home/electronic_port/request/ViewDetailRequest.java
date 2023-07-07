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
 * @date 2021/1/9 下午6:45
 * @since 1.0
 * <p>
 * {"entryId":"222920200004430353","decType":"1"}
 **/
@Data
public class ViewDetailRequest {

    private String encryptStr;

    private String entryId;

    private String decType;

    public ViewDetailRequest(String entryId, String decType, String encryptStr) {
        this.entryId = entryId;
        this.decType = decType;
        this.encryptStr = encryptStr;
    }
}
