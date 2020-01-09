package com.joe.oauth.shiro.mapper;

import com.joe.oauth.shiro.pojo.User;

import java.util.List;

public interface UserMapper {

    public User getUserByName(String username);

    public List<String> getRolesByUsername(String username);
}
