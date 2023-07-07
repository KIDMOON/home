/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.excel.support;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * easyExcel升级版本后，可使用WriteHandler方式实现国际化，不需要动态生成class
 * easyExcel内部对model使用了缓存,动态生成的class无法使用缓存
 * 在WriteSheet中传入
 * 使用I18nCellWriteHandler，可以不用DefaultLanguage,直接ExcelProperty中使用国际化key作为value
 *
 * @author klaus.jin
 * @date 2020-03-26 11:32
 * @since 1.0
 **/
@Component
public class I18nCellWriteHandler implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead == null || !isHead) {
            return;
        }
        if (cell == null) {
            return;
        }
        String value = cell.getStringCellValue();
        if (StrUtil.isEmpty(value)) {
            return;
        }
        cell.setCellValue(value);
    }
}
