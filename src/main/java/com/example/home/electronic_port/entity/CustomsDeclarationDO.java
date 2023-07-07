/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
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
import com.example.home.entity.BasicEntity;
import lombok.Data;

import java.util.Date;

/**
 * 报关单号
 *
 * @author kid.bian
 * @date 2020/12/18 下午12:09
 * @since 1.0
 **/
@Data
@TableName("customs_declaration_do")
public class CustomsDeclarationDO extends BasicEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "op_mode")
    private String opMode;

    @TableField(value = "flag_dec")
    private String flagDec;

    @TableField(value = "flag_conta")
    private String flagConta;

    /**
     * 打印次数
     */
    @TableField(value = "print_num")
    private Integer printNum;

    /**
     * 出口日期
     */
    @TableField(value = "ie_date")
    private String iEDate;

    /**
     * 出口口岸
     */
    @TableField(value = "ie_port")
    private String iEPort;

    /**
     * 发送时间
     */
    @TableField(value = "date_dec")
    private Date dateDec;

    /**
     * 报关金额
     */
    @TableField(value = "usd_prices")
    private Double usdPrices;

    /**
     * 报关单号
     */
    @TableField(value = "entry_id")
    private String entryId;

    @TableField(value = "encrypt_str")
    private String encryptStr;
}
