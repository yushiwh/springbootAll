package com.ys.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 定义一个实体用来接收配置的参数
 */
@Component
@ConfigurationProperties(prefix = "author")//匹配开头的文件的前缀
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
