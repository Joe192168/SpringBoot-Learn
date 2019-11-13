package com.joe.atomikos.services;

import com.joe.atomikos.pojo.Programmer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProgrammerService {

    List<Programmer> selectAll(String dataSource);

    void save(String dataSource,Programmer programmer);

    Programmer selectById(String dataSource,int id);

    int modify(String dataSource,@Param("pro") Programmer programmer);

    void delete(String dataSource,int id);

}
