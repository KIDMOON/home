/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.home.controller.RestResult;
import com.example.home.dto.SessionDTO;
import com.example.home.electronic_port.dto.*;
import com.example.home.electronic_port.entity.CustomsDeclarationCardListDO;
import com.example.home.electronic_port.entity.CustomsDeclarationDO;
import com.example.home.electronic_port.entity.CustomsDeclarationHeadDO;
import com.example.home.electronic_port.excel.CustomsDeclarationDownloadData;
import com.example.home.electronic_port.http.HttpUtil;
import com.example.home.electronic_port.http.KeyUtil;
import com.example.home.electronic_port.http.UrlConstant;
import com.example.home.electronic_port.request.QueryBillRequest;
import com.example.home.electronic_port.request.SendTaxRequest;
import com.example.home.electronic_port.request.ViewDetailRequest;
import com.example.home.electronic_port.service.SessionService;
import com.example.home.electronic_port.service.SynchronizeDataService;
import com.example.home.electronic_port.service.UserService;
import com.example.home.electronic_port.service.WbSocketUtil;
import com.example.home.electronic_port.vo.UserVO;
import com.example.home.exception.ServiceException;
import com.example.home.mapper.CustomsDeclarationCardListMapper;
import com.example.home.mapper.CustomsDeclarationHeadMapper;
import com.example.home.mapper.CustomsDeclarationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/9 上午11:20
 * @since 1.0
 **/
@Service
public class SynchronizeDataServiceImpl implements SynchronizeDataService {
    public int pageSize = 30;

    public final static String RESPONSE_CODE = "responseCode";
    public final static String RESPONSE_DATA = "responseData";
    public final static String RDTIME = "rdtime";


    @Autowired
    private SessionService sessionService;
    @Autowired
    private CustomsDeclarationMapper customsDeclarationMapper;
    @Autowired
    private CustomsDeclarationCardListMapper customsDeclarationCardListMapper;
    @Autowired
    private CustomsDeclarationHeadMapper customsDeclarationHeadMapper;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void synchronizeData(TimeRequest timeRequest) throws IOException {
        String start = timeRequest.getStart();
        String end = timeRequest.getEnd();

        DateTime startDate = DateUtil.parseDate(start);
        DateTime endDate = DateUtil.parseDate(end);
        DateTime endDateCopy = DateUtil.offsetMonth(startDate, 1);

        DateTime endRequest = null;
        DateTime startRequest = new DateTime(startDate.getTime());
        if (endDateCopy.after(endDate)) {
            endRequest = new DateTime(endDate.getTime());
        } else {
            endRequest = new DateTime(endDateCopy.getTime());
        }
        boolean flag = false;
        SessionDTO sessionDTO = sessionService.getSession();

        UserVO userVO = userService.findLoginUserVO();

        String key = HttpUtil.get_Request(UrlConstant.KEY_URL, MapUtil.of(RDTIME, sessionDTO.getRdtime()));
        List<String> keyList = KeyUtil.find(key);
        AES aes = new AES(Mode.CBC, Padding.ZeroPadding, keyList.get(0).getBytes(), keyList.get(1).getBytes());

        List<CustomsDeclarationDO> customsDeclarationDOS = new ArrayList<>();
        while (true) {
            CustomsDeclarationResponse customsDeclarationResponse = getBill(1, startRequest, endRequest, aes, keyList.get(2), sessionDTO);
            if (customsDeclarationResponse != null) {
                if (customsDeclarationResponse.getTotal() == 0) {
                    return;
                }
                customsDeclarationDOS.addAll(customsDeclarationResponse.getRows());
                if (customsDeclarationResponse.getTotal() > pageSize) {
                    int size = customsDeclarationResponse.getTotal() / pageSize;
                    if (customsDeclarationResponse.getTotal() % pageSize > 0) {
                        size++;
                    }
                    for (int i = 2; i <= size; i++) {
                        CustomsDeclarationResponse declarationResponse = getBill(i, startRequest, endRequest, aes, keyList.get(2), sessionDTO);
                        customsDeclarationDOS.addAll(declarationResponse.getRows());
                    }
                }
            }
            if (flag) {
                break;
            }
            startRequest = DateUtil.offsetMonth(startRequest, 1).offset(DateField.DAY_OF_MONTH, 1);
            endRequest = DateUtil.offsetMonth(startRequest, 1);
            if (startRequest.after(endDate)) {
                break;
            }
            /**
             * 最后一次
             */
            if (endRequest.after(endDate)) {
                endRequest = new DateTime(endDate.getTime());
                flag = true;
            }
        }
        for (CustomsDeclarationDO customsDeclarationDO : customsDeclarationDOS) {
            if (StrUtil.isNotEmpty(customsDeclarationDO.getIEDate())) {
                String date = customsDeclarationDO.getIEDate().substring(0, 8);
                customsDeclarationDO.setIEDate(DateUtil.format(DateUtil.parse(date, "yyyyMMdd"), "yyyy-MM-dd"));
            }
        }
        List<CustomsDeclarationViewDetailResponse> customsDeclarationViewDetailResponseList = new ArrayList<>();
        for (CustomsDeclarationDO customsDeclarationDO : customsDeclarationDOS) {
            if (StrUtil.isEmpty(customsDeclarationDO.getEntryId())) {
                continue;
            }
            ViewDetailRequest viewDetailRequest = new ViewDetailRequest(customsDeclarationDO.getEntryId(), "1", customsDeclarationDO.getEncryptStr());
            String content = HttpUtil.post_Request(UrlConstant.VIEW_DETAIL, viewDetailRequest, sessionDTO.getRdtime());
            content = aes.decryptStr(content).replace(keyList.get(2), "");
            CustomsDeclarationViewDetailResponse customsDeclarationViewDetailResponse = parseCustomsDeclarationViewDetailResponse(content, viewDetailRequest, sessionDTO, aes, keyList.get(2));
            if (customsDeclarationViewDetailResponse != null) {
                customsDeclarationViewDetailResponseList.add(customsDeclarationViewDetailResponse);
                CustomsDeclarationHeadDO customsDeclarationHeadDO = customsDeclarationViewDetailResponse.getCardHeadVo();
                if (!(ObjectUtil.equal(userVO.getCustomsCode(), customsDeclarationHeadDO.getTradeCo()) || ObjectUtil.equal(userVO.getCustomsCode(), customsDeclarationHeadDO.getOwnerCode()))) {
                    throw new ServiceException(RestResult.STATUS_ERROR, "该账号海关代码与同步数据不匹配!");
                }
            }
        }
        for (CustomsDeclarationDO customsDeclarationDO : customsDeclarationDOS) {
            customsDeclarationMapper.insert(customsDeclarationDO);
        }

        for (CustomsDeclarationViewDetailResponse customsDeclarationViewDetailResponse : customsDeclarationViewDetailResponseList) {
            String entryId = customsDeclarationViewDetailResponse.getCardHeadVo().getEntryId();
            customsDeclarationCardListMapper.delete(new QueryWrapper<CustomsDeclarationCardListDO>().lambda().eq(CustomsDeclarationCardListDO::getEntryId, entryId));

            customsDeclarationHeadMapper.delete(new QueryWrapper<CustomsDeclarationHeadDO>().lambda().eq(CustomsDeclarationHeadDO::getEntryId, entryId));
            for (CustomsDeclarationCardListDO customsDeclarationCardListDO : customsDeclarationViewDetailResponse.getCardList()) {
                customsDeclarationCardListDO.setEntryId(entryId);
                customsDeclarationCardListMapper.insert(customsDeclarationCardListDO);
            }
            customsDeclarationHeadMapper.insert(customsDeclarationViewDetailResponse.getCardHeadVo());
        }
    }

