package com.imen.lms.core.service;

import com.imen.lms.core.domain.LoginInfor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/8/17 16:26
 * @description
 */
public interface ILoginInforService {
    /**
     * 获取登录信息
     * @return 登录信息集合
     */
    List<LoginInfor> selectAll();

    /**
     * 根据用户名和用户类型查询用户
     * @param username 用户名
     * @param userType 用户类型
     * @return 用户
     */
    LoginInfor selectByUsernameAndUserType(String username, int userType);

    /**
     * 根据用户名查询密码
     * @param username 用户名
     * @return 密码
     */
    String getPasswordByUsername(String username);

    /**
     * 登录操作
     * @param username 用户名
     * @param password 密码
     */
    void login(String username,String password);

}
