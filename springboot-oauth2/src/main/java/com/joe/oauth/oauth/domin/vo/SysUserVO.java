package com.joe.oauth.oauth.domin.vo;

import com.joe.oauth.oauth.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author joe
 * @date 2019-02-12
 */
@Data
public class SysUserVO extends SysUser {

    /**
     * 权限列表
     */
    private List<String> authorityList;

}
