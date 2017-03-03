package com.ys.bean;

import javax.validation.constraints.Size;

/**
 * 定义一个JPA使用的实体类，不需要进行关联对应的表
 * Created by yushi on 2017/2/15.
 */

public class Person {


    @Size(max = 4, min = 2)//此处使用JSR-303注解来校验数据
    private String name;

    private Integer age;

    private String address;

    private String nation;


    public Person() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
