/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:27 下午
 * @since 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {


    /**
     * 文件上传后，本地路径
     */
    private String uploadPath;

    /**
     * 文件上传后，静态文件映射路径
     */
    private String uploadStaticPath;

    /**
     * 允许上传的文件类型，比如 txt,doc,xls,xlsx,pdf,png,jpg,mp3,mp4 等，用逗号(,)隔开
     */
    private Set<String> allowedSuffix = new HashSet<>();

    /**
     * 允许上传的文件大小 : 100kb  100KB  10mb 10MB，没有单位则默认为字节
     */
    private String allowedSize = "20MB";

    /**
     * 是否生成图片缩略图
     */
    private boolean generateThumbnail = false;

    /**
     * 获取文件时，是否需要验证是否登陆
     */
    private boolean checkFileAccessLogin = true;

    /**
     * 获取文件时，需要验证登陆的静态文件路径
     */
    private String accessLoginFilterPattern;

    /**
     * 映射的静态文件路径，主要用于上传文件的静态映射
     * 格式： pattern|path
     */
    private List<String> mappedStaticFiles = new ArrayList<>();
}
