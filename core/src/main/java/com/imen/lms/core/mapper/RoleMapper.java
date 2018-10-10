package com.imen.lms.core.mapper;

import com.imen.lms.core.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 11:18
 * @description
 */
@Mapper
public interface RoleMapper {
    /**
     * 查询角色信息
     * @return 角色信息列表
     */
    List<Role> selectAll();

    /**
     * 添加用户信息
     * @param role 用户信息
     */
    void insert(Role role);

    /**
     * 插入role_permission中间表
     * @param roleID 角色ID
     * @param permissionID 权限ID
     */
    void insertRolePermission(@Param("r_id") Integer roleID, @Param("p_id") Integer permissionID);

    /**
     * 根据角色名查询角色
     * @param RoleName 角色名
     * @return
     */
    Role getByName(String RoleName);

    /**
     * 根据角色ID查询角色
     * @param id 角色ID
     * @return
     */
    Role get(Integer id);

    /**
     * 更新角色
     * @param role 角色信息
     */
    void update(Role role);

    /**
     * 删除角色
     * @param id 角色ID
     */
    void delete(Integer id);

    /**
     * 插入loginInfor_role中间表
     * @param userID 用户ID
     * @param roleID 角色ID
     */
    void insertUserRole(@Param("l_id") Integer userID, @Param("r_id") Integer roleID);

    /**
     * 根据用户ID查询角色
     * @param id 用户ID
     * @return 角色列表
     */
    List<Role> getRoleByUser(Integer id);

    /**
     * 通过用户ID删除角色
     * @param id  用户ID
     */
    void deleteRoleByUser(Integer id);
}
