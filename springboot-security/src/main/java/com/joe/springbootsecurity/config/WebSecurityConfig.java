package com.joe.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @program: springboot-learn
 * @description: security配置器
 * @author: xiaoqiaohui
 * @create: 2019-12-27 12:59
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 定义用户信息服务（查询用户信息）
     * @return
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        //先缓存中查询
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("test").password("123").authorities("p2").build());
        return manager;
    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    protected PasswordEncoder passwordEncoder(){
        //不需要对采取编码，直接明文比较
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")//增加两个授权路径
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
                .anyRequest().permitAll()//除了/r/**，其它的访问请求可以访问
                .and()
                .formLogin()//允许表单登陆
                .successForwardUrl("/login-success");//自定义登陆成功的页面地址
    }
}
