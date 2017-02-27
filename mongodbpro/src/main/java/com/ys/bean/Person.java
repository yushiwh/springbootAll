package com.ys.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by yushi on 2017/2/15.
 */
@Document//表示注解映射领域模型和MongoDb的文档
public class Person {
    @Id//表名这个属性文档问Id
    private String id;

    private String name;

    private Integer age;

    private String address;
    @Field("locs")//注解文档名称为locs,locations属性将以数组的形式存在当前数据的记录中
    private Collection<Location> locations = new LinkedHashSet<Location>();


    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }
}
