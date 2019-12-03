package com.joe.oauth.services;

import com.joe.oauth.entity.SysUser;

/**
 * @author joe
 * @date 2019-02-12
 */
public interface UserService {

    SysUser getByUsername(String username);
}
