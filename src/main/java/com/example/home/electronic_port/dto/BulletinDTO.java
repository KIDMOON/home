/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.dto;

import lombok.Data;

import java.util.Date;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/6/8 下午2:09
 * @since 1.0
 **/
@Data
public class BulletinDTO {


    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 作者
     */
    private String creator;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 是否置顶
     */
    private Boolean top = false;

    private Date createdDate;


}
