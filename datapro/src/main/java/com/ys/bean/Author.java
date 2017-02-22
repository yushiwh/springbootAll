package com.ys.bean;

import org.springframework.stereotype.Component;

/**
 * 定义一个实体用来接收配置的参数
 */
@Component
public class Author {

    private int age;

    private String name;


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
