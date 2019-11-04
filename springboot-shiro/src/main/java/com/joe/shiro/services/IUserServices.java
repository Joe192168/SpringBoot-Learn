package com.joe.shiro.services;

import com.joe.shiro.pojo.User;

import java.util.List;

public interface IUserServices {

    public User getUserByName(String username);

    public List<String> getRolesByUsername(String username);
}
