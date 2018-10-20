package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.mapper.PermissionMapper;
import com.imen.lms.core.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 11:33
 * @description
 */
@Service
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> selectPermissionByUsername(String username) {
        return permissionMapper.selectPermissionByUsername(username);
    }


    @Override
    public void insert(Permission p) {
        permissionMapper.insert(p);
    }

    @Override
    public List<String> selectAllString() {
        return permissionMapper.selectAllString();
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public void delete(Integer id) {
        permissionMapper.delete(id);
    }
}
