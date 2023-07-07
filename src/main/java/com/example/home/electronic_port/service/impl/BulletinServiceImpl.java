/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.home.dto.SearchBaseDTO;
import com.example.home.dto.TableDTO;
import com.example.home.electronic_port.covert.BulletinCovert;
import com.example.home.electronic_port.dto.BulletinDTO;
import com.example.home.electronic_port.entity.BulletinDO;
import com.example.home.electronic_port.service.BulletinService;
import com.example.home.entity.DeletedEntity;
import com.example.home.mapper.BulletinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/7 5:50 下午
 * @since 1.0
 **/
@Service
public class BulletinServiceImpl extends ServiceImpl<BulletinMapper, BulletinDO> implements BulletinService {

    @Autowired
    private BulletinMapper bulletinMapper;

    @Override
    public TableDTO getTable(SearchBaseDTO dto) {
        Page<BulletinDO> page = new Page();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        LambdaQueryWrapper<BulletinDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByDesc(BulletinDO::getTop, DeletedEntity::getCreatedDate);
        IPage<BulletinDO> bulletinDOIPage = bulletinMapper.selectPage(page, queryWrapper);
        TableDTO tableDTO = new TableDTO();
        tableDTO.setSize(bulletinDOIPage.getTotal());
        tableDTO.setData(bulletinDOIPage.getRecords());
        return tableDTO;
    }

    @Override
    public void saveBulletin(BulletinDTO dto) {
        BulletinDO bulletinDO = BulletinCovert.INSTANCE.toConvertDO(dto);
        saveOrUpdate(bulletinDO);
    }

    @Override
    public void deletedBulletin(Long id) {
        bulletinMapper.deleteById(id);
    }
}
