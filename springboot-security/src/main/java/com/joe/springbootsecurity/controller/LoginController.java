package com.joe.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot-learn
 * @description: 登陆控制器
 * @author: xiaoqiaohui
 * @create: 2019-12-27 13:25
 **/
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String login(){
        return "登陆成功";
    }

    /**
     * 访问资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('p1')")//根据方法注解的方式进行权限校验
    public String r1(){
        return "访问资源1";
    }

    /**
     * 访问资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAnyAuthority('p1','p3')")//根据方法注解的方式进行权限校验
    public String r2(){
        return "访问资源2";
    }

}
