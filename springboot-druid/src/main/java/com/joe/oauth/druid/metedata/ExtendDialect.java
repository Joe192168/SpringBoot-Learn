package com.joe.oauth.druid.metedata;

public interface ExtendDialect {

    public String getLimitString(String _sql, int _start, int _limit);
}
