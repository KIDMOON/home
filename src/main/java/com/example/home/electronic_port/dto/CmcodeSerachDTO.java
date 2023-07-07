/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.dto;

import com.example.home.dto.SearchBaseDTO;
import lombok.Data;

import java.util.Date;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/3/24 11:52 上午
 * @since 1.0
 **/
@Data
public class CmcodeSerachDTO extends SearchBaseDTO {

    private Date outDate;

    private String name;
}
