/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.http;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/12/13 下午4:08
 * @since 1.0
 **/
public class UrlConstant {

    public static final String LOGIN_URL = "https://app.singlewindow.cn/cas/login?service=https%3A%2F%2Fi.chinaport.gov.cn%2Fdeskserver%2Fj_spring_cas_security_check";
    public static final String GET_SESSION = "https://i.chinaport.gov.cn/rtxwebserver/sw/rtx/commonQuery/getSession";
    public static final String LOGIN_URL_POST = "https://app.singlewindow.cn/cas/login?service=" + GET_SESSION;
    public static final String QUERY_ENTRY_ID_BILL = "https://i.chinaport.gov.cn/rtxwebserver/sw/rtx/billDownload/queryEntryIdBill";

    public static final String VIEW_DETAIL = "https://i.chinaport.gov.cn/rtxwebserver/sw/rtx/decDetail/viewDetail";

    public static final String KEY_URL = "https://i.chinaport.gov.cn/rtxwebserver/menuType";

    public static final String PRINT = "https://i.chinaport.gov.cn/rtxwebserver/sw/rtx/decDetail/preview/{item}";

    public static final String SEND_TAX = "https://i.chinaport.gov.cn/rtxwebserver/sw/rtx/send1";

}
