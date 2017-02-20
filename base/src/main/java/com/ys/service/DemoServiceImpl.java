package com.ys.service;

import com.ys.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yushi on 2017/2/16.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})//特定的异常数据回滚
    public Person savePersonWithRollBack(Person person) {

        Person p = personRepository.save(person);
        if (p.getName().equals("喻适")) {
            throw new IllegalArgumentException("喻适已经存在，数据回滚");
        }

        return p;
    }

    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class})//特定的异常数据回滚
    public Person savePersonWithRollOutBack(Person person) {
        Person p = personRepository.save(person);
        if (p.getName().equals("喻适")) {
            throw new IllegalArgumentException("喻适虽然已经存在，但是数据不会回滚");
        }

        return p;
    }

    @Override
    @CachePut(value = "perple", key = "#person.id")//CachePut缓存新增的或者更新的数据到缓存
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为：" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value = "people")//从缓存中删除key为id的数据
    public void remove(Long id) {
        System.out.println("删除了 id、key为" + id + "的数据缓存");
        personRepository.delete(id);
    }

    @Override
    @Cacheable(value = "people", key = "#person.id")//缓存数据
    public Person findOne(Person person) {
        Person p = personRepository.findOne(person.getId());
        System.out.println("为id、key为：" + p.getId() + "数据做了缓存");
        return p;
    }
}
