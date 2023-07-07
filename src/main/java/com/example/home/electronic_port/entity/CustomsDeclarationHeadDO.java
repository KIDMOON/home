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
 * @date 2021/1/8 下午8:54
 * @since 1.0
 * <p>
 * cardHeadVo": {
 * "trafMode": "航空运输",
 * "wrapType": "纸箱",
 * "seqNo": "000000000568627505",
 * "tradeName": "无锡任典工贸有限公司",
 * "otherRate": "0",
 * "insurRate": "0",
 * "netWt": "53",
 * "entryId": "223320200001888102",
 * "billNo": "98861412573_TSHA5298628",
 * "tradeMode": "一般贸易",
 * "ownerCode": "3202964555",
 * "ownerName": "无锡任典工贸有限公司",
 * "status": "0",
 * "iePort": "浦东机场",
 * "agentCode": "3121980031",
 * "transMode": "FOB",
 * "declPort": "浦东机场",
 * "tradeCo": "3202964555",
 * "contaId": "",
 * "destinatePort": "英国",
 * "packNo": "6",
 * "ediNote": "E",
 * "grossWt": "56",
 * "rtxType": "000",
 * "dDate": "20201229",
 * "aFlag": "0",
 * "feeRate": "0",
 * "iEDate": "20201230",
 * "districtCode": "扬州",
 * "modNum": "0",
 * "agentName": "上海鑫巴达报关服务有限公司",
 * "preEntryId": "E20200000554427709",
 * "tradeCountry": "英国"
 * }
 **/
@Data
@TableName("customs_declaration_head_do")
public class CustomsDeclarationHeadDO extends BasicEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "status")
    private String status;

    @TableField(value = "a_flag")
    private String aFlag;

    @TableField(value = "mod_num")
    private String modNum;

    @TableField(value = "pre_entry_id")
    private String preEntryId;

    /**
     * 运输方式
     */
    @TableField(value = "traf_mode")
    private String trafMode;

    /**
     * 包装种类
     */
    @TableField(value = "wrap_type")
    private String wrapType;

    /**
     * 统一编号
     */
    @TableField(value = "seq_no")
    private String seqNo;

    /**
     * 境内收发货人名称
     */
    @TableField(value = "trade_name")
    private String tradeName;

    /**
     * 杂费／率
     */
    @TableField(value = "other_rate")
    private String otherRate;

    /**
     * 保险费／率
     */
    @TableField(value = "insur_rate")
    private String insurRate;

    /**
     * 净重
     */
    @TableField(value = "net_wt")
    private String netWt;

    /**
     * 报关单编号
     */
    @TableField(value = "entry_id")
    private String entryId;

    /**
     * 提运单号
     */
    @TableField(value = "bill_no")
    private String billNo;

    /**
     * 贸易方式
     */
    @TableField(value = "trade_mode")
    private String tradeMode;

    /**
     * 生产销售单位编码
     */
    @TableField(value = "owner_code")
    private String ownerCode;

    /**
     * 生产销售单位名称
     */
    @TableField(value = "owner_name")
    private String ownerName;

    /**
     * 出口口岸
     */
    @TableField(value = "ie_port")
    private String iEPort;

    /**
     * 申报单位编码
     */
    @TableField(value = "agent_code")
    private String agentCode;

    /**
     * 成交方式
     */
    @TableField(value = "trans_mode")
    private String transMode;

    /**
     * 申报地海关
     */
    @TableField(value = "decl_port")
    private String declPort;

    /**
     * 境内收发货人编码
     */
    @TableField(value = "trade_co")
    private String tradeCo;

    /**
     * 集装箱号
     */
    @TableField(value = "conta_id")
    private String contaId;

    /**
     * 指运港（地区）
     */
    @TableField(value = "destinate_port")
    private String destinatePort;

    /**
     * 件数
     */
    @TableField(value = "pack_no")
    private String packNo;

    /**
     * 类型备注
     */
    @TableField(value = "edi_note")
    private String ediNote;

    /**
     * 毛重(kg)
     */
    @TableField(value = "gross_wt")
    private String grossWt;

    /**
     * 是否启运港退税
     */
    @TableField(value = "rtx_type")
    private String rtxType;

    /**
     * 申报日期
     */
    @TableField(value = "d_date")
    private String dDate;

    /**
     * 运费／率
     */
    @TableField(value = "fee_rate")
    private String feeRate;

    /**
     * 出口日期
     */
    @TableField(value = "ie_date")
    private String iEDate;

    /**
     * 境内货源地
     */
    @TableField(value = "district_code")
    private String districtCode;

    /**
     * 申报单位名称
     */
    @TableField(value = "agent_name")
    private String agentName;

    /**
     * 运抵国（地区）
     */
    @TableField(value = "trade_country")
    private String tradeCountry;

    /**
     * 备案号
     */
    @TableField(value = "manual_no")
    private String manualNo;

    /**
     * 合同协议号
     */
    @TableField(value = "contr_no")
    private String contrNo;

    /**
     * 运输工具名称
     */
    @TableField(value = "traf_name")
    private String trafName;

    /**
     * 航次
     */
    @TableField(value = "voyage_no")
    private String voyageNo;

    /**
     * 集装箱数量
     */
    @TableField(value = "jzxsl")
    private String jzxsl;

    /**
     * 结汇方式
     */
    @TableField(value = "pay_way")
    private String payWay;

    /**
     * 企业性质
     */
    @TableField(value = "co_owner")
    private String coOwner;

    /**
     * 纳税单位
     */
    @TableField(value = "payment_mark")
    private String paymentMark;

    /**
     * 征免性质
     */
    @TableField(value = "cut_mode")
    private String cutMode;

    /**
     * 运费标志
     */
    @TableField(value = "fee_mark")
    private String feeMark;

    /**
     * 运费币制
     */
    @TableField(value = "fee_curr")
    private String feeCurr;

    /**
     * 许可证号
     */
    @TableField(value = "license_no")
    private String licenseNo;

    /**
     * 保险费标志
     */
    @TableField(value = "insur_mark")
    private String insurMark;

    /**
     * 保险费币制
     */
    @TableField(value = "insur_curr")
    private String insurCurr;

    /**
     * 批准文号
     */
    @TableField(value = "appr_no")
    private String apprNo;

    /**
     * 杂费标志
     */
    @TableField(value = "other_mark")
    private String otherMark;

    /**
     * 杂费币制
     */
    @TableField(value = "other_curr")
    private String otherCurr;

    /**
     * 随附单据
     */
    @TableField(value = "cert_mark")
    private String certMark;

    /**
     * 备注
     */
    @TableField(value = "note_s")
    private String noteS;
}

