package com.joe.mapper;

import com.joe.entity.Users;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Joe
 * @since 2019-11-01
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}