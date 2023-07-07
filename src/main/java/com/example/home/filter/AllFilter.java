/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.filter;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.common.CookieHelper;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.kisso.starter.KissoProperties;
import com.example.home.interceptor.ClientContext;
import com.example.home.interceptor.SecurityContext;
import com.example.home.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 4:43 下午
 * @since 1.0
 **/
public class AllFilter implements Filter {

    @Autowired
    private KissoProperties kissoProperties;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 先清空当前线程的Context信息
        SecurityContext.clearCurrentPrincipal();
        SecurityContext.clearCurrentLoginCode();

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie clientCookie = CookieHelper.findCookieByName(request, securityProperties.getClientCookieName());
        if (clientCookie == null) {
            clientCookie = new Cookie(securityProperties.getClientCookieName(), RandomUtil.getCharacterAndNumber(128));
            clientCookie.setPath(kissoProperties.getConfig().getCookiePath());
            clientCookie.setMaxAge(kissoProperties.getConfig().getCookieMaxAge());
            CookieHelper.addHttpOnlyCookie(response, clientCookie);
        }
        ClientContext.setCurrentClient(clientCookie.getValue());

        // SecurityContext : uid
        // 从Cookie中获取用户信息
        SSOToken token = SSOHelper.getSSOToken(request);
        if (token != null) {
            //需要判断clientId与token中的issuer字段是否相等
            String issuer = token.getIssuer();
            if (ClientContext.getCurrentClient().equals(issuer)) {
                //判断token是否过期
                long gap = (System.currentTimeMillis() - token.getTime()) / 1000;
                if (gap < securityProperties.getTokenExpire()) {
                    SecurityContext.setCurrentLoginCode(token.getId());
                } else if (gap - securityProperties.getTokenExpire() < securityProperties.getTokenExtraExpire()) {
                    SecurityContext.setCurrentLoginCode(token.getId());
                    //如果过期不超过SecurityProperties.getTokenExtraExpire，则直接重新续期
                    token.setTime(System.currentTimeMillis());
                    SSOHelper.setCookie(request, response, token, false);
                    //ClientId Cookie也同时重新续期，防止两个cookie时间不对等
                    clientCookie.setPath(kissoProperties.getConfig().getCookiePath());
                    clientCookie.setMaxAge(kissoProperties.getConfig().getCookieMaxAge());
                    CookieHelper.addHttpOnlyCookie(response, clientCookie);
                }
            }
        }
        //继续后续处理
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
