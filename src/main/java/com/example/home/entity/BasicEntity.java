/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 基础实体类
 *
 * @author klaus.jin
 * @date 2021/1/3 6:28 下午
 * @since 1.0
 **/
@Data
public class BasicEntity {

    @TableField(value = "be_long", strategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    protected String beLong;

    @TableField(value = "created_date", strategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    protected Date createdDate;
}
