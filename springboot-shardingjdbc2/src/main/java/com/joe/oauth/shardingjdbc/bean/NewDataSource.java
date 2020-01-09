package com.joe.oauth.shardingjdbc.bean;

import java.io.Serializable;

public class NewDataSource implements Serializable {

    private static final long serialVersionUID = 8987455574943493788L;
    //数据源用户名
    private String username;
    //数据源密码
    private String password;
    //数据源ip
    private String dataIp;
    //数据源端口
    private String dataPort;
    //数据源类型
    private String dataType;
    //数据源名称
    private String dataName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataIp() {
        return dataIp;
    }

    public void setDataIp(String dataIp) {
        this.dataIp = dataIp;
    }

    public String getDataPort() {
        return dataPort;
    }

    public void setDataPort(String dataPort) {
        this.dataPort = dataPort;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
