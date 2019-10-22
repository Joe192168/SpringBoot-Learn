package com.joe.druid.metedata;

import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.List;
import java.util.Map;

public interface MetaLoader {

    /**
     * 获得数据库的一些相关信息
     */
    Map<String, Object> getDataBaseInformations();

    /**
     * 获得该用户下面的所有表
     */
    List<Map<String, Object>> getAllTableList();

    /**
     * 获得该用户下面的所有视图
     */
    List<Map<String, Object>> getAllViewList();

    /**
     * 获得数据库中所有方案名称
     */
    List<String> getAllSchemas();

    /**
     * 获得表或视图中的所有列信息
     */
    List<Map<String, Object>> getTableColumns(String tableName);

    /**
     * 获得一个表的索引信息
     */
    List<Map<String, Object>> getIndexInfo(String tableName);

    /**
     * 获得一个表的主键信息
     */
    List<Map<String, Object>> getAllPrimaryKeys(String tableName);

    /**
     * 获得一个表的外键信息
     */
    List<Map<String, Object>> getAllExportedKeys(String tableName);

    /**
     * 执行sql并返回list集合
     * @param sql
     * @return
     */
    List<Map<String, Object>> getQuerySQLByList(String sql);

    /**
     * 查询sql属性
     * @param sql
     * @return
     */
    List<Map<String, Object>> getListColumn(String sql);

    <T> T query(String sql, String exceptionMessage, ResultSetExtractor<T> rsExtractor, Object... args);
}
