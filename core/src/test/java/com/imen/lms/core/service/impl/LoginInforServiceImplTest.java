package com.imen.lms.core.service.impl;

import com.imen.lms.core.domain.LoginInfor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/8/17 16:34
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginInforServiceImplTest {

    @Autowired
    private LoginInforServiceImpl loginInforService;

    @Test
    public void selectAll() {
        //System.out.println(loginInforService);
        List<LoginInfor> loginInfors = loginInforService.selectAll();
        System.out.println(loginInfors);
    }
}