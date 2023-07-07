/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.excel.dto;

import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/5/6 10:23 上午
 * @since 1.0
 **/
@Data
public class SheetImportModelDTO {

    public SheetImportModelDTO() {

    }

    public SheetImportModelDTO(String fileHash, int sheetNo, int headLineMun, Class modelClass, AnalysisEventListener excelListener) {
        this.fileHash = fileHash;
        this.sheetNo = sheetNo;
        this.headLineMun = headLineMun;
        this.modelClass = modelClass;
        this.excelListener = excelListener;
    }

    /**
     * 上传的导入文件 hash
     */
    private String fileHash;

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
     * 自定义excel 行数据解析
     */
    private AnalysisEventListener excelListener;
}
