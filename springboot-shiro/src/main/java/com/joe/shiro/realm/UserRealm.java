package com.joe.shiro.realm;

import com.joe.shiro.pojo.User;
import com.joe.shiro.services.IUserServices;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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
        return null;
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