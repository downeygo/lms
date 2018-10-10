package com.imen.lms.core.mapper;

import com.imen.lms.core.domain.LoginInfor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @autor LIGANG
 * @data 2018/8/17 16:18
 * @description 登录信息mapper类
 */
@Mapper
public interface LoginInforMapper {
    /**
     * 查询所有登录信息
     * @return  登录信息集合
     */
    List<LoginInfor> selectAll();

    /**
     * 通过用户名和用户类型查询用户
     * @param username 用户名
     * @param userType 用户类型
     * @return 用户信息
     */
    LoginInfor selectByUsernameAndUserType(@Param("username") String username,@Param("userType") int userType);

    /**
     * 通过用户名查询密码
     * @param username 用户名
     * @return 密码
     */
    String getPasswordByUsername(String username);

    /**
     * 添加登录信息
     * @param loginInfor 登录信息
     */
    void add(LoginInfor loginInfor);

    /**
     * 更新登录信息
     * @param loginInfor 登录信息
     */
    void update(LoginInfor loginInfor);

    /**
     * 删除登录信息
     * @param id 登录信息
     */
    void delete(Integer id);

}