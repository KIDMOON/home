/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.cache;

import cn.hutool.core.util.StrUtil;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.home.electronic_port.entity.MoneyDO;
import com.example.home.electronic_port.entity.TradeWayDO;
import com.example.home.mapper.MoneyMapper;
import com.example.home.mapper.TradeWayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/3 5:11 下午
 * @since 1.0
 **/

@Component
public class StaticMapCacheService {

    @CreateCache(name = "money", cacheType = CacheType.REMOTE)
    public Cache<String, String> moneyCache;
    @CreateCache(name = "trade", cacheType = CacheType.REMOTE)
    public Cache<String, String> tradeCache;

    @Autowired
    private TradeWayMapper tradeWayMapper;
    @Autowired
    private MoneyMapper moneyMapper;

    public String getTradeCode(String key) {
        String data = tradeCache.get(key);
        String dataDefault = data;
        if (dataDefault == null) {
            TradeWayDO tradeWayDO = tradeWayMapper.selectOne(new QueryWrapper<TradeWayDO>().lambda().eq(TradeWayDO::getCn, key));
            if (tradeWayDO != null && StrUtil.isNotEmpty(tradeWayDO.getCode())) {
                dataDefault = tradeWayDO.getCode();
            }
            tradeCache.put(key, dataDefault);
        }
        return dataDefault;
    }

    public String getMoneyCode(String key) {
        String data = moneyCache.get(key);
        String dataDefault = data;
        if (dataDefault == null) {
            MoneyDO moneyDO = moneyMapper.selectOne(new QueryWrapper<MoneyDO>().lambda().eq(MoneyDO::getCn, key));
            if (moneyDO != null && StrUtil.isNotEmpty(moneyDO.getCode())) {
                dataDefault = moneyDO.getCode();
            }
            moneyCache.put(key, dataDefault);
        }
        return dataDefault;
    }
}

