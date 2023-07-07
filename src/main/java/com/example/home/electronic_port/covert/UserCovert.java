/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.covert;

import com.example.home.electronic_port.vo.UserVO;
import com.example.home.entity.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/6/5 上午11:50
 * @since 1.0
 **/
@Mapper
public interface UserCovert {

    UserCovert INSTANCE = Mappers.getMapper(UserCovert.class);

    UserVO toConvertVO(UserDO userDO);

}
