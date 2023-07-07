/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.dto;

import com.example.home.electronic_port.entity.CustomsDeclarationDO;
import lombok.Data;

import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/9 下午5:35
 * @since 1.0
 **/
@Data
public class CustomsDeclarationResponse {

    private Integer total;

    private Integer code;

    private List<CustomsDeclarationDO> rows;

}