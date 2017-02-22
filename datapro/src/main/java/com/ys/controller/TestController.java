package com.ys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/5.
 */
@RestController
public class TestController {


    @RequestMapping("/ok")
    public String index() {
        return "ok";
    }


}
