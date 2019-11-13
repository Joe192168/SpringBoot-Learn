package com.joe.atomikos.services.impl;

import com.joe.atomikos.constant.Data;
import com.joe.atomikos.mapper.LearnerMapper;
import com.joe.atomikos.mapper.ProgrammerMapper;
import com.joe.atomikos.pojo.Learner;
import com.joe.atomikos.pojo.Programmer;
import com.joe.atomikos.services.IProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProgrammerServiceImpl implements IProgrammerService {

    @Autowired
    private ProgrammerMapper programmerMapper;
    @Autowired
    private LearnerMapper learnerMapper;

    @Override
    public List<Programmer> selectAll(String dataSource) {
        return programmerMapper.selectAll(dataSource);
    }

    @Override
    public void save(String dataSource, Programmer programmer) {
        programmerMapper.save(dataSource,programmer);

        Learner learner1 = new Learner("db1", 99, 6662.32f, new Date());
        learnerMapper.save(Data.DATASOURCE1,learner1);

        Learner learner2 = new Learner("db2", 99, 6662.32f, new Date());
        learnerMapper.save(Data.DATASOURCE2,learner2);

        int j = 1 / 0;
    }

    @Override
    public Programmer selectById(String dataSource,int id) {
        return programmerMapper.selectById(dataSource,id);
    }

    @Override
    public int modify(String dataSource, Programmer programmer) {
        int no1 = programmerMapper.modify(dataSource,programmer);

        Learner learner = new Learner(1,"db2", 100, 6662.32f, new Date());
        int no2 = learnerMapper.modify(Data.DATASOURCE2,learner);

        return no1 + no2;
    }

    @Override
    public void delete(String dataSource,int id) {
        programmerMapper.delete(dataSource,id);
    }
}
