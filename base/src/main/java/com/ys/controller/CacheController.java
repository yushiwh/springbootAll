package com.ys.controller;

import com.ys.bean.Person;
import com.ys.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存数据的调用
 */
@RestController
public class CacheController {

    @Autowired
    DemoService demoService;

    /**
     * 保存
     */
    @RequestMapping("/put")
    public Person put(Person person) {
        return demoService.save(person);
    }


    /**
     * 测试findByAddress
     */
    @RequestMapping("/able")
    public Person cacheable(Person person) {
        return demoService.findOne(person);

    }

    @RequestMapping("/evit")
    public String evit(Long id) {

        demoService.remove(id);

        return "OK";

    }


}
