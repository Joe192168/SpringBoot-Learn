package test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    DruidDataSource druidDataSource = new DruidDataSource();
    {
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
    }

    @Test
    public void testAuthentication(){

        JdbcRealm jdbcRealm = new JdbcRealm();
        //添加数据源
        jdbcRealm.setDataSource(druidDataSource);
        //默认为false，不会去查询权限，开启权限
        jdbcRealm.setPermissionsLookupEnabled(true);

        //使用自己创建用户表,查询用户密码
        String sql = "select password from test_user where user_name=?";
        jdbcRealm.setAuthenticationQuery(sql);
        //使用自己创建的用户角色表，查询用户角色
        String roleSql = "select role_name from test_user_role where user_name=?";
        jdbcRealm.setUserRolesQuery(roleSql);
        //使用自己创建的用户权限表，查询用户权限
        String permissSql = "select permission from test_roles_permissions where role_name=?";
        jdbcRealm.setPermissionsQuery(permissSql);

        //1、构建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xiaoming","123456");
        subject.login(token);
        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        subject.checkRoles("user");
        subject.checkPermission("user:select");
    }
}
