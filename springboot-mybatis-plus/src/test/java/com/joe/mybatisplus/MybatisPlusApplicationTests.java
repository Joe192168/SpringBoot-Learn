package com.joe.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.joe.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        try {
            //分页的简便语法。不用专门定义分页类。
            int current=1,size=3;
            IPage<Map<String,Object>> page = new Page<>(current, size);
            LambdaQueryWrapper<Map<String,Object>> wrapper = new LambdaQueryWrapper<>();
            /*wrapper.like(User::getName, "刘晓");
            wrapper.orderByDesc(User::getId);*/
            //page = userMapper.selectMapsPage(page,wrapper);

            EntityWrapper<Object> ew = new EntityWrapper<Object>();



            System.out.println("Total: "+page.getTotal()+" ; Size: "+page.getSize()+" ; Current: "+page.getCurrent());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
