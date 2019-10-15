package test;

import com.shiro.realm.TestCustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomRealmTest {

    @Test
    public void testAuthentication(){

        TestCustomRealm customRealm = new TestCustomRealm();

        //1、构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //hash散列加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");//加密算法的名称
        matcher.setHashIterations(1);//密码的次数

        customRealm.setCredentialsMatcher(matcher);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");
        subject.login(token);

        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        //校验用户是否有该角色
        subject.checkRole("admin");
        //校验用户是否有该权限
        subject.checkPermissions("user:update","user:add");
    }

}