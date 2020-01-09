package com.joe.oauth.service.impl;

import com.joe.oauth.entity.User;
import com.joe.oauth.mapper.UserMapper;
import com.joe.oauth.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人员信息 服务实现类
 * </p>
 *
 * @author Joe
 * @since 2019-11-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
