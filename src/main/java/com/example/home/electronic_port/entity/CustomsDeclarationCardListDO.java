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
import com.example.home.entity.BasicEntity;
import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/8 下午9:40
 * @since 1.0
 * <p>
 * <p>
 * "gNo": "1",
 * "gModel": "3|2|餐桌用|机织|100%棉|||",
 * "seqNo": "000000000568627505",
 * "originCountry": "英国",
 * "gQty": "30",
 * "gName": "餐布",
 * "declPrice": "4.35",
 * "codeT": "6302519000",
 * "qty1": "30",
 * "codeS": "00",
 * "rmbPrice": "           856.00",
 * "qty2": "18",
 * "usdPrice": "           131.00",
 * "unit1": "件",
 * "unit2": "千克",
 * "gUnit": "件",
 * "modNum": "0",
 * "tradeCurr": "美元",
 * "declTotal": "130.5"
 **/
@Data
@TableName("customs_declaration_card_list_do")
public class CustomsDeclarationCardListDO extends BasicEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "mod_num")
    private String modNum;

    /**
     * 报关单编号
     */
    @TableField(value = "entry_id")
    private String entryId;

    /**
     * 商品序号
     */
    @TableField(value = "g_no")
    private String gNo;

    /**
     * 商品规格
     */
    @TableField(value = "g_model")
    private String gModel;

    /**
     * 统一编号
     */
    @TableField(value = "seq_no")
    private String seqNo;

    /**
     * 目的国（地区）
     */
    @TableField(value = "origin_country")
    private String originCountry;

    /**
     * 申报数量
     */
    @TableField(value = "g_qty")
    private String gQty;

    /**
     * 商品名称
     */
    @TableField(value = "g_name")
    private String gName;

    /**
     * 单价
     */
    @TableField(value = "decl_price")
    private String declPrice;

    /**
     * 商品编号
     */
    @TableField(value = "code_t")
    private String codeT;

    /**
     * 法定数量
     */
    @TableField(value = "qty1")
    private String qty1;

    /**
     * 附加编号
     */
    @TableField(value = "code_s")
    private String codeS;

    /**
     * 人民币离岸价
     */
    @TableField(value = "rmb_price")
    private String rmbPrice;

    /**
     * 第二数量
     */
    @TableField(value = "qty2")
    private String qty2;

    /**
     * 统计美元价
     */
    @TableField(value = "usd_price")
    private String usdPrice;

    /**
     * 法定单位
     */
    @TableField(value = "unit1")
    private String unit1;

    /**
     * 第二单位
     */
    @TableField(value = "unit2")
    private String unit2;

    /**
     * 申报单位
     */
    @TableField(value = "g_unit")
    private String gUnit;

    /**
     * 币制
     */
    @TableField(value = "trade_curr")
    private String tradeCurr;

    /**
     * 成交总价
     */
    @TableField(value = "decl_total")
    private String declTotal;

    /**
     * 备案序号
     */
    @TableField(value = "contr_item")
    private String contrItem;

    /**
     * 生产厂家
     */
    @TableField(value = "product_Name")
    private String productName;

    /**
     * 征免方式
     */
    @TableField(value = "duty_mode")
    private String dutyMode;

    /**
     * 运保费
     */
    @TableField(value = "ybf")
    private String ybf;

    /**
     * 征税率
     */
    @TableField(value = "zsl")
    private String zsl;

    /**
     * 退税率
     */
    @TableField(value = "tsl")
    private String tsl;
}
