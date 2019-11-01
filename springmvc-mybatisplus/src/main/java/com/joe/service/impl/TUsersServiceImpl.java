package com.joe.service.impl;

import com.joe.entity.TUsers;
import com.joe.mapper.TUsersMapper;
import com.joe.service.ITUsersService;
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
public class TUsersServiceImpl extends ServiceImpl<TUsersMapper, TUsers> implements ITUsersService {

}
