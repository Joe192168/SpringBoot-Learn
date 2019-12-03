package com.joe.oauth.services;

import com.joe.oauth.entity.SysPermission;

import java.util.List;

/**
 * @author joe
 * @date 2019-02-12
 */
public interface PermissionService {

    List<SysPermission> findByUserId(Integer userId);

}
