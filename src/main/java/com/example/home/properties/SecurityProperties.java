/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 4:57 下午
 * @since 1.0
 **/
@Data
@ConfigurationProperties(prefix = "security")
@Component
public class SecurityProperties {


    /**
     * 登陆时，是否验证图片验证码是否正确
     */
    private boolean checkCaptcha = true;

    /**
     * 登陆时，是否验证密码是否正确【用于开发测试】
     */
    private boolean checkPassword = true;

    /**
     * 新建 & 修改密码时，是否启用严格模式
     * 需要根据配置的Regex来判断密码是否合法
     */
    private boolean checkPasswordRegex = false;

    /**
     * 新建 & 修改密码时，是否启用字典匹配模式
     * 根据常用字典库过滤密码
     */
    private boolean checkPasswordDict = false;

    /**
     * 登陆时，是否验证密码错误次数
     */
    private boolean checkPasswordErrorCount = true;

    /**
     * 密码最多错误次数 : 防止爆力破解
     */
    private int maxPasswordErrorCount = 5;

    /**
     * 密码错误验证时间间隔 : 秒
     */
    private int expirePasswordErrorCount = 60;

    /**
     * 密码验证正则表达式
     */
    private String passwordRegex = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_!@#$%^&*`~()-+=]+$)(?![0-9\\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_!@#$%^&*`~()-+=]{10,64}$";

    /**
     * 第一次登陆是否必须修改密码
     */
    private boolean changePasswordFirstLogin = false;

    /**
     * 周期到了是否需要修改密码 : 比如每90天必须修改一次密码
     */
    private boolean changePasswordInCycle = false;

    /**
     * 修改密码时，是否必须不重复，即曾经使用过的密码不可设置（比如12次以内）
     */
    private boolean changePasswordNotRepeat = false;

    /**
     * 一个密码周期 : 天数
     */
    private int passwordCycle = 90;

    /**
     * 配合changePasswordNotRepeat使用，X次以内的密码不允许重复
     */
    private int passwordNotRepeatCount = 12;

    /**
     * 标记client的唯一标识的cookie名称
     */
    private String clientCookieName = "CLIENT_ID";

    /**
     * token有效期，用SSOToken中的time字段来判断
     * 要求cookie的过期时间比较长或者永不过期
     */
    private long tokenExpire = 1 * 24 * 3600;

    /**
     * token的额外时间，即超时不超过此时间，token可以自动获得续期
     */
    private long tokenExtraExpire = 24 * 3600;


    /**
     * 登陆时，是否验证项目系统权限
     */
    private boolean checkAuth = true;
}
