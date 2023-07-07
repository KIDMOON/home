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
 * @date 2020/10/30 4:36 下午
 * @since 1.0
 **/
public class SecurityContext {

    private static ThreadLocal<UserPrincipal> currentPrincipal = new ThreadLocal<>();

    private static ThreadLocal<String> currentLoginCode = new ThreadLocal<>();

    public static UserPrincipal getCurrentPrincipal() {
        return currentPrincipal.get();
    }

    public static void setCurrentPrincipal(UserPrincipal principal) {
        currentPrincipal.set(principal);
    }

    public static void clearCurrentPrincipal() {
        currentPrincipal.remove();
    }

    public static String getCurrentLoginCode() {
        return currentLoginCode.get();
    }

    public static void setCurrentLoginCode(String loginCode) {
        currentLoginCode.set(loginCode);
    }

    public static void clearCurrentLoginCode() {
        currentLoginCode.remove();
    }

}
