/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.util;

import cn.hutool.crypto.digest.MD5;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/17 下午5:02
 * @since 1.0
 **/
public class Md5Util {

    public static final MD5 md5 = MD5.create();

    {
        md5.setSalt("123456".getBytes());
    }

    public static void main(String[] args) {

        System.out.println(md5.digestHex("123456"));
    }
}
