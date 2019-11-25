package com.joe.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.joe.quartz.mapper")
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}
