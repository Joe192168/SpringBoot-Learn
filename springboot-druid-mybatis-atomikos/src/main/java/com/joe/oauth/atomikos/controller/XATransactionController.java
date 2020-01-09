package com.joe.oauth.atomikos.controller;

import com.joe.oauth.atomikos.constant.Data;
import com.joe.oauth.atomikos.pojo.Programmer;
import com.joe.oauth.atomikos.services.IProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : joe
 * @description : 测试分布式事务
 */
@RestController
public class XATransactionController {

    @Autowired
    private IProgrammerService programmerService;


    @RequestMapping("/db/change")
    @Transactional
    public void changeDb() {
        Programmer programmer01 = new Programmer(1, "db1", 100, 6662.32f, new Date());
        Programmer programmer02 = new Programmer(1, "db2", 100, 6662.32f, new Date());
        programmerService.modify(Data.DATASOURCE1, programmer01);
        programmerService.modify(Data.DATASOURCE2, programmer02);
    }

    @RequestMapping("ts/db/change")
    @Transactional
    public void changeTsDb() {
        Programmer programmer01 = new Programmer(1, "db1", 99, 6662.32f, new Date());
        Programmer programmer02 = new Programmer(1, "db2", 99, 6662.32f, new Date());
        programmerService.modify(Data.DATASOURCE1, programmer01);
        programmerService.modify(Data.DATASOURCE2, programmer02);
        int i = 1 / 0;
    }


}