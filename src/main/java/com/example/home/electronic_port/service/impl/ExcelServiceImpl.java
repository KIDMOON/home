/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.home.electronic_port.cache.StaticMapCacheService;
import com.example.home.electronic_port.dto.ExcelRequest;
import com.example.home.electronic_port.entity.CustomsDeclarationCardListDO;
import com.example.home.electronic_port.entity.CustomsDeclarationDO;
import com.example.home.electronic_port.entity.CustomsDeclarationHeadDO;
import com.example.home.electronic_port.excel.CustomsDeclarationDownloadData;
import com.example.home.electronic_port.service.ExcelService;
import com.example.home.mapper.CustomsDeclarationCardListMapper;
import com.example.home.mapper.CustomsDeclarationHeadMapper;
import com.example.home.mapper.CustomsDeclarationMapper;
import com.example.home.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/1 3:52 下午
 * @since 1.0
 **/
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private CustomsDeclarationMapper customsDeclarationMapper;
    @Autowired
    private CustomsDeclarationHeadMapper customsDeclarationHeadMapper;
    @Autowired
    private CustomsDeclarationCardListMapper customsDeclarationCardListMapper;
    @Autowired
    private StaticMapCacheService staticMapCacheService;

    @Override
    public List<CustomsDeclarationDownloadData> getExcelList(ExcelRequest excelRequest) {
        List<CustomsDeclarationDownloadData> returnList = new ArrayList<>();
        List<Long> ids = excelRequest.getEntryIdList();
        List<CustomsDeclarationDO> cd = customsDeclarationMapper.selectList(new QueryWrapper<CustomsDeclarationDO>().lambda().in(CustomsDeclarationDO::getEntryId, ids));
        Map<String, CustomsDeclarationDO> cdMap = cd.stream().collect(Collectors.toMap(CustomsDeclarationDO::getEntryId, item -> item));
        List<CustomsDeclarationHeadDO> cdh = customsDeclarationHeadMapper.selectList(new QueryWrapper<CustomsDeclarationHeadDO>().lambda().in(CustomsDeclarationHeadDO::getEntryId, ids));
        Map<String, CustomsDeclarationHeadDO> cdhMap = cdh.stream().collect(Collectors.toMap(CustomsDeclarationHeadDO::getEntryId, item -> item));
        List<CustomsDeclarationCardListDO> customsDeclarationCardListDOList = customsDeclarationCardListMapper.selectList(new QueryWrapper<CustomsDeclarationCardListDO>().lambda().in(CustomsDeclarationCardListDO::getEntryId, ids).orderByAsc(CustomsDeclarationCardListDO::getId));
        for (CustomsDeclarationCardListDO listDO : customsDeclarationCardListDOList) {
            String entryId = listDO.getEntryId();
            CustomsDeclarationDO declarationDO = cdMap.get(entryId);
            CustomsDeclarationHeadDO declarationHeadDO = cdhMap.get(entryId);
            CustomsDeclarationDownloadData data = new CustomsDeclarationDownloadData();
            data.setEntryId(entryId);
            data.setSeqNo(declarationHeadDO.getSeqNo());
            data.setManualNo(declarationHeadDO.getManualNo());
            data.setBillNo(declarationHeadDO.getBillNo());
            data.setIEPort(declarationDO.getIEPort());
            data.setIEDate(DateUtil.dateConvertion(declarationHeadDO.getIEDate()));
            data.setCutMode(declarationHeadDO.getCutMode());
            data.setPayWay(declarationHeadDO.getPayWay());
            data.setTrafMode(declarationHeadDO.getTrafMode());
            data.setOwnerCode(declarationHeadDO.getOwnerCode());
            data.setOwnerName(declarationHeadDO.getOwnerName());
            data.setTradeCo(declarationHeadDO.getTradeCo());
            data.setTradeName(declarationHeadDO.getTradeName());
            data.setAgentCode(declarationHeadDO.getAgentCode());
            data.setAgentName(declarationHeadDO.getAgentName());
            data.setContrNo(declarationHeadDO.getContrNo());
            data.setTradeCountry(declarationHeadDO.getTradeCountry());
            data.setTradeCode(staticMapCacheService.getTradeCode(declarationHeadDO.getTradeMode()));
            data.setTradeMode(declarationHeadDO.getTradeMode());
            data.setTransMode(declarationHeadDO.getTransMode());
            data.setFeeCurr(declarationHeadDO.getFeeCurr());
            data.setFeeRate(declarationHeadDO.getFeeRate());
            data.setInsurCurr(declarationHeadDO.getInsurCurr());
            data.setInsurRate(declarationHeadDO.getInsurRate());
            data.setInsurMark(declarationHeadDO.getInsurMark());
            data.setOtherCurr(declarationHeadDO.getOtherCurr());
            data.setOtherRate(declarationHeadDO.getOtherRate());
            data.setOtherMark(declarationHeadDO.getOtherMark());
            data.setDDate(declarationHeadDO.getDDate());
            data.setStatus(declarationHeadDO.getStatus());
            data.setPrintNum(declarationDO.getPrintNum());
            data.setGNo(listDO.getGNo());
            data.setCodeT(listDO.getCodeT());
            data.setCodeS(listDO.getCodeS());
            data.setGName(listDO.getGName());
            data.setGModel(listDO.getGModel());
            data.setQty1(listDO.getQty1());
            data.setUnit1(listDO.getUnit1());
            data.setGQty(listDO.getGQty());
            data.setGUnit(listDO.getGUnit());
            data.setQty2(listDO.getQty2());
            data.setUnit2(listDO.getUnit2());
            data.setCurrCode(staticMapCacheService.getMoneyCode(listDO.getTradeCurr()));
            data.setTradeCurr(listDO.getTradeCurr());
            data.setDeclPrice(listDO.getDeclPrice());
            data.setDeclTotal(listDO.getDeclTotal());
            data.setUsdPrice(listDO.getUsdPrice());
            data.setRmbPrice(listDO.getRmbPrice());
            data.setZsl(listDO.getZsl());
            data.setTsl(listDO.getTsl());
            data.setDownloadData(DateUtil.dateToString(declarationDO.getCreatedDate()));
            returnList.add(data);
        }
        return returnList;
    }
}
