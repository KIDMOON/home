/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.controller;

import com.example.home.controller.RestResult;
import com.example.home.dto.SearchBaseDTO;
import com.example.home.dto.TableDTO;
import com.example.home.electronic_port.dto.BulletinDTO;
import com.example.home.electronic_port.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/6/7 5:47 下午
 * @since 1.0
 **/
@RestController
@RequestMapping("/app/api/bulletin")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;

    @PostMapping("/table")
    public RestResult getBulletinTable(@RequestBody SearchBaseDTO dto) {
        TableDTO returnDto = bulletinService.getTable(dto);
        return new RestResult().setData(returnDto);
    }

    /**
     * 新增 编辑
     *
     * @param dto
     * @return
     */
    @PostMapping("/save")
    public RestResult saveBulletin(@RequestBody BulletinDTO dto) {
        bulletinService.saveBulletin(dto);
        return new RestResult().setMessage("保存成功");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public RestResult saveBulletin(@PathVariable("id") Long id) {
        bulletinService.deletedBulletin(id);
        return new RestResult();
    }
}
