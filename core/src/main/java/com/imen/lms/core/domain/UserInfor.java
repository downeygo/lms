package com.imen.lms.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 13:54
 * @description 用户信息实体类
 */
@Getter
@Setter
@ToString
public class UserInfor extends BaseDomain {
    private String realName;//真实姓名
    private Integer gender;//0:男 1：女
    private String phoneNumber;//电话号码
    private String email;//电子邮件
    private Date birth;//出生年月
    private LoginInfor loginInfor;//登录信息
    private List<Role> roles;//用户拥有的角色
}
