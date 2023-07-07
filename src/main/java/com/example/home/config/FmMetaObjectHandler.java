/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.home.interceptor.SecurityContext;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/16 下午5:18
 * @since 1.0
 **/
public class FmMetaObjectHandler implements MetaObjectHandler {

    private static final String BE_LONG = "beLong";
    private static final String CREATED_DATE = "createdDate";
    private static final String CREATED_BY = "createdBy";
    private static final String MODIFIER = "modifier";
    private static final String MODIFIED_DATE = "modifiedDate";
    private static final String MODIFIED_BY = "modifiedBy";
    private static final String VERSION = "version";

    private static final String DEFAULT_USER = "system";
    private static final String DEFAULT_REAL_NAME = "系统";

    @Override
    public void insertFill(MetaObject metaObject) {
        String code = SecurityContext.getCurrentLoginCode();
        String loginCode = code == null ? DEFAULT_USER : code;
        Date now = new Date();
        setFieldValByName(BE_LONG, loginCode, metaObject);
        //创建日期
        if (metaObject.hasSetter(CREATED_DATE)) {
            Object value = getFieldValByName(CREATED_DATE, metaObject);
            if (value == null) {
                setInsertFieldValByName(CREATED_DATE, now, metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
