package com.joe.oauth.shiro.services;

import com.joe.oauth.shiro.pojo.User;

import java.util.List;

public interface IUserServices {

    public User getUserByName(String username);

    public List<String> getRolesByUsername(String username);
}