    @Override
    public void sendTax(String entryId) throws IOException, InterruptedException {
        if (StrUtil.isEmpty(entryId)) {
            return;
        }
        SessionDTO sessionDTO = sessionService.getSession();
        SendTaxRequest sendTaxRequest = new SendTaxRequest();
        sendTaxRequest.setIds(entryId);
        String signContent = sessionDTO.getCards() + "||" + entryId;
        String sign = WbSocketUtil.sign(sessionDTO.getPwd(), signContent);
        String content = HttpUtil.post_Request_sign(UrlConstant.SEND_TAX, sendTaxRequest, sessionDTO.getRdtime(), sign);
        Map<String, String> resultMap = JSON.parseObject(content, Map.class);
        if (!(resultMap.containsKey(RESPONSE_CODE) || resultMap.get(RESPONSE_CODE).equals("30001"))) {
            throw new ServiceException(RestResult.STATUS_ERROR, "发送失败");
        }
    }

    @Override
    public List<PrintBatchDTO> printBatch(ExcelRequest excelRequest) {
        List<PrintBatchDTO> returnList = new ArrayList<>();
        List<Long> ids = excelRequest.getEntryIdList();
        List<CustomsDeclarationHeadDO> cdh = customsDeclarationHeadMapper.selectList(new QueryWrapper<CustomsDeclarationHeadDO>().lambda().in(CustomsDeclarationHeadDO::getEntryId, ids));
        for (CustomsDeclarationHeadDO headDO : cdh) {
            PrintBatchDTO dto = new PrintBatchDTO();
            BeanUtil.copyProperties(headDO, dto);
            List<PrintGoodDTO> goods = new ArrayList<>();
            List<CustomsDeclarationCardListDO> customsDeclarationCardListDOList = customsDeclarationCardListMapper.selectList(
                    new QueryWrapper<CustomsDeclarationCardListDO>().lambda()
                            .eq(CustomsDeclarationCardListDO::getEntryId, headDO.getEntryId())
                            .orderByAsc(CustomsDeclarationCardListDO::getId));
            for (CustomsDeclarationCardListDO cardListDO : customsDeclarationCardListDOList) {
                PrintGoodDTO goodDTO = new PrintGoodDTO();
                BeanUtil.copyProperties(cardListDO, goodDTO);
                goods.add(goodDTO);
            }
            dto.setGoods(goods);
            returnList.add(dto);
        }
        return returnList;
    }

