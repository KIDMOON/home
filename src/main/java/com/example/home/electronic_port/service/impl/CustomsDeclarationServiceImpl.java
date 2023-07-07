/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.home.dto.SearchBaseDTO;
import com.example.home.dto.TableDTO;
import com.example.home.electronic_port.cache.StaticMapCacheService;
import com.example.home.electronic_port.covert.CustomsDeclarationCardListCovert;
import com.example.home.electronic_port.covert.CustomsDeclarationHeadCovert;
import com.example.home.electronic_port.entity.CustomsDeclarationCardListDO;
import com.example.home.electronic_port.entity.CustomsDeclarationDO;
import com.example.home.electronic_port.entity.CustomsDeclarationHeadDO;
import com.example.home.electronic_port.response.CustomsDeclarationViewDetailVO;
import com.example.home.electronic_port.service.CustomsDeclarationService;
import com.example.home.electronic_port.vo.CustomsDeclarationCardListVO;
import com.example.home.electronic_port.vo.CustomsDeclarationHeadVO;
import com.example.home.entity.BasicEntity;
import com.example.home.interceptor.SecurityContext;
import com.example.home.mapper.CustomsDeclarationCardListMapper;
import com.example.home.mapper.CustomsDeclarationHeadMapper;
import com.example.home.mapper.CustomsDeclarationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/16 下午7:05
 * @since 1.0
 **/
@Service
public class CustomsDeclarationServiceImpl implements CustomsDeclarationService {

    @Autowired
    private CustomsDeclarationMapper customsDeclarationMapper;
    @Autowired
    private CustomsDeclarationHeadMapper customsDeclarationHeadMapper;
    @Autowired
    private CustomsDeclarationCardListMapper customsDeclarationCardListMapper;
    @Autowired
    private StaticMapCacheService staticMapCacheService;

    @Override
    public TableDTO searchCustomsDeclarationDODTO(SearchBaseDTO searchBaseDTO) {
        Page<CustomsDeclarationDO> page = new Page();
        page.setCurrent(searchBaseDTO.getPage());
        page.setSize(searchBaseDTO.getSize());
        LambdaQueryWrapper<CustomsDeclarationDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CustomsDeclarationDO::getBeLong, SecurityContext.getCurrentLoginCode());
        if (StrUtil.isNotEmpty(searchBaseDTO.getSearch())) {
            queryWrapper.and(wrapper -> wrapper.and(wrapper1 -> wrapper1.like(CustomsDeclarationDO::getEntryId, searchBaseDTO.getSearch())))
                    .or(wrapper2 -> wrapper2.like(CustomsDeclarationDO::getIEDate, searchBaseDTO.getSearch()));
        }
        IPage<CustomsDeclarationDO> customsDeclarationDOList = customsDeclarationMapper.selectPage(page, queryWrapper);
        TableDTO tableDTO = new TableDTO();
        tableDTO.setSize(customsDeclarationDOList.getTotal());
        tableDTO.setData(customsDeclarationDOList.getRecords());
        return tableDTO;
    }


    @Override
    public CustomsDeclarationViewDetailVO findCustomsDeclarationViewDetailResponse(String entryId) {
        CustomsDeclarationViewDetailVO customsDeclarationViewDetailVO = new CustomsDeclarationViewDetailVO();
        CustomsDeclarationHeadDO customsDeclarationHeadDO = customsDeclarationHeadMapper.selectOne(new QueryWrapper<CustomsDeclarationHeadDO>().lambda().eq(CustomsDeclarationHeadDO::getBeLong, SecurityContext.getCurrentLoginCode()).eq(CustomsDeclarationHeadDO::getEntryId, entryId));
        if (StrUtil.isNotEmpty(customsDeclarationHeadDO.getTradeMode())) {
            customsDeclarationHeadDO.setTradeMode(customsDeclarationHeadDO.getTradeMode() + "(" + staticMapCacheService.getTradeCode(customsDeclarationHeadDO.getTradeMode()) + ")");
        }
        CustomsDeclarationHeadVO customsDeclarationHeadVO = CustomsDeclarationHeadCovert.INSTANCE.toConvertVO(customsDeclarationHeadDO);
        List<CustomsDeclarationCardListDO> customsDeclarationCardListDOList = customsDeclarationCardListMapper.selectList(new QueryWrapper<CustomsDeclarationCardListDO>().lambda().eq(CustomsDeclarationCardListDO::getBeLong, SecurityContext.getCurrentLoginCode()).eq(CustomsDeclarationCardListDO::getEntryId, entryId));
        List<CustomsDeclarationCardListVO> customsDeclarationCardListVOList = CustomsDeclarationCardListCovert.INSTANCE.toConvertVOList(customsDeclarationCardListDOList);
        for (CustomsDeclarationCardListVO vo : customsDeclarationCardListVOList) {
            if (StrUtil.isNotEmpty(vo.getTradeCurr())) {
                vo.setTradeCode(staticMapCacheService.getMoneyCode(vo.getTradeCurr()));
            }
        }
        customsDeclarationViewDetailVO.setCustomsDeclarationCardListVOList(customsDeclarationCardListVOList);
        customsDeclarationViewDetailVO.setCustomsDeclarationHeadVO(customsDeclarationHeadVO);
        return customsDeclarationViewDetailVO;
    }

    @Override
    @Transactional
    public void printAddOne(String entryId) {
        CustomsDeclarationDO customsDeclarationDO = customsDeclarationMapper.selectOne(new QueryWrapper<CustomsDeclarationDO>().lambda().eq(BasicEntity::getBeLong, SecurityContext.getCurrentLoginCode()).eq(CustomsDeclarationDO::getEntryId, entryId));
        if (customsDeclarationDO != null) {
            Integer num = customsDeclarationDO.getPrintNum();
            if (num == null) {
                num = 0;
            }
            customsDeclarationDO.setPrintNum(num + 1);
            customsDeclarationMapper.updateById(customsDeclarationDO);
        }
    }


}
