/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/11/7 12:24 下午
 * @since 1.0
 **/
@Data
@TableName("software_dw_do")
public class SoftwareDWDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "sub_name")
    private String subName;

    @TableField(value = "version")
    private String version;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "software")
    private String software;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "sort")
    private Integer sort;
}
