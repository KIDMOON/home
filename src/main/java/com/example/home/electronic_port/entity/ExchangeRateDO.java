/*
 * Copyright 2006-2021 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.electronic_port.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.home.entity.BasicEntity;
import lombok.Data;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2021/3/23 上午11:35
 * @since 1.0
 **/
@Data
@TableName("exchange_rate_do")
public class ExchangeRateDO extends BasicEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "money_id")
    private String moneyId;

    @TableField(value = "year")
    private Integer year;

    @TableField(value = "month")
    private Integer month;

    @TableField(value = "money_rate")
    private Double moneyRate;

    @TableField(value = "day01")
    private Double day01;

    @TableField(value = "day02")
    private Double day02;

    @TableField(value = "day03")
    private Double day03;

    @TableField(value = "day04")
    private Double day04;

    @TableField(value = "day05")
    private Double day05;

    @TableField(value = "day06")
    private Double day06;

    @TableField(value = "day07")
    private Double day07;

    @TableField(value = "day08")
    private Double day08;

    @TableField(value = "day09")
    private Double day09;

    @TableField(value = "day10")
    private Double day10;

    @TableField(value = "day11")
    private Double day11;

    @TableField(value = "day12")
    private Double day12;

    @TableField(value = "day13")
    private Double day13;

    @TableField(value = "day14")
    private Double day14;

    @TableField(value = "day15")
    private Double day15;

    @TableField(value = "day16")
    private Double day16;

    @TableField(value = "day17")
    private Double day17;

    @TableField(value = "day18")
    private Double day18;

    @TableField(value = "day19")
    private Double day19;

    @TableField(value = "day20")
    private Double day20;

    @TableField(value = "day21")
    private Double day21;

    @TableField(value = "day22")
    private Double day22;

    @TableField(value = "day23")
    private Double day23;

    @TableField(value = "day24")
    private Double day24;

    @TableField(value = "day25")
    private Double day25;

    @TableField(value = "day26")
    private Double day26;

    @TableField(value = "day27")
    private Double day27;

    @TableField(value = "day28")
    private Double day28;

    @TableField(value = "day29")
    private Double day29;

    @TableField(value = "day30")
    private Double day30;

    @TableField(value = "day31")
    private Double day31;
}

