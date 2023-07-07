package com.example.home;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.example.home.mapper")
@EnableConfigurationProperties
@EnableMethodCache(basePackages = "com.example.home")
@EnableCreateCacheAnnotation
public class HomeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HomeApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HomeApplication.class);
    }

}
