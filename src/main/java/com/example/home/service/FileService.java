/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import com.example.home.dto.FileDTO;
import com.example.home.entity.FileDO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:15 下午
 * @since 1.0
 **/
public interface FileService {

    List<FileDTO> uploadFiles(List<MultipartFile> singletonList, String type);

    void download(Long snowflakeId, HttpServletRequest request, HttpServletResponse response);

    FileDO getFileBySnowflakeId(Long snowflakeId);

    FileDTO fileDTO(FileDO fileDO);

    void downloadFile(HttpServletResponse response, HttpServletRequest request, FileDO fileDO, String outputfileName);


    void show(Long snowflakeId, HttpServletRequest request, HttpServletResponse response);
}


