/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.request;

import com.alibaba.fastjson.JSON;
import com.example.home.electronic_port.entity.CustomsDeclarationDO;
import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/12/13 下午4:23
 * @since 1.0
 * <p>
 * json.put("curPage", 1);
 * json.put("decType", 1);
 * json.put("endDate", "20201212");
 * json.put("entryId", "");
 * json.put("pageSize", 10);
 * json.put("startDate", "20201112")
 **/
@Data
public class QueryBillRequest {

    private Integer curPage;
    private String decType;
    private String endDate;
    private String entryId = "";
    private Integer pageSize;
    private String startDate;

    public QueryBillRequest(Integer curPage, String decType, Integer pageSize, String startDate, String endDate) {
        this.curPage = curPage;
        this.decType = decType;
        this.endDate = endDate;
        this.pageSize = pageSize;
        this.startDate = startDate;
    }

    public static void main(String[] args) {
//        QueryBillRequest queryBillRequest = new QueryBillRequest(1, "1", 10, "20201112", "20201212");
//        System.out.println(JSON.toJSONString(queryBillRequest));

        CustomsDeclarationDO customsDeclarationDO=new CustomsDeclarationDO();
        customsDeclarationDO.setIEDate("dsdsad");

        System.out.println(JSON.toJSONString(customsDeclarationDO));

    }

}
