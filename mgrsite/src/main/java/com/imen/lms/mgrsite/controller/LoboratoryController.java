package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.service.ILaboratoryService;
import com.imen.lms.core.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String list(Model m){
        m.addAttribute("lab",laboratoryService.selectAll());
        return "labaratory/list";
    }
}
