/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.dto;

import com.example.home.entity.FileDO;
import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:05 下午
 * @since 1.0
 **/
@Data
public class FileDTO {

    private String name;
    private String hash;
    private String thumb;
    private String type;
    private String contentType;
    private Long snowflakeId;
    private Long size;

    public static FileDTO transferFileToDTO(FileDO fmFile) {
        if (fmFile == null) {
            return null;
        }

        FileDTO dto = new FileDTO();
        dto.setName(fmFile.getName());
        dto.setHash(fmFile.getHash());
        dto.setThumb(fmFile.getThumb());
        dto.setType(fmFile.getType());
        dto.setSize(fmFile.getSize());
        dto.setSnowflakeId(fmFile.getSnowflakeId());
        dto.setContentType(fmFile.getContentType());
        return dto;
    }

}
