package com.joe.webflux.repository;

import com.joe.webflux.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用标准的java doc,用户仓储
 * {@link User user} {@link Repository}
 */
@Repository
public class UserRepository {

    /**
     * 采用内存型的存储方式 --》 Map
     * 这块防止高并发，采用ConcurrentMap
     */
    private final ConcurrentMap<Integer,User> concurrentMap =  new ConcurrentHashMap<>();

    /**
     * 自增生成器
     */
    private final static AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 保存用户对象
     * @param user {@link User}对象
     * @return 如果保存成功，返回<code>true</code>,否则,返回<code>false</code>
     */
    public boolean save(User user){
        //id 从1开始生成
        Integer id = atomicInteger.incrementAndGet();
        user.setId(id);
        return concurrentMap.put(id,user) == null;
    }

    /**
    * 返回所有用户列表
    */
    public Collection<User> findAll(){
        return concurrentMap.values();
    }
}
