package com.ys.controller;

import com.ys.bean.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/5.
 */
@RestController
@RequestMapping("/testController")
public class TestController {

    @Autowired
    private AuthorSettings author;

    @RequestMapping("/test")
    public String  index(){
        return "姓名:"+author.getName()+"----"+"年龄:"+author.getAge();
    }




}
