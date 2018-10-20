package com.imen.lms.core.mapper;

import com.imen.lms.core.domain.UserInfor;
import com.imen.lms.core.page.UserInforQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 14:14
 * @description 用户信息mapper类
 */
@Mapper
public interface UserInforMapper extends BaseMapper<UserInforQuery>{
    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<UserInfor> selectAll();

    /**
     * 添加用户
     * @param userInfor 用户信息
     */
    void add(UserInfor userInfor);

    /**
     * 删除用户
     * @param id 用户ID
     */
    void delete(Integer id);

    /**
     * 根据用户ID获取用户信息
     * @param id 用户ID
     * @return
     */
    UserInfor get(Integer id);
}
