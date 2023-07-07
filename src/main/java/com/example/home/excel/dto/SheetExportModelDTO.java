/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.excel.dto;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.write.handler.WriteHandler;
import lombok.Data;

import java.util.List;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/5/6 10:28 上午
 * @since 1.0
 **/
@Data
public class SheetExportModelDTO {

    public SheetExportModelDTO(String sheetName, int sheetNo, int headLineMun, Class modelClass, List<? extends BaseRowModel> modeles) {
        this.sheetName = sheetName;
        this.sheetNo = sheetNo;
        this.headLineMun = headLineMun;
        this.modelClass = modelClass;
        this.modeles = modeles;
    }

    public SheetExportModelDTO(String sheetName, int sheetNo, int headLineMun, Class modelClass, Object object) {
        this.sheetName = sheetName;
        this.sheetNo = sheetNo;
        this.headLineMun = headLineMun;
        this.modelClass = modelClass;
        this.object = object;
    }

    /**
     * sheet名称
     */
    private String sheetName;

    /**
     * 当前sheet 是第几个 重1开始
     */
    private int sheetNo;

    /**
     * 数据重第几行开始
     */
    private int headLineMun;

    /**
     * 当前sheet中导出、导入数据的 模型对象class
     */
    private Class modelClass;

    /**
     * 当前sheet中导出、导入数据的 模型对象
     */
    List<? extends BaseRowModel> modeles;

    /**
     * 自定义头
     */
    List<List<String>> head;


    /**
     * 自定义写handler,特殊行单元格处理等
     */
    List<WriteHandler> handlers;

    /**
     * 是否忽略自动列宽
     */
    private Boolean escapeColumnMatch;

    private Object object;
}
