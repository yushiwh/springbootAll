package com.ys.controller;

import com.ys.bean.Age;
import com.ys.bean.Person;
import com.ys.service.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * data的访问控制器
 * <p>
 * 保存
 * save支持批量的保存
 * <p>
 * 删除：支持使用id删除对象、批量删除以及删除全部
 * <p>
 * void delete(ID id);
 * void delete (T entity)
 * void delete (Iterable<? extrends T> entities)
 * void deleteAll()
 * <p>
 * <p>
 * <p>
 * Created by yushi on 2017/2/22.
 */
@RestController
public class DataController {
    @Autowired
    private PersonRepository personRepository;


    /**
     * 保存
     *
     * @param name
     * @param address
     * @param age
     * @return localhost:8080/save?name=喻适&address=武汉&age=30
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person person = personRepository.save(new Person(null, name, age, address));
        return person;
    }


    /**
     * 测试一下findByAddress
     *
     * @param address
     * @return localhost:8080/findbyaddress?address=武汉
     */
    @RequestMapping("/findbyaddress")
    public List<Person> fingByAddress(String address) {
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }

    /**
     * 测试一下findByNameAndAddress
     *
     * @param name
     * @param address
     * @return localhost:8080/findByNameAndAddress?address=武汉&name=喻适
     */
    @RequestMapping("/findByNameAndAddress")
    public List<Person> findByNameAndAddress(String name, String address) {
        List<Person> people = personRepository.findByNameAndAddress(name, address);
        return people;
    }

    /**
     * 测试withNameAndAddressQuery
     *
     * @param name
     * @param address
     * @return localhost:8080/withNameAndAddressQuery?address=武汉&name=喻适
     */
    @RequestMapping("withNameAndAddressQuery")
    public List<Person> withNameAndAddressQuery(String name, String address) {
        List<Person> people = personRepository.withNameAndAddressNamedQuery(name, address);
        return people;
    }

    /**
     * 测试一下sort
     *
     * @return localhost:8080/sort
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.DESC, "age"));
        return people;
    }

    /**
     * 测试一下分页
     *
     * @return localhost:8080/page
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
        return pagePeople;
    }

    /////////////下面是测试一下JpaRepository的各种方法/////////////////////
    @RequestMapping(value = "/and", method = RequestMethod.GET)
    public List<Person> findByNameAndAge(String name, Integer age) {
        List<Person> people = personRepository.findByNameAndAge(name, age);
        return people;
    }


    //Or功能，同where name=?1 or age=?2
    @RequestMapping("/or")
    List<Person> findByNameOrAge(String name, Integer age) {
        List<Person> people = personRepository.findByNameOrAge(name, age);
        return people;
    }

    //Is功能，同where name=?1
    @RequestMapping("/is")
    List<Person> findByNameIs(String name) {
        List<Person> people = personRepository.findByNameIs(name);
        return people;
    }

    //Between功能，同where  age between 1？ and ?2
    //localhost:8080/between?start=20&end=25
    @RequestMapping("/between")
    List<Person> findByAgeBetween(Integer start, Integer end) {
        List<Person> people = personRepository.findByAgeBetween(start, end);
        return people;
    }


    //LessThan 同 age<?1
    @RequestMapping("/lessthan")
    //localhost:8080/lessthan?age=20
    List<Person> findByAgeLessThan(Integer age) {
        List<Person> people = personRepository.findByAgeLessThan(age);
        return people;
    }

    //LessThanEquals 同 age<=?1
    @RequestMapping("/lessthanequal")
    //localhost:8080/lessthanequal?age=30
    List<Person> findByAgeLessThanEqual(Integer age) {
        List<Person> people = personRepository.findByAgeLessThanEqual(age);
        return people;
    }


    //GreaterThan 同 age>?1
    @RequestMapping("/greaterthan")
    //localhost:8080/greaterthan?age=30
    List<Person> findByAgeGreaterThan(Integer age) {
        List<Person> people = personRepository.findByAgeGreaterThan(age);
        return people;
    }

    //GreaterThanEquals 同 age>=?1
    @RequestMapping("/greaterthanequal")
    //localhost:8080/greaterthanequal?age=30
    List<Person> findByAgeGreaterThanEqual(Integer age) {
        List<Person> people = personRepository.findByAgeGreaterThanEqual(age);
        return people;
    }

    //After 同 age>?1
    @RequestMapping("/after")
    //localhost:8080/after?age=30
    List<Person> findByAgeAfter(Integer age) {
        List<Person> people = personRepository.findByAgeAfter(age);
        return people;
    }

    //Before 同 age<?1
    @RequestMapping("/before")
    //localhost:8080/before?age=30
    List<Person> findByAgeBefore(Integer age) {
        List<Person> people = personRepository.findByAgeBefore(age);
        return people;
    }


    //Like 同 where name like ?1
    @RequestMapping("/like")
    //localhost:8080/like?name=喻适
    List<Person> findByNameLike(String name) {
        List<Person> people = personRepository.findByNameLike(name);
        return people;
    }

    //NotLike 同 where name not like ?1
    @RequestMapping("/notlike")
    //localhost:8080/notlike?name=喻适
    List<Person> findByNameNotLike(String name) {
        List<Person> people = personRepository.findByNameNotLike(name);
        return people;
    }


    //StartingWith 同 where name not like ?1(前面加上%)
    @RequestMapping("/startingwith")
    //localhost:8080/startingwith?name=喻
    List<Person> findByNameStartingWith(String name) {
        List<Person> people = personRepository.findByNameStartingWith(name);
        return people;
    }

    //EndingWith 同 where name not like ?1(后面加上%)
    @RequestMapping("/endingwith")
    //localhost:8080/endingwith?name=适
    List<Person> findByNameEndingWith(String name) {
        List<Person> people = personRepository.findByNameEndingWith(name);
        return people;
    }

    //Containing 同 where name not like ?1(前后面加上%)
    @RequestMapping("/containing")
    //localhost:8080/containing?name=喻
    List<Person> findByNameContaining(String name) {
        List<Person> people = personRepository.findByNameContaining(name);
        return people;
    }


    //OrderBy 同 where name =?1 order by age desc
    @RequestMapping("/orderby")
    //localhost:8080/orderby?name=喻适
    List<Person> findByNameOrderByAgeDesc(String name) {
        List<Person> people = personRepository.findByNameOrderByAgeDesc(name);
        return people;
    }

    //Not 同 where name <>?1
    @RequestMapping("/not")
    //localhost:8080/not?name=喻适
    List<Person> findByNameNot(String name) {
        List<Person> people = personRepository.findByNameNot(name);
        return people;
    }

    //In 同 where age in ?有问题
    @RequestMapping("/in")
    List<Person> findByAgeIn() {
        Collection<Age> ages = new ArrayList<Age>();

        ages.add(new Age(20));
        ages.add(new Age(30));

        List<Person> people = personRepository.findByAgeIn(ages);
        return people;
    }

    //NotIn 同 where age in ?1有问题
    @RequestMapping("/notin")
    List<Person> findByAgeNotIn() {
        Collection<Age> ages = new ArrayList<Age>();

        ages.add(new Age(20));
        ages.add(new Age(25));

        List<Person> people = personRepository.findByAgeNotIn(ages);
        return people;
    }


    //前10条数据
    @RequestMapping("/first")
    List<Person> findFirst10ByName(String name) {
        List<Person> people = personRepository.findFirst10ByName(name);
        return people;
    }


    //前10条数据
    @RequestMapping("/top")
    List<Person> findTop30ByName(String name) {
        List<Person> people = personRepository.findTop30ByName(name);
        return people;
    }

}
