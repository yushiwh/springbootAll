package com.ys.service;

import com.ys.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by yushi on 2017/2/25.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    Repository repository;

    @Override
    @CachePut(value = "person", key = "#person.id")//新增一个缓存
    public Person save(Person person) {
        Person p = repository.save(person);
        System.out.println("为id、key为：" + person.getId() + "的数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value = "people")//从缓存中删除数据
    public void remove(Long id) {
        System.out.println("删出id的缓存：" + id);
    }

    @Override
    @Cacheable(value = "person", key = "#person.id")//查询后将缓存放进
    public Person findOne(Person person) {
        Person p = repository.findOne(person.getId());
        System.out.println("为id、key为：" + person.getId() + "的数据做了缓存");
        return p;
    }
}
