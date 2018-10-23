package com.imen.lms.core.page;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @autor LIGANG
 * @data 2018/10/13 14:09
 * @description
 */
@Getter
@Setter
@Component
public class UserInforQuery extends BaseQuery {
    private String username;//用户名
    private String realName;//真实姓名
    private Integer gender;//0:男 1：女
    private String phoneNumber;//电话号码
    private String email;//电子邮件
    private Date birth;//出生年月
    private Integer userType;//用户类型
}
