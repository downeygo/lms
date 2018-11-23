package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.Laboratory;
import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.domain.Role;
import com.imen.lms.core.domain.UserInfor;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.page.UserInforQuery;
import com.imen.lms.core.service.ILaboratoryService;
import com.imen.lms.core.service.IUserInforService;
import com.imen.lms.core.service.impl.CodelkupServiceImpl;
import com.imen.lms.core.util.JSONResult;
import com.imen.lms.core.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @autor LIGANG
 * @data 2018/10/4 19:13
 * @description
 */
@Controller
public class LoboratoryController {

    @Autowired
    private ILaboratoryService laboratoryService;
    @Autowired
    private IUserInforService userInforService;
    @Autowired
    private CodelkupServiceImpl codelkupService;

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

    @GetMapping("/laboratory/toDetailPage")
    @RequiresPermissions("laboratory:detail")
    @PermissionName("实验室详情")
    public String toDetailPage(Model m, Integer id) {
        Laboratory lab = laboratoryService.getByID(id);
        List<UserInfor> userInforList = userInforService.selectAll();
        List<Map<String, String>> member = laboratoryService.getMember(id);
        String memberString = laboratoryService.getMemberString(member);
        m.addAttribute("lab", lab);
        m.addAttribute("users", userInforList);
        m.addAttribute("member", member);
        m.addAttribute("memberStr", memberString);
        return "labaratory/labDetail";
    }

    @PutMapping("/laboratory/toDetailPage")
    @ResponseBody
    @RequiresPermissions("laboratory:detail")
    @PermissionName("实验室详情")
    public JSONResult save(Laboratory lab) {
        JSONResult jsonResult = null;
        try {
            UserInfor user = userInforService.get(lab.getEstablisherID());
            lab.setEstablisher(user.getRealName());
            laboratoryService.updateOne(lab);
            jsonResult = new JSONResult(true, "修改成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("修改失败");
        }
        return jsonResult;
    }

    @GetMapping("/laboratory/addMember")
    @ResponseBody
    @RequiresPermissions("laboratory:addMember")
    @PermissionName("实验室添加成员")
    public JSONResult queryMember() {
        JSONResult jsonResult = null;
        try {
            List<UserInfor> userInforList = userInforService.selectAll();
            List<Map<String, String>> type = codelkupService.codelkup("LABUSERTYPE");
            jsonResult = new JSONResult(true, type, userInforList);
        } catch (Exception e) {
            jsonResult = new JSONResult(false, "系统异常,请联系管理员");
        }
        return jsonResult;
    }

    @PostMapping("/laboratory/addMember")
    @ResponseBody
    @RequiresPermissions("laboratory:addMember")
    @PermissionName("实验室添加成员")
    public JSONResult addMember(Integer labID, Integer userID, Integer typeID) {
        JSONResult jsonResult = null;
        try {
            laboratoryService.addMember(labID, userID, typeID);
            jsonResult = new JSONResult(true, "添加成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(false, "系统异常,请联系管理员");
        }
        return jsonResult;
    }
}
