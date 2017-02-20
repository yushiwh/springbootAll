package com.ys.controller;

import com.ys.bean.PersonRedis;
import com.ys.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/20.
 */
@RestController
public class RedisController {

    @Autowired
    PersonDao personDao;

    @RequestMapping("/set")
    public void set() {
        PersonRedis personRedis = new PersonRedis("1", "ys", 22);
        personDao.save(personRedis);
        personDao.stringRedisTemplateDemo();
    }


    @RequestMapping("/getStr")
    public String getStr() {
        return personDao.getString();
    }

    @RequestMapping("/getPerson")
    public PersonRedis getPerson() {
        return personDao.getPerson();
    }

}
