package com.imen.lms.core.service;

import com.imen.lms.core.domain.Role;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 14:47
 * @description
 */
public interface IRoleService extends IBaseService<Role>{
    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<Role> selectAll();

    /**
     * 添加角色
     * @param role 角色信息
     * @param ids 权限ID集合
     */
    void add(Role role, Integer[] ids);

    /**
     * 获取角色和对应的权限
     * @param id 角色ID
     * @return 角色信息
     */
    Role getRolePermission(Integer id);

    /**
     * 更新角色
     * @param role 角色信息
     * @param permissionID 权限ID集合
     */
    void update(Role role, Integer[] permissionID);

    /**
     * 删除角色
     * @param id 角色ID
     */
    void delete(Integer id);
}
