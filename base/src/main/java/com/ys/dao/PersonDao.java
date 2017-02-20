package com.ys.dao;

import com.ys.bean.PersonRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by yushi on 2017/2/20.
 */
@Repository
public class PersonDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;// 直接植入StringRedisTemplate

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;// 使用@Resource指定stringRedisTemplate，可注入基于字符串的简单属性操作方法

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;// 直接植入RedisTemplate

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps;// 使用@Resource指定redisTemplate，可注入基于对象的简单属性操作方法

    public void stringRedisTemplateDemo() {
        valOpsStr.set("xx", "yy");//通过set方法，存储字符串类型
    }


    public void save(PersonRedis personRedis) {
        valOps.set(personRedis.getId(), personRedis);//通过set方法，存储对象
    }


    public String getString() {
        return valOpsStr.get("xx");//通过get方法取出字符串
    }

    public PersonRedis getPerson() {
        return (PersonRedis) valOps.get("1");//通过get方法取出对象
    }


}
