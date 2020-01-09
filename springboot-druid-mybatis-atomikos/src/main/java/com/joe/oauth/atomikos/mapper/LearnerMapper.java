package com.joe.oauth.atomikos.mapper;

import com.joe.oauth.atomikos.pojo.Learner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface LearnerMapper {

    List<Learner> selectAll(String dataSource);

    void save(String dataSource,@Param("lea")Learner learner);

    Learner selectById(String dataSource, int id);

    int modify(String dataSource,@Param("lea") Learner learner);

    void delete(String dataSource,int id);

}
