/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.home.dto.TableDTO;
import com.example.home.electronic_port.dto.CmcodeSerachDTO;
import com.example.home.electronic_port.entity.CmcodeDO;
import com.example.home.electronic_port.service.CmcodeService;
import com.example.home.mapper.CmcodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品代码相关
 *
 * @author klaus.jin
 * @date 2021/3/23 10:30 上午
 * @since 1.0
 **/
@Service
public class CmcodeServiceImpl implements CmcodeService {

    @Autowired
    private CmcodeMapper cmcodeMapper;

    @Override
    public TableDTO getCmcodeTable(CmcodeSerachDTO dto) {
        Page<CmcodeDO> page = new Page();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        Map<String, Object> condition = buildCondition(dto);

        IPage<CmcodeDO> cmcodeIPage = cmcodeMapper.selectTableByCondition(page, condition);
        TableDTO tableDTO = new TableDTO();
        tableDTO.setSize(cmcodeIPage.getTotal());
        tableDTO.setData(cmcodeIPage.getRecords());
        return tableDTO;
    }

    private Map<String, Object> buildCondition(CmcodeSerachDTO dto) {
        Map<String, Object> map = new HashMap<>(16);
        if (null != dto.getOutDate()) {
            map.put("outDate", dto.getOutDate());
        }
        if (StrUtil.isNotEmpty(dto.getName())) {
            map.put("name", dto.getName());
        }
        return map;
    }
}
