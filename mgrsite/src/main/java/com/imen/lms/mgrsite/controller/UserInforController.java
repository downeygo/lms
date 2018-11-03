package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.domain.Role;
import com.imen.lms.core.domain.UserInfor;
import com.imen.lms.core.page.BaseQuery;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.page.UserInforQuery;
import com.imen.lms.core.service.ILoginInforService;
import com.imen.lms.core.service.IRoleService;
import com.imen.lms.core.service.IUserInforService;
import com.imen.lms.core.util.JSONResult;
import com.imen.lms.core.util.PermissionName;
import com.imen.lms.core.util.StringUtil;
import org.apache.catalina.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/4 14:33
 * @description
 */
@Controller
public class UserInforController {
    @Autowired
    private IUserInforService userInforService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ILoginInforService loginInforService;


    @GetMapping("/user")
    @RequiresPermissions("user:list")
    @PermissionName("用户列表")
    public String list(Model m, UserInforQuery uq) {
        try {
            PageResult page = userInforService.query(uq, uq.getCurrentPage(), uq.getPageSize());
            m.addAttribute("page", page);
            m.addAttribute("user", uq);
            return "user/list";
        } catch (Exception e) {
            return "error/500";
        }
    }

    @PostMapping("/user")
    @RequiresPermissions("user:add")
    @PermissionName("用户添加")
    @ResponseBody
    public JSONResult add(String username, String password, String ids) {
        JSONResult jsonResult = null;
        try {
            Integer[] roleID = StringUtil.stringToIntArr(ids);
            System.out.println(roleID);
            userInforService.add(username, password, roleID);
            jsonResult = new JSONResult(true, "添加成功");

        } catch (RuntimeException e) {
            jsonResult = new JSONResult(e.getMessage());
        } catch (Exception e) {
            jsonResult = new JSONResult("添加失败");
        }
        return jsonResult;
    }

    @PutMapping("/user")
    @RequiresPermissions("user:update")
    @PermissionName("用户更新")
    @ResponseBody
    public JSONResult update(LoginInfor loginInfor, String ids) {
        JSONResult jsonResult = null;
        try {
            Integer[] roleID = StringUtil.stringToIntArr(ids);
            userInforService.update(loginInfor, roleID);
            jsonResult = new JSONResult(true, "修改成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("修改失败");
        }
        return jsonResult;
    }

    @DeleteMapping("/user/{id}")
    @RequiresPermissions("user:delete")
    @PermissionName("用户删除")
    @ResponseBody
    public JSONResult delete(@PathVariable("id") Integer id) {
        JSONResult jsonResult = null;
        try {
            userInforService.delete(id);
            jsonResult = new JSONResult(true, "删除成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("删除失败");
        }
        return jsonResult;
    }

    @GetMapping("/user_getRole")
    @RequiresPermissions("user:getRole")
    @PermissionName("用户获取角色(加载下拉框)")
    @ResponseBody
    public JSONResult getRole() {
        JSONResult<Role> jsonResult = null;
        try {
            List<Role> roles = roleService.selectAll();
            jsonResult = new JSONResult<Role>(true, roles);
        } catch (Exception e) {
            jsonResult = new JSONResult<Role>("加载错误", e.getMessage());
        }
        return jsonResult;
    }

    @GetMapping("/user/{id}")
    @RequiresPermissions("user:get")
    @PermissionName("用户查询")
    @ResponseBody
    public JSONResult get(@PathVariable("id") Integer id) {
        JSONResult<UserInfor> jsonResult = null;
        try {
            UserInfor userInfor = userInforService.getUserRole(id);
            jsonResult = new JSONResult(true, userInfor);
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @GetMapping("/user/personal")
    public String personal(Model m) {
        try {
            String username = loginInforService.getCurrentUsername();
            UserInfor user = userInforService.selectByUsernameAndUserType(username);
            m.addAttribute("user", user);
        } catch (Exception e) {
            return "error/500";
        }
        return "user/personal";
    }

    @PutMapping("/user/personal")
    @ResponseBody
    public JSONResult updatePersonal(UserInfor userInfor) {
        JSONResult jsonResult = null;
        try {
            String username = loginInforService.getCurrentUsername();
            Integer id = userInforService.selectByUsernameAndUserType(username).getId();
            userInfor.setId(id);
            userInforService.updateOne(userInfor);
            jsonResult = new JSONResult(true, "修改成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("修改失败");
        }
        return jsonResult;
    }

    @GetMapping("/user/changePWD")
    public String toChangePWDPage(Model m) {
        try {
            String username = loginInforService.getCurrentUsername();
            m.addAttribute("username", username);
        } catch (Exception e) {
            return "error/500";
        }
        return "user/changePWD";
    }


    @PutMapping("/user/changePWD")
    @ResponseBody
    public JSONResult changePWD(String oldPWD, String newPWD) {
        JSONResult jsonResult = null;
        try {
            loginInforService.changPWD(oldPWD, newPWD);
            jsonResult = new JSONResult(true, "修改成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }
}
