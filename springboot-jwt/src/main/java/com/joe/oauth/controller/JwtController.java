package com.joe.oauth.controller;

import com.idsmanager.dingdang.jwt.DingdangUserRetriever;
import org.springframework.ui.Model;
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

        //1.使用公钥，解析 id_token
        // 使用PublicKey解密上一步获取的 id_token 令牌
        String PublicKey = "{\"kty\":\"RSA\",\"kid\":\"6185075449758146752\",\"alg\":\"RS256\",\"n\":\"gySnYHNMhTYDxhRx1-79BVf-Pj9He8y6Ecn3QGwW6De8ONc1eNWD25XBhDZwkHrh8g3bZY0ckPf0PE78IQh4GBvhx7pdkztvxnqhLO_GgK96JEYvI5B_whrASEfKMHBcnnSJKduhUMm5UaNcGutMXPsu9hxltQeFXGNEw74oaZcTfukK1GJj9HvlA26Lw5s_L0nc1M5_X9AMqtMW6mcn3DnuEeE9k8Z4ZW1035VBMNONZy7syYI4wK8umVqjXvN8MZFqyK5PRx3lrg4YhtcGE_kZb_JiZuwXNuKUTJW9dpJqTqGdG9s5P9hHE25UdUKResQngyrHkbNGlM8O7EmCqQ\",\"e\":\"AQAB\"}";
        DingdangUserRetriever retriever = new DingdangUserRetriever( id_token,PublicKey);
        DingdangUserRetriever.User user = null;
        try {
            //2.获取用户信息
            user = retriever.retrieve();
        } catch (Exception e) {
            return "error";
        }
        return user.getName();
    }

}
