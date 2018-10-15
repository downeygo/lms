package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.domain.Role;
import com.imen.lms.core.domain.UserInfor;
import com.imen.lms.core.mapper.LoginInforMapper;
import com.imen.lms.core.mapper.RoleMapper;
import com.imen.lms.core.mapper.UserInforMapper;
import com.imen.lms.core.page.UserInforQuery;
import com.imen.lms.core.service.IUserInforService;
import com.imen.lms.core.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 14:16
 * @description
 */
@Service
@Transactional
public class UserInforServiceImpl extends BaseServiceImpl<UserInfor,UserInforQuery> implements IUserInforService {

    @Autowired
    private UserInforMapper userInforMapper;
    @Autowired
    private LoginInforMapper loginInforMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<UserInfor> selectAll() {
        return userInforMapper.selectAll();
    }

    @Override
    public void add(String username, String password, Integer[] roleID) {
        //判断用户是否存在
        LoginInfor loginInfor = loginInforMapper.selectByUsernameAndUserType(username, LoginInfor.USER_TYPE_ADMIN);
        if (loginInfor == null) {
            //添加登录信息
            loginInfor = new LoginInfor();
            loginInfor.setUsername(username);
            loginInfor.setPassword(MD5Util.passwordEncrypt(password,username));
            loginInfor.setState(LoginInfor.LOGIN_STATE_INITIALIZE);
            loginInfor.setUserType(LoginInfor.USER_TYPE_ADMIN);
            loginInforMapper.add(loginInfor);
            //添加用户基本信息
            loginInfor = loginInforMapper.selectByUsernameAndUserType(username, LoginInfor.USER_TYPE_ADMIN);
            UserInfor userInfor = new UserInfor();
            userInfor.setId(loginInfor.getId());
            userInforMapper.add(userInfor);
            //添加用户角色
            for (Integer id : roleID) {
                roleMapper.insertUserRole(loginInfor.getId(), id);
            }
        } else {
            throw new RuntimeException("用户名存在");
        }
    }

    @Override
    public void update(LoginInfor loginInfor, Integer[] roleID) {
        //更新用户信息
        loginInfor.setPassword(MD5Util.passwordEncrypt(loginInfor.getPassword(),loginInfor.getUsername()));
        loginInforMapper.update(loginInfor);
        //删除用户角色中间表
        roleMapper.deleteRoleByUser(loginInfor.getId());
        //重新添加角色
        for (Integer id : roleID) {
            roleMapper.insertUserRole(loginInfor.getId(), id);
        }
    }

    @Override
    public void delete(Integer id) {
        userInforMapper.delete(id);
        loginInforMapper.delete(id);
    }

    @Override
    public UserInfor getUserRole(Integer id) {
        UserInfor userInfor = userInforMapper.get(id);
        List<Role> roles = roleMapper.getRoleByUser(id);
        userInfor.setRoles(roles);
        return userInfor;
    }
}
