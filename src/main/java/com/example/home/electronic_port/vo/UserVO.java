/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.vo;

import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/17 下午5:33
 * @since 1.0
 **/
@Data
public class UserVO {

    private Long userId;

    private String name;

    private String account;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 信用代码
     */
    private String creditCode;

    /**
     * 海关代码
     */
    private String customsCode;

    /**
     * 公司性质   produce 生产企业  trade 外贸企业
     */
    private String companyType;

    /**
     * 口岸
     */
    private String portPwd;

    private Long imageId;
}
