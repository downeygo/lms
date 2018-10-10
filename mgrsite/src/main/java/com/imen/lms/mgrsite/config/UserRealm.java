package com.imen.lms.mgrsite.config;

import com.imen.lms.core.domain.LoginInfor;
import com.imen.lms.core.domain.Permission;
import com.imen.lms.core.service.ILoginInforService;
import com.imen.lms.core.service.IPermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/8/24 14:04
 * @description UserRealm类
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private ILoginInforService loginInforService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public String getName() {
        return "UserRealm";
    }

    //授权（检查是否有权限）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        LoginInfor loginInfor = loginInforService.selectByUsernameAndUserType(username, LoginInfor.USER_TYPE_SUPER_ADMIN);
        List<String> permissions = null;
        //判断是否是超级管理员
        if (loginInfor != null) {
            permissions = permissionService.selectAllString();
        } else {
            permissions = permissionService.selectPermissionByUsername(username);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

    //认证（验证是否登录）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if (username == null) {
            return null;
        }
        //判断是否是超级管理员
        LoginInfor loginInfor = loginInforService.selectByUsernameAndUserType(username, LoginInfor.USER_TYPE_SUPER_ADMIN);
        if (loginInfor == null) {
            loginInfor = loginInforService.selectByUsernameAndUserType(username, LoginInfor.USER_TYPE_ADMIN);
            if (loginInfor == null) {
                return null;
            }
        }
        String password = loginInfor.getPassword();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, ByteSource.Util.bytes(username), getName());
        return info;
    }
}
