package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.domain.UserInfor;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.page.UserInforQuery;
import com.imen.lms.core.service.IUserInforService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/8/17 16:34
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan
@MapperScan
public class LoginInforServiceImplTest {

    @Autowired
    private LoginInforServiceImpl loginInforService;
    @Autowired
    private IUserInforService userInforService;
    @Autowired
    private UserInforQuery userInforQuery;

    @Test
    public void selectAll() {
        /*userInforQuery.setUsername("root");
        PageResult<UserInfor> query = userInforService.query(userInforQuery);
        System.out.println(query.getListResult().toString());*/

    }
}