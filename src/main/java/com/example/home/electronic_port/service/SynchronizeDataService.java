package com.example.home.electronic_port.service;

import com.example.home.electronic_port.dto.ExcelRequest;
import com.example.home.electronic_port.dto.PrintBatchDTO;
import com.example.home.electronic_port.dto.TimeRequest;

import java.io.IOException;
import java.util.List;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/1/9 上午11:20
 * @since 1.0
 **/
public interface SynchronizeDataService {

    void synchronizeData(TimeRequest timeRequest) throws IOException;

    void sendTax(String entryId) throws IOException, InterruptedException;

    List<PrintBatchDTO> printBatch(ExcelRequest excelRequest);
}
