/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 注册信息 实体类
 *
 * @author klaus.jin
 * @date 2021/1/3 6:07 下午
 * @since 1.0
 **/
@Data
@TableName(value = "user")
public class UserDO {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 联系人
     */
    @TableField(value = "contact")
    private String contact;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 公司名称
     */
    @TableField(value = "company")
    private String company;

    /**
     * 信用代码
     */
    @TableField(value = "credit_code")
    private String creditCode;

    /**
     * 海关代码
     */
    @TableField(value = "customs_code")
    private String customsCode;

    /**
     * 公司性质   produce 生产企业  trade 外贸企业
     */
    @TableField(value = "company_type")
    private String companyType;

    /**
     * 软件到期时间
     */
    @TableField(value = "expiration_date")
    private Date expirationDate;

    /**
     * 注册用户的状态 0 待审核 1 试用中 2 合同中 3 已失效
     */
    @TableField(value = "status")
    private Integer status;


    /**
     * 电子口岸密码
     */
    @TableField(value = "port_pwd")
    private String portPwd;

    /**
     * 头像
     */
    @TableField(value = "image_id")
    private Long imageId;

}
