package com.joe.oauth.shiro.realm;

import com.joe.oauth.shiro.pojo.User;
import com.joe.oauth.shiro.services.IUserServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserServices userServices;

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
        //info.addStringPermission("user:add");

        Subject subject = SecurityUtils.getSubject();

        //这块通过subject.getPrincipal()，通过认证传过来的用户账号 SimpleAuthenticationInfo(userName,user.getPassword(),getName());
        String username = (String)subject.getPrincipal();

        List<String> list = userServices.getRolesByUsername(username);
        //添加用户角色授权
        info.addStringPermissions(list);

        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //从主体传过来的认证信息中，获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //模拟数据集用户名和密码
        /*String username = "admin";
        String password = "123456";*/
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userServices.getUserByName(userName);
        if (user==null){
            //用户名不存在 shiro底层会抛出UnknownAccountException
            return null;
        }
        //判断密码
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,user.getPassword(),getName());
        //加密盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("sale011"));
        return authenticationInfo;
    }
}