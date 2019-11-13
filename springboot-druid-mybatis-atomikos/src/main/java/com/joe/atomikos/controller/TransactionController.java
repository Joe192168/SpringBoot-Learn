package com.joe.atomikos.controller;

import com.joe.atomikos.constant.Data;
import com.joe.atomikos.pojo.Programmer;
import com.joe.atomikos.services.IProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : joe
 * @description : 测试单数据库事务
 */
@RestController
public class TransactionController {

    @Autowired
    private IProgrammerService programmerService;

    @RequestMapping("db/save")
    @Transactional
    public void saveDb1() {
        Programmer programmer = new Programmer("db1", 99, 6662.32f, new Date());
        programmerService.save(Data.DATASOURCE1, programmer);
    }

    @RequestMapping("/db/change1")
    @Transactional
    public void changeTsDb1() {
        Programmer programmer = new Programmer(1, "db1", 100, 6662.32f, new Date());
        programmerService.modify(Data.DATASOURCE1, programmer);
    }

    @RequestMapping("/db/change2")
    @Transactional
    public void changeTsDb2() {
        Programmer programmer = new Programmer(1, "db1", 88, 6662.32f, new Date());
        programmerService.modify(Data.DATASOURCE1, programmer);
        // 抛出异常 查看回滚
        int j = 1 / 0;
    }

}