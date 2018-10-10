package com.imen.lms.core.service;

import com.imen.lms.core.domain.Permission;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 11:33
 * @description
 */
public interface IPermissionService {
    /**
     * 查询用户对应的权限表达式
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
     * 查询所有权限信息
     * @return 权限集合
     */
    List<Permission> selectAll();

    /**
     * 删除权限
     * @param id 权限ID
     */
    void delete(Integer id);
}
