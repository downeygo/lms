package com.imen.lms.core.mapper;

import com.imen.lms.core.domain.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 11:17
 * @description
 */
@Mapper
public interface PermissionMapper {
    /**
     * 查询所有权限
     * @return 权限集合
     */
    List<Permission> selectAll();

    /**
     * 通过用户名查询权限表达式
     * @param username 用户名
     * @return 权限表达式集合
     */
    List<String> selectPermissionByUsername(String username);

    /**
     * 添加权限
     * @param p 权限信息
     */
    void insert(Permission p);

    /**
     * 查询所有权限表达式
     * @return 权限表达式集合
     */
    List<String> selectAllString();

    /**
     * 删除权限
     * @param id 权限ID
     */
    void delete(Integer id);

    /**
     * 通过角色ID查询权限
     * @param id 角色ID
     * @return 权限信息
     */
    List<Permission> getPermissionByRole(Integer id);

    /**
     * 根据角色ID删除权限
     * @param id 角色ID
     */
    void deletePermissionByRole(Integer id);
}
