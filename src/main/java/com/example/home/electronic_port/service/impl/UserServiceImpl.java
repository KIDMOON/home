/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.service.impl;

import com.example.home.electronic_port.covert.UserCovert;
import com.example.home.electronic_port.dto.UserDTO;
import com.example.home.electronic_port.service.UserService;
import com.example.home.electronic_port.vo.UserVO;
import com.example.home.entity.UserDO;
import com.example.home.interceptor.SecurityContext;
import com.example.home.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/17 下午5:14
 * @since 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO findLoginUserVO() {
        String loginCode = SecurityContext.getCurrentLoginCode();
        UserDO userDO = userMapper.selectById(loginCode);
        if (userDO == null) {
            return null;
        }
        UserVO userVO = UserCovert.INSTANCE.toConvertVO(userDO);
        userVO.setUserId(userDO.getId());
        return userVO;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        UserDO userDO = userMapper.selectById(userDTO.getUserId());
        if (userDO == null) {
            return;
        }
        userDO.setCompany(userDTO.getCompany());
        userDO.setPortPwd(userDTO.getPortPwd());
        userDO.setMobile(userDTO.getMobile());
        userDO.setContact(userDTO.getContact());
        userDO.setEmail(userDTO.getEmail());
        userDO.setCreditCode(userDTO.getCreditCode());
        userDO.setCustomsCode(userDTO.getCustomsCode());
        userDO.setAccount(userDO.getAccount());
        userMapper.updateById(userDO);
    }
}
