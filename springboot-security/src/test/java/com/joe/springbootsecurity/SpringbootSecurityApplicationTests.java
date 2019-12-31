package com.joe.springbootsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootTest
class SpringbootSecurityApplicationTests {

    @Test
    void contextLoads() {
        //对密码进行加密
        String hashpw = BCrypt.hashpw("123",BCrypt.gensalt());
        System.out.println("加密后："+hashpw);

        //校验密码
        boolean falg = BCrypt.checkpw("123","$2a$10$TbVsZvdIwVAGAYF7B8F2h.9LBCv3BIUCxfZKBFHV/pHBT1sPAMVFm");
        System.out.println("结果："+falg);
    }

}
