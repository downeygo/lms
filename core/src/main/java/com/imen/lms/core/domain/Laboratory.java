package com.imen.lms.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:13
 * @description
 */
@Getter
@Setter
@ToString
public class Laboratory extends BaseDomain{
    private String name;//名称
    private String code;//编码
    private Integer type;//类型
    private String location;//位置
    private UserInfor establisher;//创办人
    private Integer size;//可容纳人数
    private String openTime;//开放时间
    private String closeTime;//结束时间
    private Date establishTime;//成立时间
}
