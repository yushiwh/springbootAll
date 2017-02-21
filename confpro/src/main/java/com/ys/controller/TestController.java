package com.ys.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yushi on 2017/2/5.
 */
@RestController
public class TestController {

    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.name}")
    private String bookName;


    @RequestMapping("/ok")
    public String index() {
        return "Author:" + bookAuthor + "--Name:" + bookName;
    }


}
