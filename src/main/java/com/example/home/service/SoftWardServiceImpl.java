/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.home.dto.DeleteIdListDTO;
import com.example.home.dto.SearchBaseDTO;
import com.example.home.dto.SoftwareDWTableDTO;
import com.example.home.dto.TableDTO;
import com.example.home.entity.SoftwareDWDO;
import com.example.home.mapper.SoftwareDWMapper;
import com.example.home.repuest_dto.SoftWardSaveRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/11/7 1:38 下午
 * @since 1.0
 **/
@Service
public class SoftWardServiceImpl implements SoftWardService {

    @Autowired
    private SoftwareDWMapper softwareDWMapper;
    @Autowired
    private FileService fileService;

    @Override
    public void saveSoftWard(SoftWardSaveRequest softWardSaveRequest) {
        SoftwareDWDO softwareDWDO = new SoftwareDWDO();
        softwareDWDO.setIcon(softWardSaveRequest.getIcon());
        softwareDWDO.setName(softWardSaveRequest.getName());
        softwareDWDO.setSubName(softWardSaveRequest.getSubName());
        softwareDWDO.setVersion(softWardSaveRequest.getVersion());
        softwareDWDO.setUpdateTime(new Date());
        softwareDWDO.setSoftware(softWardSaveRequest.getSoftware());
        softwareDWDO.setSort(softWardSaveRequest.getSort());
        softwareDWMapper.insert(softwareDWDO);
    }

    @Override
    public TableDTO findSoftWardDTO(SearchBaseDTO searchBaseDTO) {
        Page<SoftwareDWDO> page = new Page();
        page.setCurrent(searchBaseDTO.getPage());
        page.setSize(searchBaseDTO.getSize());
        IPage<SoftwareDWDO> softwareDWDOIPage = softwareDWMapper.selectPage(page, new QueryWrapper<SoftwareDWDO>().lambda().orderByDesc(SoftwareDWDO::getSort));


        List<SoftwareDWTableDTO> softwareDWTableDTOList = new ArrayList<>();
        if (CollUtil.isNotEmpty(softwareDWDOIPage.getRecords())) {
            for (SoftwareDWDO softwareDWDO : softwareDWDOIPage.getRecords()) {
                SoftwareDWTableDTO softwareDWTableDTO = new SoftwareDWTableDTO();
                BeanUtils.copyProperties(softwareDWDO, softwareDWTableDTO);
                if (softwareDWDO.getUpdateTime() != null) {
                    softwareDWTableDTO.setTime(DateUtil.formatTime(softwareDWDO.getUpdateTime()));
                }
                softwareDWTableDTOList.add(softwareDWTableDTO);
            }
        }
        TableDTO tableDTO = new TableDTO();
        tableDTO.setSize(softwareDWDOIPage.getTotal());
        tableDTO.setData(softwareDWTableDTOList);
        return tableDTO;
    }

    @Override
    public void deleteSoftwardById(DeleteIdListDTO deleteIdListDTO) {
        if (CollUtil.isNotEmpty(deleteIdListDTO.getIdList())) {
            softwareDWMapper.deleteBatchIds(deleteIdListDTO.getIdList());
        }

    }


}
