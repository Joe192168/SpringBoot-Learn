package com.joe.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot-learn
 * @description:
 * @author: joe
 * @create: 2019-12-25 10:03
 **/
@RestController
public class JwtController {


    // id_token 是 IDaaS 请求时带来的，在 body 里获取，PublicKey是在 IDaaS 里注册应用时生成的，注册完可见，此示例代码是获取用户信息。
    // JWT SSO
    @RequestMapping(value = "/sso/login")
    public String SSOUrl(@RequestParam String id_token, String redirect_url, Model model, HttpServletRequest request){
    //1.接收方法为GET方式,参数名为 id_token
    //2.<解析令牌>为解析 id_token 并验证代码
        //1.使用公钥，解析 id_token
        return id_token;
    }

    @GetMapping("/oauth/index")
    public String oauthIndex(){
        return "hello oauth!";
    }

}
