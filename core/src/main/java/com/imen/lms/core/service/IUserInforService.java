package com.imen.lms.core.service;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.domain.UserInfor;
import com.imen.lms.core.page.UserInforQuery;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 14:15
 * @description
 */
public interface IUserInforService extends IBaseService<UserInfor,UserInforQuery>{
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<UserInfor> selectAll();

    /**
     * 添加用户
     * @param username 用户名
     * @param password 密码
     * @param roleID 角色ID
     */
    void add(String username, String password, Integer[] roleID);

    /**
     * 更新用户
     * @param loginInfor 用户信息
     * @param roleID 角色ID
     */
    void update(LoginInfor loginInfor, Integer[] roleID);

    /**
     * 删除用户
     * @param id 用户ID
     */
    void delete(Integer id);

    /**
     * 获取用户和对应的角色
     * @param id
     * @return
     */
    UserInfor getUserRole(Integer id);
}
