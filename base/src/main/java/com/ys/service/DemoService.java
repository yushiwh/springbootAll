package com.ys.service;

import com.ys.bean.Person;

/**
 * 业务服务类接口，主要是看看事务
 * Created by yushi on 2017/2/16.
 */
public interface DemoService {
    Person savePersonWithRollBack(Person person);

    Person savePersonWithRollOutBack(Person person);


    ///下面为测试缓存技术的接口
    Person save(Person person);

    void remove(Long id);

    Person findOne(Person person);

}
