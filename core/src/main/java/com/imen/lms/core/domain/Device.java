package com.imen.lms.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @autor LIGANG
 * @data 2018/10/28 10:58
 * @description 设备
 */
@Getter
@Setter
@ToString
public class Device extends BaseDomain{
    private String name;//名称
    private String type;//类型
    private String brand;//品牌
    private Double price;//价格
}
