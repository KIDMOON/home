/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.home.electronic_port.entity.CommodityCodeDO;
import org.apache.ibatis.annotations.Update;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/3/23 3:05 下午
 * @since 1.0
 **/
public interface CommodityCodeMapper extends BaseMapper<CommodityCodeDO> {

    @Update("truncate table commodity_code")
    void deleteAll();
}
