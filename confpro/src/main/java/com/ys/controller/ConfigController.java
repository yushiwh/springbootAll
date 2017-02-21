package com.ys.controller;

import com.ys.bean.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/5.
 */
@RestController
public class ConfigController {

    @Autowired
    private Author author;

    @RequestMapping("/conf")
    public String index() {
        return "name:" + author.getName() + "--age:" + author.getAge();
    }


}
