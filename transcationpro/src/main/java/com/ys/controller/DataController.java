package com.ys.controller;

import com.ys.bean.Person;
import com.ys.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST的演示
 */
@RestController
public class DataController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/rollback")//localhost:8080/rollback?name=喻适&address=武汉&age=22
    public Person test(Person person) {
        return demoService.savePersonWithRollBack(person);

    }

    @RequestMapping("/rolloutback")//localhost:8080/rolloutback?name=哈哈&address=武汉&age=22
    public Person testout(Person person) {
        return demoService.savePersonWithOutRollBack(person);

    }
}
