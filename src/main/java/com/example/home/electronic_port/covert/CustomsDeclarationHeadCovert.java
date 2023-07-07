/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.covert;

import com.example.home.electronic_port.entity.CustomsDeclarationHeadDO;
import com.example.home.electronic_port.vo.CustomsDeclarationHeadVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/17 下午12:27
 * @since 1.0
 **/
@Mapper
public interface CustomsDeclarationHeadCovert {

    CustomsDeclarationHeadCovert INSTANCE = Mappers.getMapper(CustomsDeclarationHeadCovert.class);

    CustomsDeclarationHeadVO toConvertVO(CustomsDeclarationHeadDO customsDeclarationHeadDO);

}
