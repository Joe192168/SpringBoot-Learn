package com.joe.oauth.shiro.services.impl;

import com.joe.oauth.shiro.pojo.User;
import com.joe.oauth.shiro.mapper.UserMapper;
import com.joe.oauth.shiro.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        return userMapper.getRolesByUsername(username);
    }
}
