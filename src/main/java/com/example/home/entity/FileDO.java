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
 * @date 2020/10/30 12:07 下午
 * @since 1.0
 **/
@Data
@TableName("file_do")
public class FileDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "hash")
    private String hash;

    @TableField(value = "thumb")
    private String thumb;

    @TableField(value = "type")
    private String type;

    @TableField(value = "content_type")
    private String contentType;

    @TableField(value = "size")
    private Long size;

    @TableField(value = "time")
    private Date time;

    @TableField(value = "snowflake_id")
    private Long snowflakeId;
}
