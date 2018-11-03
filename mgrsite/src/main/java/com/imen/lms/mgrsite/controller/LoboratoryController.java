package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.ILaboratoryService;
import com.imen.lms.core.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
