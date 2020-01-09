package com.joe.oauth.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joe.oauth.mybatisplus.domain.User;
import com.joe.oauth.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/page")
    public IPage<User> listPage(){
        //分页的简便语法。不用专门定义分页类。
        int current=1,size=3;
        IPage<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "刘晓");
        wrapper.orderByDesc(User::getId);
        try {
            page = userMapper.selectPage( page, wrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total: "+page.getTotal()+" ; Size: "+page.getSize()+" ; Current: "+page.getCurrent());
        return page;
    }
}
