/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/5/18 下午8:35
 * @since 1.0
 **/
@Data
public class CustomsDeclarationDownloadData {

    @ExcelProperty("报关单号")
    private String entryId;

    @ExcelProperty("统一编号")
    private String seqNo;

    @ExcelProperty("备案号")
    private String manualNo;

    @ExcelProperty("提运单号")
    private String billNo;

    @ExcelProperty("出口口岸")
    private String iEPort;

    @ExcelProperty("出口日期")
    private String iEDate;

    @ExcelProperty("征免性质")
    private String cutMode;

    @ExcelProperty("结汇方式")
    private String payWay;

    @ExcelProperty("运输方式")
    private String trafMode;

    @ExcelProperty("经营单位编号")
    private String ownerCode;

    @ExcelProperty("经营单位")
    private String ownerName;

    @ExcelProperty("发货单位编号")
    private String tradeCo;

    @ExcelProperty("发货单位")
    private String tradeName;

    @ExcelProperty("申报单位编号")
    private String agentCode;

    @ExcelProperty("申报单位")
    private String agentName;

    @ExcelProperty("合同协议号")
    private String contrNo;

    @ExcelProperty("运抵国")
    private String tradeCountry;

    @ExcelProperty("贸易方式编码")
    private String tradeCode;

    @ExcelProperty("贸易方式")
    private String tradeMode;

    @ExcelProperty("成交方式")
    private String transMode;

    @ExcelProperty("运费币制")
    private String feeCurr;

    @ExcelProperty("运费/率")
    private String feeRate;

    @ExcelProperty("保险费币制")
    private String insurCurr;

    @ExcelProperty("保险费/率")
    private String insurRate;

    @ExcelProperty("保险费标记")
    private String insurMark;

    @ExcelProperty("杂费币制")
    private String otherCurr;

    @ExcelProperty("杂费/率")
    private String otherRate;

    @ExcelProperty("杂费标记")
    private String otherMark;

    @ExcelProperty("申报日期")
    private String dDate;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("打印次数")
    private Integer printNum;

    @ExcelProperty("项号")
    private String gNo;

    @ExcelProperty("商品编号")
    private String codeT;

    @ExcelProperty("附加编号")
    private String codeS;

    @ExcelProperty("商品名称")
    private String gName;

    @ExcelProperty("规格型号")
    private String gModel;

    @ExcelProperty("法定数量")
    private String qty1;

    @ExcelProperty("法定单位")
    private String unit1;

    @ExcelProperty("第一数量")
    private String gQty;

    @ExcelProperty("第一单位")
    private String gUnit;

    @ExcelProperty("第二数量")
    private String qty2;

    @ExcelProperty("第二单位")
    private String unit2;

    @ExcelProperty("币制编码")
    private String currCode;

    @ExcelProperty("币制")
    private String tradeCurr;

    @ExcelProperty("单价")
    private String declPrice;

    @ExcelProperty("成交总价")
    private String declTotal;

    @ExcelProperty("统计美元价")
    private String usdPrice;

    @ExcelProperty("人民币离岸价")
    private String rmbPrice;

    @ExcelProperty("征税率")
    private String zsl;

    @ExcelProperty("退税率")
    private String tsl;

    @ExcelProperty("下载日期")
    private String downloadData;
}
