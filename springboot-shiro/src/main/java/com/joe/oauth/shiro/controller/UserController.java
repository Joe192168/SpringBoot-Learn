package com.joe.oauth.shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello shiro!");
        return "ok";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/index")
    public String testThymeleaf(Model model){
        //把数据放到model
        model.addAttribute("username","xiaoming");
        //返回test.html
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //使用Shiro认证操作
        //获取Subject
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        //执行登陆
        try {
            //登陆成功
            subject.login(token);
            return "redirect:index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            //登陆失败，用户名错误
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            //登陆失败,密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

}