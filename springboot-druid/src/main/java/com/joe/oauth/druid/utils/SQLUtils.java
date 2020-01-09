package com.joe.oauth.druid.utils;

import org.junit.platform.commons.util.StringUtils;

public class SQLUtils {

    private static final int PAGE_SIZE=10;

    /**
     * 拼接分页查询sql<br/>
     * ORACLE,SQLSERVER,DB2,DM通用
     *
     * @param field
     * @param sql
     * @param pageSize
     * @param pageNo
     * @param orderBy
     * @return
     */
    public static String joinPageQuerySql(final String field, final String sql, final String pageSize, final String pageNo,
                                          final String orderBy) {
        int pageNum = 1;
        int sizeNum = SQLUtils.PAGE_SIZE;
        if (StringUtils.isNotBlank(pageNo)) {
            pageNum = Integer.parseInt(pageNo);
            if (pageNum < 1) {
                pageNum = 1;
            }
        }
        if (StringUtils.isNotBlank(pageSize)) {
            sizeNum = Integer.parseInt(pageSize);
            if (sizeNum < 0) {
                sizeNum = SQLUtils.PAGE_SIZE;
            }
        }
        StringBuilder buff = new StringBuilder("SELECT ");
        buff.append(field);
        buff.append(" FROM (SELECT ROW_NUMBER() OVER (ORDER BY ");
        buff.append(StringUtils.isNotBlank(orderBy) ? orderBy : field);
        buff.append(") AS RN,PAGE1.* FROM(");
        buff.append(sql);
        buff.append(") PAGE1 ) PAGE2 WHERE RN <= ");
        buff.append(pageNum * sizeNum);
        buff.append(" AND RN > ");
        buff.append((pageNum - 1) * sizeNum);
        return buff.toString();
    }

}
