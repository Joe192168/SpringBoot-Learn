package com.joe.oauth.service.impl;

import com.joe.oauth.entity.Users;
import com.joe.oauth.mapper.UsersMapper;
import com.joe.oauth.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joe
 * @since 2019-11-12
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
