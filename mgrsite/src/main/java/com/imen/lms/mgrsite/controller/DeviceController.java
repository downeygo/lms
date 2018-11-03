package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.Device;
import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.domain.Role;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.IDeviceService;
import com.imen.lms.core.util.JSONResult;
import com.imen.lms.core.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @autor LIGANG
 * @data 2018/10/28 11:30
 * @description
 */
@Controller
public class DeviceController {
    @Autowired
    private IDeviceService deviceService;

    @GetMapping("/device")
    @RequiresPermissions("device:list")
    @PermissionName("设备列表")
    public String list(Model m, Device d) {
        try {
            PageResult page = deviceService.query(d, d.getCurrentPage(), d.getPageSize());
            m.addAttribute("page", page);
            m.addAttribute("d", d);
        } catch (Exception e) {
            return "error/500";
        }
        return "device/list";
    }

    @GetMapping("/device/{id}")
    @RequiresPermissions("device:get")
    @PermissionName("设备查询")
    @ResponseBody
    public JSONResult get(@PathVariable("id") Integer id) {
        JSONResult<Role> jsonResult = null;
        try {
            Device device = deviceService.getByID(id);
            jsonResult = new JSONResult(true, device);
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @PostMapping("/device")
    @RequiresPermissions("device:add")
    @PermissionName("设备添加")
    @ResponseBody
    public JSONResult add(Device d) {
        JSONResult<Permission> jsonResult = null;
        try {
            deviceService.insertOne(d);
            jsonResult = new JSONResult(true, "添加成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @PutMapping("/device")
    @RequiresPermissions("device:update")
    @PermissionName("角色更新")
    @ResponseBody
    public JSONResult update(Device d) {
        JSONResult<Role> jsonResult = null;
        try {
            deviceService.updateOne(d);
            jsonResult = new JSONResult(true, "更新成功");
        } catch (Exception e) {
            jsonResult = new JSONResult(e.getMessage());
        }
        return jsonResult;
    }

    @DeleteMapping("/device/{id}")
    @RequiresPermissions("device:delete")
    @PermissionName("设备删除")
    @ResponseBody
    public JSONResult delete(@PathVariable("id") Integer id) {
        JSONResult<Role> jsonResult = null;
        try {
            deviceService.deleteOne(id);
            jsonResult = new JSONResult(true, "删除成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("删除失败");
        }
        return jsonResult;
    }

}
