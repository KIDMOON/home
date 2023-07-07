/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.interceptor;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 4:56 下午
 * @since 1.0
 **/
public class ClientContext {

    private static ThreadLocal<String> currentClient = new ThreadLocal<>();

    public static void setCurrentClient(String clientId) {
        currentClient.set(clientId);
    }

    public static String getCurrentClient() {
        return currentClient.get();
    }

    public static void clearCurrentClient() {
        currentClient.remove();
    }
}
