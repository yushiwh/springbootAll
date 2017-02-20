package com.ys.controller;

import com.ys.bean.Person;
import com.ys.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/16.
 */
@RestController
public class TranscationController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/rollback")
    public Person rollBack(Person person) {
        return demoService.savePersonWithRollBack(person);
    }


    @RequestMapping("/norollback")
    public Person noRollBack(Person person) {
        return demoService.savePersonWithRollOutBack(person);
    }
}
