package com.imen.lms.website.controller;

import com.imen.lms.core.service.ILoginInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/8/24 10:24
 * @description
 */
@RestController
public class LoginController {
    @Autowired
    private ILoginInforService loginInforService;

    @GetMapping("/hello")
    public List hello(){
        return loginInforService.selectAll();
    }
}
