package com.ys.controller;

import com.ys.bean.Person;
import com.ys.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/5.
 */
@RestController
public class CacheController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/put")
    public Person putPerson(Person person) {
        return demoService.save(person);
    }

    @RequestMapping("/able")
    public Person cachePerson(Person person) {
        return demoService.findOne(person);
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        demoService.remove(id);
        return "ok";
    }


}
