/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/3 6:05 下午
 * @since 1.0
 **/
@Data
@TableName(value = "trade_way_do")
public class TradeWayDO {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "code")
    private String code;

    @TableField(value = "cn")
    private String cn;

    @TableField(value = "en")
    private String en;
}
