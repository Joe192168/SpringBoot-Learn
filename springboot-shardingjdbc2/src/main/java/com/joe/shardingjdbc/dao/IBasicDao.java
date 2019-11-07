package com.joe.shardingjdbc.dao;

import java.util.List;

public interface IBasicDao<T, P> {

    void createTableIfNotExists();

    void dropTable();

    void truncateTable();

    Long insert(T entity);

    List<T> select();

    void update(T entity);

    void delete(P key);
}