/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.interceptor;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 4:36 下午
 * @since 1.0
 **/
@Data
public class UserPrincipal implements Serializable {

    private Long userId;
    private String loginCode;
    private String realName;
    private String locale;
    private boolean active = true;
    private boolean deleted = false;
    private boolean admin = false;
    private Set<String> permissions;
    private Set<String> roles;
    private String project;
    private Set<Long> projectSet;
    private Long expires;
    private Date createdDateTime;

}
