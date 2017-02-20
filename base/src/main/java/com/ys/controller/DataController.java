package com.ys.controller;

import com.ys.bean.Person;
import com.ys.service.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * JPA的访问的路径和调用
 */
@RestController
public class DataController {
    //1 Spring Data JPA已自动为你注册bean，所以可自动注入
    @Autowired
    PersonRepository personRepository;

    /**
     * 保存
     * save支持批量保存：<S extends T> Iterable<S> save(Iterable<S> entities);
     * <p>
     * 删除：
     * 删除支持使用id，对象以，批量删除及删除全部：
     * void delete(ID id);
     * void delete(T entity);
     * void delete(Iterable<? extends T> entities);
     * void deleteAll();
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {

        Person p = personRepository.save(new Person(null, name, age, address));

        return p;

    }


    /**
     * 测试findByAddress
     */
    @RequestMapping("/q1")
    public List<Person> q1(String address) {

        List<Person> people = personRepository.findByAddress(address);

        return people;

    }

    @RequestMapping("/q1like")
    public List<Person> q1like(String address) {

        List<Person> people = personRepository.findByAddressContaining(address);

        return people;

    }

    @RequestMapping("/q1likefirst")
    public List<Person> findByAddressStartingWith(String address) {

        List<Person> people = personRepository.findByAddressStartingWith(address);

        return people;

    }

    @RequestMapping("/q1likeend")
    public List<Person> findByAddressEndingWith(String address) {

        List<Person> people = personRepository.findByAddressEndingWith(address);

        return people;

    }

    /**
     * 测试findByNameAndAddress
     */
    @RequestMapping("/q2")
    public Person q2(String name, String address) {

        Person people = personRepository.findByNameAndAddress(name, address);

        return people;

    }

    /**
     * 测试withNameAndAddressQuery
     */
    @RequestMapping("/q3")
    public Person q3(String name, String address) {

        Person p = personRepository.withNameAndAddressQuery(name, address);

        return p;

    }

    /**
     * 测试withNameAndAddressNamedQuery
     */
    @RequestMapping("/q4")
    public Person q4(String name, String address) {

        Person p = personRepository.withNameAndAddressNamedQuery(name, address);

        return p;

    }

    /**
     * 测试排序
     */
    @RequestMapping("/sort")
    public List<Person> sort() {

        List<Person> people = personRepository.findAll(new Sort(Direction.ASC, "age"));

        return people;

    }

    /**
     * 测试分页
     */
    @RequestMapping("/page")
    public Page<Person> page() {

        Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));

        return pagePeople;

    }


    //@RequestMapping("/auto")
    //public Page<Person> auto(Person person) {
    //
    //    Page<Person> pagePeople = personRepository.findByAuto(person, new PageRequest(0, 10));
    //
    //    return pagePeople;
    //
    //}


}
