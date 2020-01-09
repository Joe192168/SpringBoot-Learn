package com.joe.oauth.druid.metedata;

public class ExtendDB2Dialect implements ExtendDialect {
    @Override
    public String getLimitString(String _sql, int _start, int _limit) {
        return new StringBuffer(_sql.length()+20 )
                .append("SELECT * FROM  ( SELECT B.*, ROWNUMBER() OVER() AS RN FROM ( ")
                .append(_sql)
                .append(" ) AS B )AS A WHERE A.RN BETWEEN "+_start+" AND "+_limit+"")
                .toString();
    }
}
