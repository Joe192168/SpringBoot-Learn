package test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Authentication认证
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //添加一个用户
        simpleAccountRealm.addAccount("test","123456","admin","user");
    }

    @Test
    public void testAuthentication(){

        //1、构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("test","123456");
        subject.login(token);
        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        /*subject.logout();
        System.out.println("isAuthenticated:"+subject.isAuthenticated());*/
        subject.checkRoles("admin","user");
    }
}
