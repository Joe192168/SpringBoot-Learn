package com.joe.druid.utils;

import java.util.List;

public class PageUtil<T> {

    private Integer pageNumSum;

    private Integer pageNum;

    private Integer pageSize;

    private List<T> list;

    public Integer getPageNumSum() {
        return pageNumSum;
    }

    public void setPageNumSum(Integer pageNumSum) {
        this.pageNumSum = pageNumSum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
