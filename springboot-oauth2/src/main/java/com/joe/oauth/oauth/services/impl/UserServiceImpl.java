package com.joe.oauth.oauth.services.impl;

import com.joe.oauth.oauth.entity.SysUser;
import com.joe.oauth.oauth.repository.SysUserRepository;
import com.joe.oauth.oauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author joe
 * @date 2019-02-12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}
