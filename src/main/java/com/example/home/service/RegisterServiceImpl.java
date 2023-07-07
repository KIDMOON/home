/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.home.controller.RestResult;
import com.example.home.entity.UserDO;
import com.example.home.exception.ServiceException;
import com.example.home.mapper.UserMapper;
import com.example.home.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/1/3 6:44 下午
 * @since 1.0
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveRegister(UserDO dto) {
        if (null == dto) {
            throw new ServiceException(RestResult.STATUS_ERROR, "参数异常，注册失败！");
        }
        List<UserDO> userDOList = userMapper.selectList(new QueryWrapper<UserDO>().lambda().eq(UserDO::getAccount, dto.getAccount()));
        if (CollUtil.isNotEmpty(userDOList)) {
            throw new ServiceException(RestResult.STATUS_ERROR, "账号重复，已被注册");
        }
        dto.setStatus(0);
        String passWord = Md5Util.md5.digestHex(dto.getPassword());
        dto.setPassword(passWord);
        userMapper.insert(dto);
    }
}
