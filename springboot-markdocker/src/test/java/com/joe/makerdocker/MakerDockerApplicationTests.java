package com.joe.makerdocker;

import com.joe.makerdocker.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MakerDockerApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        /*Map<String, String> map = new HashMap<>(16);
        map.put("db-type", "redis");
        redisUtil.set("data", map); // 结果： ["java.util.HashMap",{"db-type":"redis"}]*/
        redisUtil.set("v-data","小明");//结果：[]
        /*Map<String, String> data = (Map<String, String>) redisUtil.get("data");
        System.out.println(data.get("db-type")); // 结果： red*/
        System.out.println(redisUtil.get("v-data")); // 结果： 小明
    }

}
