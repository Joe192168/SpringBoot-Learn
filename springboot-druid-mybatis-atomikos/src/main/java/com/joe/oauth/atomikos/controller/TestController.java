package com.joe.oauth.atomikos.controller;

import com.joe.oauth.atomikos.constant.Data;
import com.joe.oauth.atomikos.pojo.Programmer;
import com.joe.oauth.atomikos.services.IProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : joe
 * @description : 测试controller
 */

@RestController
public class TestController {


    @Autowired
    private IProgrammerService programmerService;

    /**
     * 查询全部数据源的数据
     */
    @GetMapping("/db/programmers")
    public List<Programmer> getAllProgrammers() {
        List<Programmer> programmers = programmerService.selectAll(Data.DATASOURCE1);
        programmers.addAll(programmerService.selectAll(Data.DATASOURCE2));
        return programmers;
    }

    @GetMapping("ts/db/programmers")
    @Transactional
    public List<Programmer> getAllProgrammersTs() {
        List<Programmer> programmers = programmerService.selectAll(Data.DATASOURCE1);
        programmers.addAll(programmerService.selectAll(Data.DATASOURCE2));
        return programmers;
    }

    /**
     * 不指定就使用默认的数据源
     */
    @GetMapping("/db1/programmers")
    public List<Programmer> getDB1Programmers() {

        return programmerService.selectAll(null);
    }

    /**
     * 从指定数据源查询
     */
    @GetMapping("/db2/programmers")
    public List<Programmer> getDB2Programmers() {
        return programmerService.selectAll(Data.DATASOURCE2);
    }


}