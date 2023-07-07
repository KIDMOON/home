/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.config;

import com.example.home.filter.AllFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 5:16 下午
 * @since 1.0
 **/
@Configuration
public class FilterConfig {

    @Bean
    public AllFilter tokenFilter() {
        return new AllFilter();
    }

    @Bean
    public FilterRegistrationBean tokenFilterBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(tokenFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
