package com.shiro.realm;

import com.shiro.dao.UserDao;
import com.shiro.vo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义Realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /*Map<String, String> userMap = new HashMap<>();

    {
        userMap.put("admin","2b90d78826aa7171dfe16fe4e59ffc77");
        //设置Realm的名称，自定义
        super.setName("customRealm");
    }*/

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //从数据库中或缓存中获取角色数据
        Set<String> roles = getRolesByUserName(userName);

        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    //模拟数据查询用户授权
    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:update");
        sets.add("user:add");
        return sets;
    }
    //模拟数据查询用户角色
    private Set<String> getRolesByUserName(String userName) {
        /*Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");*/
        System.out.println("从数据库中获取角色信息");
        List<String> list = userDao.getRolesByUserName(userName);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        Set<String> sets = new HashSet<String>(list);
        return sets;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从主体传过来的认证信息中，获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        //通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,"customRealm");
        //加密盐
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("sale011"));
        return authenticationInfo;
    }

    //模拟数据库查询用户凭证
    private String getPasswordByUserName(String userName) {
        User user = userDao.getUserByUserName(userName);
        if (user!=null){
            return user.getPassword();
        }
        return null;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","sale011");
        System.out.println(md5Hash.toString());
    }
}