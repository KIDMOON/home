/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.home.entity.DeletedEntity;
import lombok.Data;

import java.util.Date;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/4 3:44 下午
 * @since 1.0
 **/
@Data
@TableName(value = "bulletin_do")
public class BulletinDO extends DeletedEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 公告标题
     */
    @TableField("title")
    private String title;

    /**
     * 作者
     */
    @TableField("creator")
    private String creator;

    /**
     * 公告内容
     */
    @TableField("content")
    private String content;

    /**
     * 是否置顶
     */
    @TableField("top")
    private Boolean top = false;
}
