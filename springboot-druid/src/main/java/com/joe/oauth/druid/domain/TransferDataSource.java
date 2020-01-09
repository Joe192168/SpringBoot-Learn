package com.joe.oauth.druid.domain;

import java.io.Serializable;

/**
 * @program: geometry-bi
 * @description: 数据源接收类
 * @author: lihaoxun
 * @create: 2018-11-30 10:11
 * @version: 3.0.0
 */
public class TransferDataSource implements Serializable {

    private static final long serialVersionUID = -802990098377262758L;
    /**
     * 显示名称
     */
    private String name;

    /**
     * 数据库地址
     */
    private String address;

    /**
     * 端口号
     */
    private String port;

    /**
     * 连接名称
     */
    private String conneCtionName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 数据库名称(ORACLE,MYSQL,POSTGRESQL,SQLSERVER)
     */
    private String dataBaseName;

    /**
     * Schema
     */
    private String schemaName;

    /**
     * 驱动类
     */
    private String driver;

    /**
     * 地址
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getConneCtionName() {
        return conneCtionName;
    }

    public void setConneCtionName(String conneCtionName) {
        this.conneCtionName = conneCtionName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
