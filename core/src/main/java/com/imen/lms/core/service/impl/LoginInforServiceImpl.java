package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.mapper.LoginInforMapper;
import com.imen.lms.core.service.ILoginInforService;
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
public class LoginInforServiceImpl implements ILoginInforService {

    @Autowired
    private LoginInforMapper loginInforMapper;

    @Override
    public List<LoginInfor> selectAll() {
        return loginInforMapper.selectAll();
    }

    @Override
    public LoginInfor selectByUsernameAndUserType(String username, int userType) {
        return loginInforMapper.selectByUsernameAndUserType(username,userType);
    }

    @Override
    public String getPasswordByUsername(String username) {
        return loginInforMapper.getPasswordByUsername(username);
    }

    @Override
    public void login(String username, String password){
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

}
