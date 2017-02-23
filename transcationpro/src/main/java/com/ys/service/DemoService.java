package com.ys.service;

import com.ys.bean.Person;

/**
 * Created by yushi on 2017/2/23.
 */
public interface DemoService {

    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithOutRollBack(Person person);


}
