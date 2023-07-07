/*
 * Copyright 2006-2022 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2022/9/29 12:21 PM
 * @since 1.0
 **/
public class SM4_Context {


    public int mode;

    public int[] sk;

    public boolean isPadding;

    public SM4_Context() {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new int[32];
    }
}
