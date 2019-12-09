package com.joe.oauth;

import com.joe.oauth.services.MyUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OauthApplicationTests {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Test
    void contextLoads() {
        System.out.println("加密："+myUserDetailsService.encode("order123"));
    }

}
