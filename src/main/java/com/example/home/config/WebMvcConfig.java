/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.home.interceptor.PermissionInterceptor;
import com.example.home.interceptor.SecurityInterceptor;
import com.example.home.properties.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 5:14 下午
 * @since 1.0
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private FileProperties fileProperties;

//    @Bean
//    public SecurityInterceptor contextInterceptor() {
//        return new SecurityInterceptor();
//    }

//    @Bean
//    public PermissionInterceptor permissionInterceptor() {
//        return new PermissionInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //1.加入的顺序就是拦截器执行的顺序
        //2.按顺序执行所有拦截器的preHandle
        //3.所有的preHandle 执行完再执行全部postHandle 最后是afterCompletion
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/app/api/**");

    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/files/**").addResourceLocations("classpath:/static/");
//
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 清除默认 Json 转换器
//        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 添加fastjson的配置信息，比如：是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue);       // 在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> supportedMediaTypes = new ArrayList<>();

        supportedMediaTypes.add(MediaType.ALL);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        // 将converter添加到converters中
        converters.add(fastConverter);
        // 添加 StringHttpMessageConverter，解决中文乱码问题
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(stringHttpMessageConverter);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        List<String> mappedStaticFiles = fileProperties.getMappedStaticFiles();
        if (mappedStaticFiles != null && mappedStaticFiles.size() > 0) {
            for (String mappedStaticFile : mappedStaticFiles) {
                //配置文件格式为 pattern | path
                String[] args = mappedStaticFile.split("\\|");
                if (args.length == 2) {
                    registry.addResourceHandler(StrUtil.trim(args[0])).addResourceLocations(StrUtil.trim(args[1]));
                }
            }
        }
    }

}
