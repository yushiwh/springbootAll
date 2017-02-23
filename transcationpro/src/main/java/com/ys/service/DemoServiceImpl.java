package com.ys.service;

import com.ys.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yushi on 2017/2/23.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})//指定特定的异常回滚
    public Person savePersonWithRollBack(Person person) {

        Person p = personRepository.save(person);
        if (person.getName().equals("喻适")) {
            throw new IllegalArgumentException("喻适名字已经存在，数据回滚");//模拟异常
        }

        return p;
    }

    @Override
    @Transactional(noRollbackFor = IllegalArgumentException.class)//指定不回滚的异常
    public Person savePersonWithOutRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("哈哈")) {
            throw new IllegalArgumentException("哈哈名字已经存在，但是数据不会回滚");//模拟异常
        }

        return p;
    }
}
