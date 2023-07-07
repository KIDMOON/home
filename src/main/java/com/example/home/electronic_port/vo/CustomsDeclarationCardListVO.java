/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/17 下午12:23
 * @since 1.0
 **/
@Data
public class CustomsDeclarationCardListVO {

    private Long id;

    private String gNo;

    private String gModel;

    private String seqNo;

    private String originCountry;

    private String gQty;

    private String gName;

    private String declPrice;

    private String codeT;

    private String qty1;

    private String codeS;

    private String rmbPrice;

    private String qty2;

    private String usdPrice;

    private String unit1;

    private String unit2;

    private String gUnit;

    private String modNum;

    private String tradeCurr;

    private String declTotal;

    private String entryId;

    private String ybf;

    private String zsl;

    private String tsl;

    private String tradeCode;
}
