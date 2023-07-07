/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 商品代码实体(国家给的官方商品 仅用于展示)
 *
 * @author klaus.jin
 * @date 2021/3/24 10:39 上午
 * @since 1.0
 **/
@Data
@TableName(value = "cmcode")
public class CmcodeDO {

    /**
     * 商品代码
     */
    @TableField(value = "CODE")
    private String code;

    /**
     * 起始时间
     */
    @TableField(value = "ST_DATE")
    private Date stDate;

    /**
     * 结束时间
     */
    @TableField(value = "END_DATE")
    private Date endDate;

    @TableField(value = "ZHCMCODE")
    private String zhcmCode;

    /**
     * 商品名称
     */
    @TableField(value = "NAME")
    private String name;

    /**
     * 单位代码
     */
    @TableField(value = "DWCODE")
    private String dwCode;

    /**
     * 单位
     */
    @TableField(value = "UNIT")
    private String unit;

    /**
     * 基本代码标志
     */
    @TableField(value = "BCFLAG")
    private String bcFlag;

    @TableField(value = "STDFLAG")
    private Integer stdFlag;

    @TableField(value = "DWFLAG")
    private Integer dwFlag;

    /**
     * 税种
     */
    @TableField(value = "SZ")
    private String sz;

    /**
     * 征税率
     */
    @TableField(value = "ZSSL_SET")
    private String zsslSet;

    @TableField(value = "CLDE")
    private Float clde;

    @TableField(value = "CJDL")
    private Float cjdl;

    /**
     * 退税率
     */
    @TableField(value = "TSL")
    private Float tsl;

    @TableField(value = "SPLB")
    private String splb;

    @TableField(value = "TSFLAG")
    private String tsFlag;

    @TableField(value = "NOTE")
    private String note;
}
