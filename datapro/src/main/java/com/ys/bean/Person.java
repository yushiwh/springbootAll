package com.ys.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * 定义一个JPA使用的实体类，不需要进行关联对应的表
 * <p>
 * 没有通过@Column啦映射表和属性的名称
 * 不注解的时候，会自动根据属性名生成数据表的字段名
 * name映射成NAME
 * testName映射成TEST_NAME
 * 表名的映射规则也是如此
 * <p>
 * <p>
 * Created by yushi on 2017/2/15.
 */
@Entity//注解指明这是一个和数据库表映射的实体类
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and address=?2")
public class Person {
    @Id//数据库的主键
    @GeneratedValue//默认自增
    private Long id;

    private String name;

    private Integer age;

    private String address;


    public Person() {
        super();
    }

    public Person(Long id, String name, Integer age, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
