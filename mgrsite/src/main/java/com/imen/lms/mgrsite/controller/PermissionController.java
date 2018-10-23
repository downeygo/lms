package com.imen.lms.mgrsite.controller;

import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.page.PageResult;
import com.imen.lms.core.service.IPermissionService;
import com.imen.lms.core.util.JSONResult;
import com.imen.lms.core.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @autor LIGANG
 * @data 2018/10/6 12:04
 * @description
 */
@Controller
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("/permission")
    @RequiresPermissions("permission:list")
    @PermissionName("权限列表")
    public String list(Model m, Permission p) {
        try {
            PageResult page = permissionService.query(p, p.getCurrentPage(), p.getPageSize());
            m.addAttribute("page", page);
            m.addAttribute("p", p);
        } catch (Exception e) {
            return "error/500";
        }
        return "permission/list";
    }

    @DeleteMapping("/permission/{id}")
    @RequiresPermissions("permission:delete")
    @PermissionName("权限删除")
    @ResponseBody
    public JSONResult delete(@PathVariable("id") Integer id) {
        JSONResult jsonResult = null;
        try {
            permissionService.delete(id);
            jsonResult = new JSONResult(true, "删除成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("删除失败", e.getMessage());
        }
        return jsonResult;
    }


    @GetMapping("/reload")
    @RequiresPermissions("permission:reload")
    @PermissionName("权限加载")
    @ResponseBody
    public JSONResult reload() {
        JSONResult jsonResult = null;
        try {
            List<String> permissionList = permissionService.selectAllString();
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            Collection<HandlerMethod> methods = handlerMethods.values();
            for (HandlerMethod method : methods) {
                RequiresPermissions anno = method.getMethodAnnotation(RequiresPermissions.class);
                if (anno != null) {
                    String resource = anno.value()[0];
                    //去除重复
                    if (permissionList.contains(resource)) {
                        continue;
                    }
                    String name = method.getMethodAnnotation(PermissionName.class).value();
                    Permission p = new Permission();
                    p.setName(name);
                    p.setResource(resource);
                    permissionService.insert(p);
                }
            }
            jsonResult = new JSONResult(true, "加载成功");
        } catch (Exception e) {
            jsonResult = new JSONResult("加载失败", e.getMessage());
        }
        return jsonResult;
    }
}
