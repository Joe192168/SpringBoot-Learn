package com.joe.shardingjdbc.entity;

import java.util.Date;

public class NewDataSource {
    private Integer dataId;
    private String dataIp;
    private String dataCode;
    private String dataUserName;
    private String dataUserPassword;
    private String dataremarks;
    private Integer dataState;
    private Date creatTime;
    private Date updateTime;
    private Integer dataType;
    private String dataDbName;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataIp() {
        return dataIp;
    }

    public void setDataIp(String dataIp) {
        this.dataIp = dataIp;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataUserName() {
        return dataUserName;
    }

    public void setDataUserName(String dataUserName) {
        this.dataUserName = dataUserName;
    }

    public String getDataUserPassword() {
        return dataUserPassword;
    }

    public void setDataUserPassword(String dataUserPassword) {
        this.dataUserPassword = dataUserPassword;
    }

    public String getDataremarks() {
        return dataremarks;
    }

    public void setDataremarks(String dataremarks) {
        this.dataremarks = dataremarks;
    }

    public Integer getDataState() {
        return dataState;
    }

    public void setDataState(Integer dataState) {
        this.dataState = dataState;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataDbName() {
        return dataDbName;
    }

    public void setDataDbName(String dataDbName) {
        this.dataDbName = dataDbName;
    }

    @Override
    public String toString() {
        return "NewDataSource{" +
                "dataId=" + dataId +
                ", dataIp='" + dataIp + '\'' +
                ", dataCode='" + dataCode + '\'' +
                ", dataUserName='" + dataUserName + '\'' +
                ", dataUserPassword='" + dataUserPassword + '\'' +
                ", dataremarks='" + dataremarks + '\'' +
                ", dataState=" + dataState +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                ", dataType=" + dataType +
                ", dataDbName='" + dataDbName + '\'' +
                '}';
    }
}
