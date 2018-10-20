package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.domain.Role;
import com.imen.lms.core.mapper.PermissionMapper;
import com.imen.lms.core.mapper.RoleMapper;
import com.imen.lms.core.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 14:49
 * @description
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public void add(Role role, Integer[] ids) {
        //查看角色是否存在
        Role r = roleMapper.getByName(role.getName());
        //不存在
        if (r == null) {
            //插入角色
            roleMapper.insert(role);
            //获取刚才插入角色的ID
            role = roleMapper.getByName(role.getName());
            for (Integer id : ids) {
                //为角色添加权限
                roleMapper.insertRolePermission(role.getId(), id);
            }
        } else {
            throw new RuntimeException("角色已经存在");
        }
    }


    @Override
    public Role getRolePermission(Integer id) {
        Role role = roleMapper.get(id);
        List<Permission> permission = permissionMapper.getPermissionByRole(id);
        role.setPermissions(permission);
        return role;
    }

    @Override
    public void update(Role role, Integer[] permissionID) {
        boolean flag = false;
        //查看角色名是否存在
        Role r = roleMapper.getByName(role.getName());
        //不存在则更新
        if (r == null) {
            flag = true;
            //存在:如果是同一个角色，也可以更新
        } else if (r.getId().equals(role.getId())) {
            flag = true;
        } else {
            throw new RuntimeException("角色已经存在");
        }
        if (flag) {
            //更新角色
            roleMapper.update(role);
            //删除角色权限中间表
            permissionMapper.deletePermissionByRole(role.getId());
            //重新添加新的权限
            for (Integer integer : permissionID) {
                roleMapper.insertRolePermission(role.getId(), integer);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        //删除角色权限
        permissionMapper.deletePermissionByRole(id);
        //删除角色
        roleMapper.delete(id);
    }
}
