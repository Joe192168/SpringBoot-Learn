package com.joe.atomikos.mapper;

import com.joe.atomikos.pojo.Programmer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author : joe
 */
@Mapper
public interface ProgrammerMapper {

    List<Programmer> selectAll(String dataSource);

    void save(String dataSource,@Param("pro")Programmer programmer);

    Programmer selectById(String dataSource,int id);

    int modify(String dataSource,@Param("pro") Programmer programmer);

    void delete(String dataSource,int id);
}