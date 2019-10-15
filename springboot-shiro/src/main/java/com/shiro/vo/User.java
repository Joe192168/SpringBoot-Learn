package com.shiro.vo;

/**
 * @program: shrio-study
 * @description:
 * @author: 肖乔辉
 * @create: 2019-10-08 13:41
 * @version: 1.0.0
 */
public class User {

    private String username;
    private String password;
    private boolean rememberMe;

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

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}