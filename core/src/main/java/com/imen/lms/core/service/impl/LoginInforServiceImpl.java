package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.mapper.LoginInforMapper;
import com.imen.lms.core.service.ILoginInforService;
import com.imen.lms.core.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/8/17 16:27
 * @description
 */
@Service
@Transactional
public class LoginInforServiceImpl extends BaseServiceImpl<LoginInfor> implements ILoginInforService {

    @Autowired
    private LoginInforMapper loginInforMapper;

    @Override
    public List<LoginInfor> selectAll() {
        return loginInforMapper.selectAll();
    }

    @Override
    public LoginInfor selectByUsernameAndUserType(String username, int userType) {
        return loginInforMapper.selectByUsernameAndUserType(username, userType);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return loginInforMapper.getPasswordByUsername(username);
    }

    @Override
    public void login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    @Override
    public String getCurrentUsername() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    public void changPWD(String oldPWd, String newPWD) {
        String currentUsername = this.getCurrentUsername();
        String oldPassword = MD5Util.passwordEncrypt(oldPWd, currentUsername);
        LoginInfor loginInfor = this.selectByUsernameAndUserType(currentUsername, LoginInfor.USER_TYPE_ADMIN);
        if (!oldPassword.equals(loginInfor.getPassword())) {
            throw new RuntimeException("密码不正确，不允许修改");
        }
        String newPassword = MD5Util.passwordEncrypt(newPWD, currentUsername);
        loginInfor.setPassword(newPassword);
        loginInforMapper.update(loginInfor);
    }


}
