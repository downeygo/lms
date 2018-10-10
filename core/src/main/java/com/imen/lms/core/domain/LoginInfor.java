package com.imen.lms.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @autor LIGANG
 * @data 2018/8/17 16:18
 * @description 登录信息实体类
 */
@Getter
@Setter
@ToString
public class LoginInfor extends BaseDomain {
    public static int LOGIN_STATE_NORMAL = 0;//用户状态正常：0
    public static int LOGIN_STATE_UNNORMAL = 1;//用户状态非正常：1
    public static int LOGIN_STATE_DELETE = 2;//用户删除状态：2
    public static int LOGIN_STATE_INITIALIZE = 9;//用户初始化状态：9
    public static int USER_TYPE_CUSTOM = 0;//普通用户
    public static int USER_TYPE_ADMIN = 1;//管理员
    public static int USER_TYPE_SUPER_ADMIN = 9;//超级管理员

    private String username;//用户名
    private String password;//密码
    private Integer state;//状态
    private Integer userType;//类型
}
