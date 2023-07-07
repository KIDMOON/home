/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.covert;

import com.example.home.electronic_port.entity.CustomsDeclarationCardListDO;
import com.example.home.electronic_port.vo.CustomsDeclarationCardListVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/17 下午12:29
 * @since 1.0
 **/
@Mapper
public interface CustomsDeclarationCardListCovert {

    CustomsDeclarationCardListCovert INSTANCE = Mappers.getMapper(CustomsDeclarationCardListCovert.class);

    CustomsDeclarationCardListDO toConvertVO(CustomsDeclarationCardListDO customsDeclarationCardListDO);

    List<CustomsDeclarationCardListVO> toConvertVOList(List<CustomsDeclarationCardListDO> customsDeclarationCardListDOS);

}
