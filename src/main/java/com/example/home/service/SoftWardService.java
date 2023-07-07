/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import com.example.home.dto.DeleteIdListDTO;
import com.example.home.dto.SearchBaseDTO;
import com.example.home.dto.TableDTO;
import com.example.home.repuest_dto.SoftWardSaveRequest;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/11/7 1:38 下午
 * @since 1.0
 **/
public interface SoftWardService {

    void saveSoftWard(SoftWardSaveRequest softWardSaveRequest);

    TableDTO findSoftWardDTO(SearchBaseDTO searchBaseDTO);

    void deleteSoftwardById(DeleteIdListDTO deleteIdListDTO);
}
