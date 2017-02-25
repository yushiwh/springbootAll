package com.ys.service;

import com.ys.bean.Person;

/**
 * Created by yushi on 2017/2/25.
 */
public interface DemoService {

    public Person save(Person person);

    public void remove(Long id);

    public Person findOne(Person person);

}
