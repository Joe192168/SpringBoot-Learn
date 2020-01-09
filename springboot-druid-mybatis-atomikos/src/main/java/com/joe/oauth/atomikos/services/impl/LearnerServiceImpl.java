package com.joe.oauth.atomikos.services.impl;

import com.joe.oauth.atomikos.pojo.Learner;
import com.joe.oauth.atomikos.services.ILearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnerServiceImpl implements ILearnerService {

    @Autowired
    private ILearnerService iLearnerService;

    @Override
    public List<Learner> selectAll(String dataSource) {
        return iLearnerService.selectAll(dataSource);
    }

    @Override
    public void save(String dataSource, Learner learner) {
        iLearnerService.save(dataSource,learner);
    }

    @Override
    public Learner selectById(String dataSource, int id) {
        return iLearnerService.selectById(dataSource,id);
    }

    @Override
    public int modify(String dataSource, Learner learner) {
        return iLearnerService.modify(dataSource,learner);
    }

    @Override
    public void delete(String dataSource, int id) {
        iLearnerService.delete(dataSource,id);
    }
}
