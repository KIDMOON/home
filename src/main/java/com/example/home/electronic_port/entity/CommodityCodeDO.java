/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 商品代码实体(仅用于计算)
 *
 * @author klaus.jin
 * @date 2021/3/23 10:38 上午
 * @since 1.0
 **/
@Data
@TableName(value = "commodity_code")
public class CommodityCodeDO {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 商品代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 征税率
     */
    @TableField(value = "tax_rate")
    private String taxRate;

    /**
     * 退税率
     */
    @TableField(value = "rebate_rate")
    private Double rebateRate;

    /**
     * 起始时间
     */
    @TableField(value = "start_date")
    private Date startDate;

    /**
     * 结束时间
     */
    @TableField(value = "end_date")
    private Date endDate;
}