    public CustomsDeclarationResponse getBill(int curPage, Date startRequest, Date endRequest, AES aes, String replaceContent, SessionDTO sessionDTO) throws IOException {
        String startString = DatePattern.PURE_DATE_FORMAT.format(startRequest);
        String endString = DatePattern.PURE_DATE_FORMAT.format(endRequest);
        QueryBillRequest queryBillRequest = new QueryBillRequest(curPage, "1", pageSize, startString, endString);
        String content = HttpUtil.post_Request(UrlConstant.QUERY_ENTRY_ID_BILL, queryBillRequest, sessionDTO.getRdtime());
        content = aes.decryptStr(content).replace(replaceContent, "");
        Map<String, Object> stringObjectMap = JSON.parseObject(content, Map.class);

        if (stringObjectMap.containsKey(RESPONSE_CODE) && (Integer) stringObjectMap.get(RESPONSE_CODE) == 100) {
            JSONObject dataNew = (JSONObject) stringObjectMap.get(RESPONSE_DATA);
            CustomsDeclarationResponse customsDeclarationResponse = JSON.parseObject(dataNew.toJSONString(), CustomsDeclarationResponse.class);
            return customsDeclarationResponse;
        } else if (stringObjectMap.containsKey(RESPONSE_CODE) && (Integer) stringObjectMap.get(RESPONSE_CODE) == 30001) {
            try {
                String signContent = sessionDTO.getCards() + "||" + startString + endString;
                String sign = WbSocketUtil.sign(sessionDTO.getPwd(), signContent);
                String contentNew = HttpUtil.post_Request_sign(UrlConstant.QUERY_ENTRY_ID_BILL, queryBillRequest, sessionDTO.getRdtime(), sign);
                String parseContent = aes.decryptStr(contentNew).replace(replaceContent, "");
                Map<String, Object> parseObject = JSON.parseObject(parseContent, Map.class);
                if (parseObject.containsKey(RESPONSE_CODE) && (Integer) parseObject.get(RESPONSE_CODE) == 100) {
                    JSONObject dataNew = (JSONObject) parseObject.get(RESPONSE_DATA);
                    CustomsDeclarationResponse customsDeclarationResponse = JSON.parseObject(dataNew.toJSONString(), CustomsDeclarationResponse.class);
                    return customsDeclarationResponse;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public CustomsDeclarationViewDetailResponse parseCustomsDeclarationViewDetailResponse(String json, ViewDetailRequest viewDetailRequest, SessionDTO sessionDTO, AES aes, String replaceContent) {
        Map<String, Object> stringObjectMap = JSON.parseObject(json, Map.class);
        if (stringObjectMap.containsKey(RESPONSE_CODE) && (Integer) stringObjectMap.get(RESPONSE_CODE) == 100) {
            JSONObject dataNew = (JSONObject) stringObjectMap.get(RESPONSE_DATA);
            CustomsDeclarationViewDetailResponse customsDeclarationResponse = JSON.parseObject(dataNew.toJSONString(), CustomsDeclarationViewDetailResponse.class);
            return customsDeclarationResponse;
        } else {
            try {
                String content = sessionDTO.getCards() + "||" + viewDetailRequest.getEntryId();
                String sign = WbSocketUtil.sign(sessionDTO.getPwd(), content);
                String response = HttpUtil.post_Request_sign(UrlConstant.VIEW_DETAIL, viewDetailRequest, sessionDTO.getRdtime(), sign);
                String parseContent = aes.decryptStr(response).replace(replaceContent, "");
                Map<String, Object> parseObject = JSON.parseObject(parseContent, Map.class);
                if (parseObject.containsKey(RESPONSE_CODE) && (Integer) parseObject.get(RESPONSE_CODE) == 100) {
                    JSONObject dataNew = (JSONObject) parseObject.get(RESPONSE_DATA);
                    CustomsDeclarationViewDetailResponse customsDeclarationResponse = JSON.parseObject(dataNew.toJSONString(), CustomsDeclarationViewDetailResponse.class);
                    return customsDeclarationResponse;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
