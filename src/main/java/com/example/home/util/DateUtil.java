/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description goes here.
 *
 * @author klaus.jin
 * @date 2021/3/24 3:17 下午
 * @since 1.0
 **/
public class DateUtil {

    /**
     * 指定年份的第一天
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getFirstDay(String date) throws ParseException {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date nowDay = format.parse(date);
        String time = format.format(nowDay);
        String firstDay = time + "-01-01";
        return firstDay;
    }

    /**
     * @param str 传递的日期字符串
     * @Description:日期转换，将接口返回的20180524转为2018-05-24
     */
    public static String dateConvertion(String str) {
        Date parse = null;
        String dateString = "";
        try {
            parse = new SimpleDateFormat("yyyyMMdd").parse(str);
            dateString = new SimpleDateFormat("yyyy-MM-dd").format(parse);
        } catch (ParseException e) {
            dateString = null;
        }
        return dateString;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
}
