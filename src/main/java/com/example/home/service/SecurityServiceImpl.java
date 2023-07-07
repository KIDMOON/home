/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.service;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.home.controller.RestResult;
import com.example.home.dto.LoginDTO;
import com.example.home.entity.UserDO;
import com.example.home.exception.ServiceException;
import com.example.home.interceptor.ClientContext;
import com.example.home.mapper.UserMapper;
import com.example.home.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 8:42 下午
 * @since 1.0
 **/
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(HttpServletRequest request, HttpServletResponse response, LoginDTO loginDTO) {
        //判断LoginDTO参数是否完整
        if (loginDTO == null
                || StrUtil.isBlank(loginDTO.getLoginCode())
                || StrUtil.isBlank(loginDTO.getLoginPwd())) {
            throw new ServiceException(RestResult.STATUS_ERROR, "参数异常");
        }
        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>().lambda().eq(UserDO::getAccount, loginDTO.getLoginCode()));
        if (userDO == null) {
            throw new ServiceException(RestResult.STATUS_ERROR, "用户不存在");
        }
        if (userDO.getStatus() == null || userDO.getStatus() != 2) {
            throw new ServiceException(RestResult.STATUS_ERROR, "账号不在合同期,请联系相关人员");
        }
        String loginPwd = Md5Util.md5.digestHex(loginDTO.getLoginPwd());
        if (!loginPwd.equals(userDO.getPassword())) {
            throw new ServiceException(RestResult.STATUS_ERROR, "密码错误");
        }

        if (userDO.getExpirationDate()!=null){
            if (userDO.getExpirationDate().before(new Date())){
                throw new ServiceException(RestResult.STATUS_ERROR, "已过期,请联系相关人员");
            }
        }
        StpUtil.login(userDO.getId());
        return true;
    }

}
