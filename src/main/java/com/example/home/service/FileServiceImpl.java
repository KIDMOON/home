/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.kisso.common.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.home.constant.FileType;
import com.example.home.constant.SystemConstant;
import com.example.home.controller.RestResult;
import com.example.home.dto.FileDTO;
import com.example.home.entity.FileDO;
import com.example.home.exception.ServiceException;
import com.example.home.mapper.FileMapper;
import com.example.home.properties.FileProperties;
import com.example.home.properties.MinioProp;
import com.example.home.util.FileUtils;
import com.example.home.util.MinioUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:18 下午
 * @since 1.0
 **/
@Service
public class FileServiceImpl implements FileService {

    public static final String FILE_HASH_DATE_PATTERN = "yyyyMMddHHmmss";
    public static final int FILE_HASH_RANDOM_LENGTH = 8;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private FileProperties fileProperties;
    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioProp minioProp;
    public static final Snowflake SNOWFLAKE = IdUtil.createSnowflake(1, 1);


    @Override
    public List<FileDTO> uploadFiles(List<MultipartFile> singletonList, String type) {
        if (CollUtil.isEmpty(singletonList)) {
            return new ArrayList<>();
        }
        //now datetime str
        Date now = new Date();
        //path & mkdirs
        String absDir = getAbsDir(now);

        List<FileDTO> fileDTOList = new ArrayList<>();
        for (MultipartFile multipartFile : singletonList) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            //name = 上传文件的实际名称
            String fileName = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            long size = multipartFile.getSize();
            //suffix & judge if allowed upload
            String suffix = FileUtils.getFileSuffix(fileName, false);
            suffix = suffix == null ? null : suffix.toLowerCase();
            if (StrUtil.isBlank(suffix)) {
                throw new ServiceException("40000", RestResult.STATUS_WARNING, "upload blank type file is not allowed");
            }

            String hash = getHash(now, suffix);
            //save to disk
            try {
                minioUtil.uploadFile(multipartFile, minioProp.getBucketName(), absDir + hash);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceException("40000", RestResult.STATUS_ERROR, "upload file failed");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException("40000", RestResult.STATUS_ERROR, "upload file failed");
            }
            //save to db
            FileDO fmFile = new FileDO();
            fmFile.setContentType(contentType);
            fmFile.setName(fileName);
            fmFile.setHash(hash);
            fmFile.setSize(size);
            fmFile.setType(type);
            fmFile.setTime(now);
            fmFile.setSnowflakeId(SNOWFLAKE.nextId());
            fileMapper.insert(fmFile);

            //dto
            FileDTO fileDTO = FileDTO.transferFileToDTO(fmFile);
            fileDTOList.add(fileDTO);
        }
        return fileDTOList;
    }

    @Override
    public void download(Long snowflakeId, HttpServletRequest request, HttpServletResponse response) {
        FileDO fmFile = getFileBySnowflakeId(snowflakeId);
        if (fmFile != null) {
            downloadFile(response, request, fmFile, fmFile.getName());
        }
    }

    @Override
    public FileDO getFileBySnowflakeId(Long snowflakeId) {
        return fileMapper.selectOne(new QueryWrapper<FileDO>().lambda().eq(FileDO::getSnowflakeId, snowflakeId));
    }

    private String getAbsDir(Date now) {
        //path & mkdirs
        String basePath = fileProperties.getUploadPath();
        String absDir = FileUtils.getAbsolutePath(basePath, now);
//        FileUtils.mkdirs(absDir);
        return absDir;
    }

    @Override
    public FileDTO fileDTO(FileDO fileDO){
        FileDTO fileDTO = FileDTO.transferFileToDTO(fileDO);
        return fileDTO;
    }

    @Override
    public void downloadFile(HttpServletResponse response, HttpServletRequest request, FileDO fileDO, String outputfileName) {
        try {
            if (fileDO != null) {
                // 以流的形式下载文件。
                String absDir = getAbsDir(fileDO.getTime());
                InputStream fis = new BufferedInputStream(minioUtil.getObject(minioProp.getBucketName(), absDir + fileDO.getName()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();
                // 设置response的Header
                //火狐浏览器
                if ("FF".equals(getBrowser(request))) {
                    outputfileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(outputfileName.getBytes("UTF-8")))) + "?=";
                    String fileName = new String(outputfileName.getBytes("UTF-8"),
                            "iso-8859-1");
                    response.addHeader(
                            "Content-Disposition", "attachment;filename=" + fileName);
                }else{
                    response.addHeader(
                            "Content-Disposition", "attachment;filename=" + new String(outputfileName.getBytes("gbk"), "iso8859-1"));
                }
                response.addHeader("Content-Length", "" + fileDO.getSize());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileDO findFileSoftware() {
        List<FileDO> fileDOS = fileMapper.selectList(new QueryWrapper<FileDO>().lambda().eq(FileDO::getType, FileType.TYPE_SOFTWARE).orderByDesc(FileDO::getId));
        if (CollUtil.isNotEmpty(fileDOS)) {
            return fileDOS.get(0);
        }
        return null;
    }

    @Override
    public void show(Long snowflakeId, HttpServletRequest request, HttpServletResponse response) {
        FileDO file = getFileBySnowflakeId(snowflakeId);
        try {
            if (file != null) {
                // 以流的形式下载文件。
                String absDir = getAbsDir(file.getTime());
                InputStream fis = new BufferedInputStream(minioUtil.getObject(minioProp.getBucketName(), absDir + file.getHash()));
                ServletOutputStream servletOutputStream = response.getOutputStream();
                IoUtil.copy(fis, servletOutputStream);
                IoUtil.close(fis);
                IoUtil.close(servletOutputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getHash(Date now, String type) {

        SimpleDateFormat sdf = new SimpleDateFormat(FILE_HASH_DATE_PATTERN);
        String nowDateStr = sdf.format(now);

        //path & mkdirs
        String basePath = fileProperties.getUploadPath();
        String absDir = FileUtils.getAbsolutePath(basePath, now);
        FileUtils.mkdirs(absDir);
        String hash = nowDateStr + RandomUtil.getCharacterAndNumber(FILE_HASH_RANDOM_LENGTH) + FileUtils.FONEFILENAME + SystemConstant.FILE_TYPE_STR + type;
        return hash;
    }

    public static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") >= 0){
                return "IE";
            }
            if (UserAgent.indexOf("firefox") >= 0){
                return "FF";
            }
            if (UserAgent.indexOf("safari") >= 0){
                return "SF";
            }
        }
        return null;
    }
}
