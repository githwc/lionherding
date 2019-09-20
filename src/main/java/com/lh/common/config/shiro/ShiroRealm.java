package com.lh.common.config.shiro;

import com.lh.system.entity.SysUser;
import com.lh.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 功能描述：自定义Realm
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xie
 * @Datetime: 2019-09-17 16:34
 * @Version: 1.0.0
 */
@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserService sysUserService;

    /**
     * 功能： 执行身份认证
     *      比对 UsernamePasswordToken(前台用户信息)和SimpleAuthenticationInfo(数据库中查询到的信息)
     *      完成身份认证,错误抛出异常
     *
     * @param ——authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        log.debug("————执行身份认证————");
        UsernamePasswordToken token = (UsernamePasswordToken) auth;
        if (token == null) {
            log.debug("————token为空————");
            return null;
        }
        SysUser sysUser = sysUserService.getUserByName(token.getUsername());
        //密码加密
        // token.setPassword(EncoderUtil.encrypt(token.getUsername(),String.valueOf(token.getPassword()),sysUser.getLoginName()).toCharArray());
        //判断用户名
        if (sysUser == null) {
            return null;
        }
        /**
         * principal: 查询到的实体信息
         * credentials: 查询到的密码
         * credentialsSalt: 盐值
         * realmName: 当前realm对象的name
         */
        // ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getLoginName());
        // return new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),credentialsSalt,getName());
        return new SimpleAuthenticationInfo(sysUser,"123456",getName());
    }

    /**
     * 功能： 执行身份授权
     * 获取用户权限信息，包括角色以及权限。
     * 只有当触发检测用户权限时才会调用此方法，例如checkRole,checkPermission
     *
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("————执行权限认证————");
        SysUser sysUser = null;
        if (principals != null) {
            sysUser = (SysUser) principals.getPrimaryPrincipal();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置用户拥有的角色集合 比如 admin,test
        Set<String> roleSet = sysUserService.getUserRoles(sysUser.getLoginName());
        info.setRoles(roleSet);
        //设置用户拥有的权限集合 比如sys:role:add
        Set<String> permissionSet =sysUserService.getUserPermissions(sysUser.getLoginName());
        info.addStringPermissions(permissionSet);
        return info;
    }

}
