package com.joe.shiro.services.impl;

import com.joe.shiro.mapper.UserMapper;
import com.joe.shiro.pojo.User;
import com.joe.shiro.services.IUserServices;
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
