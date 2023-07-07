/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import com.example.home.annotation.Action;
import com.example.home.annotation.Login;
import com.example.home.constant.FileType;
import com.example.home.controller.RestResult;
import com.example.home.dto.FileDTO;
import com.example.home.dto.HashDTO;
import com.example.home.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 11:57 上午
 * @since 1.0
 **/
@RestController
@RequestMapping("/app/api")
public class ApiFileController {

    @Autowired
    private FileService fileService;

    @Login(action = Action.Skip)
    @PostMapping("/upload")
    public RestResult uploadFile(@RequestParam("file") MultipartFile file) {
        List<FileDTO> uploadDTOList = fileService.uploadFiles(Collections.singletonList(file), FileType.TYPE_SOFTWARE);
        return new RestResult().setData(uploadDTOList.size() > 0 ? uploadDTOList.get(0) : null);
    }

    @PostMapping("/download/software")
    public void download(@RequestBody HashDTO hashDTO, HttpServletRequest request, HttpServletResponse response) {
        fileService.download(hashDTO.getSnowflakeId(), request, response);
    }


    @Login(action = Action.Skip)
    @GetMapping("/show/{snowflakeId}")
    public void showPicture(@PathVariable("snowflakeId") Long snowflakeId, HttpServletRequest request, HttpServletResponse response) {
        fileService.show(snowflakeId, request, response);
    }

}
