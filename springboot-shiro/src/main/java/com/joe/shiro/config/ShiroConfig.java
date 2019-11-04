package com.joe.shiro.config;

import com.joe.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用过滤器：
         * anon：无需认证（登陆）访问
         * authc：必须认证才可以访问
         * user：如果使用rememberMe的功能可以直接访问
         * perms：该资源必须得到资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/

        filterMap.put("/index","anon");
        //放行login登录
        filterMap.put("/login","anon");

        //授权过滤器
        //注意：当授权拦截后，shiro会自动跳转到未授权页面
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");

        filterMap.put("/*","authc");

        //修改跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建加密设置
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用md5 算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数： 意为加密几次
        hashedCredentialsMatcher.setHashIterations(1);

        return hashedCredentialsMatcher;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }


}