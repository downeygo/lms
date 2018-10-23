package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.domain.Role;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.IPermissionService;
import com.imen.lms.core.service.IRoleService;
import com.imen.lms.core.util.JSONResult;
import com.imen.lms.core.util.PermissionName;
import com.imen.lms.core.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/6 14:45
 * @description
 */
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/role")
    @RequiresPermissions("role:list")
    @PermissionName("角色列表")
    public String list(Model m, Role r) {
        try {
            PageResult page = roleService.query(r, r.getCurrentPage(), r.getPageSize());
            m.addAttribute("page", page);
            m.addAttribute("r", r);
        } catch (Exception e) {
            return "error/500";
        }
        return "role/list";
    }


    @GetMapping("/role_getPermission")
    @RequiresPermissions("role:getPermission")
    @PermissionName("角色获取权限(加载下拉框)")
    @ResponseBody
    public JSONResult getPermission() {
        JSONResult<Permission> jsonResult = null;
        try {
            List<Permission> permissions = permissionService.selectAll();
            jsonResult = new JSONResult<Permission>(true, permissions);
        } catch (Exception e) {
            jsonResult = new JSONResult<Permission>("加载错误", e.getMessage());
        }
        return jsonResult;
    }

    @PostMapping("/role")
    @RequiresPermissions("role:add")
    @PermissionName("角色添加")
    @ResponseBody
    public JSONResult add(Role role, String ids) {
        JSONResult<Permission> jsonResult = null;
        try {
            Integer[] permissionID = StringUtil.stringToIntArr(ids);
            roleService.add(role, permissionID);
            jsonResult = new JSONResult(true, "添加成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @GetMapping("/role/{id}")
    @RequiresPermissions("role:get")
    @PermissionName("角色查询")
    @ResponseBody
    public JSONResult get(@PathVariable("id") Integer id) {
        JSONResult<Role> jsonResult = null;
        try {
            Role role = roleService.getRolePermission(id);
            jsonResult = new JSONResult(true, role);
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @PutMapping("/role")
    @RequiresPermissions("role:update")
    @PermissionName("角色更新")
    @ResponseBody
    public JSONResult update(Role role, String ids) {
        JSONResult<Role> jsonResult = null;
        try {
            Integer[] permissionID = StringUtil.stringToIntArr(ids);
            roleService.update(role, permissionID);
            jsonResult = new JSONResult(true, "更新成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @DeleteMapping("/role/{id}")
    @RequiresPermissions("role:delete")
    @PermissionName("角色删除")
    @ResponseBody
    public JSONResult delete(@PathVariable("id") Integer id) {
        JSONResult<Role> jsonResult = null;
        try {
            roleService.delete(id);
            jsonResult = new JSONResult(true, "删除成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("删除失败");
        }
        return jsonResult;
    }
}
