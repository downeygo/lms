package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.service.ILoginInforService;
import com.imen.lms.core.util.JSONResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @autor LIGANG
 * @data 2018/8/23 17:31
 * @description
 */
@Controller
public class LoginController {
    @Autowired
    private ILoginInforService loginInforService;

    @GetMapping(value = {"/login", "/"})
    public String login() {
        return "login";
    }

    @PostMapping("/loginTo")
    @ResponseBody
    public JSONResult loginTo(String username, String password, Model m) {
        JSONResult jsonResult = null;
        try {
            loginInforService.login(username, password);
            jsonResult = new JSONResult(true, "登录成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("用户名或密码错误");
        }
        return jsonResult;
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
