package top.catoy.docmanagement.config.shiro;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.catoy.docmanagement.database.UserBean;
import top.catoy.docmanagement.service.UserService;
import top.catoy.docmanagement.domain.User;
import top.catoy.docmanagement.utils.JWTUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 执行授权逻辑
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String username = JWTUtil.getUsername(principals.toString());

        User user = JWTUtil.getUserInfo(principals.toString());
//        UserBean user = userService.getUser(username);
        System.out.println(user+"-------------------------------------------------*************");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());
        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);
        System.out.println("执行授权");
        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User user = userService.getUserByName(username);
//        UserBean userBean = userService.getUserByName(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, user.getUserPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        System.out.println("执行认证");
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
