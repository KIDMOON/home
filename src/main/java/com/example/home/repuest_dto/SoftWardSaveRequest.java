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
 * @date 2020/11/7 1:32 下午
 * @since 1.0
 **/
@Data
public class SoftWardSaveRequest {

    private String name;

    private String subName;

    private String version;

    private String icon;

    private String software;

    private Integer sort;

}
