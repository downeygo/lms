package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.domain.Role;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.ILaboratoryService;
import com.imen.lms.core.util.JSONResult;
import com.imen.lms.core.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:13
 * @description
 */
@Controller
public class LoboratoryController {

    @Autowired
    private ILaboratoryService laboratoryService;

    @GetMapping("/laboratory")
    @RequiresPermissions("laboratory:list")
    @PermissionName("实验室列表")
    public String list(Model m, Laboratory lab) {
        PageResult page = laboratoryService.query(lab, lab.getCurrentPage(), lab.getPageSize());
        m.addAttribute("page", page);
        m.addAttribute("lab", lab);
        return "labaratory/list";
    }

    @PostMapping("/laboratory")
    @RequiresPermissions("laboratory:add")
    @PermissionName("实验室添加")
    @ResponseBody
    public JSONResult add(Laboratory l) {
        JSONResult<Permission> jsonResult = null;
        try {
            laboratoryService.insertOneCus(l);
            jsonResult = new JSONResult(true, "添加成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @DeleteMapping("/laboratory/{id}")
    @RequiresPermissions("laboratory:closeOrOpen")
    @PermissionName("实验室关闭和启用")
    @ResponseBody
    public JSONResult closeOrOpen(@PathVariable("id") Integer id) {
        JSONResult<Role> jsonResult = null;
        try {
            laboratoryService.closeOrOpen(id);
            jsonResult = new JSONResult(true, "操作成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }
}
