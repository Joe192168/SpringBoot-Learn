package com.joe.shiro.mapper;

import com.joe.shiro.pojo.User;

import java.util.List;

public interface UserMapper {

    public User getUserByName(String username);

    public List<String> getRolesByUsername(String username);
}
