package com.joe.service.impl;

import com.joe.entity.Users;
import com.joe.mapper.UsersMapper;
import com.joe.service.IUsersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joe
 * @since 2019-11-01
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
