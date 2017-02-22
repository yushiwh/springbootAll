package com.ys.service;

import com.ys.bean.Age;
import com.ys.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by yushi on 2017/2/22.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    //使用方法名查询，接受name参数，返回值为列表
    List<Person> findByAddress(String name);

    //使用方法名查询，接受name和address两个参数，返回值为单个对象
    List<Person> findByNameAndAddress(String name, String address);

    //使用@Query查询，参数按照名称进行绑定
    @Query("select p from Person p where p.name =:name and p.address=:address")
    List<Person> withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    //使用NameQuery查询，注意在实体中定义的@NameQuery的定义
    List<Person> withNameAndAddressNamedQuery(String name, String address);


    ////根据书本上面251页的查询关键字来写的方法////////////////

    //And功能，同where name=?1 and age=?2
    List<Person> findByNameAndAge(String name, Integer age);

    //Or功能，同where name=?1 or age=?2
    List<Person> findByNameOrAge(String name, Integer age);

    //Is功能，同where name=?1
    List<Person> findByNameIs(String name);

    //Between功能，同where  age between 1？ and ?2
    List<Person> findByAgeBetween(Integer start, Integer end);


    //LessThan 同 age<?1
    List<Person> findByAgeLessThan(Integer age);

    //LessThanEquals 同 age<=?1
    List<Person> findByAgeLessThanEqual(Integer age);


    //GreaterThan 同 age>?1
    List<Person> findByAgeGreaterThan(Integer age);

    //GreaterThanEquals 同 age>=?1
    List<Person> findByAgeGreaterThanEqual(Integer age);


    //After 同 age>?1
    List<Person> findByAgeAfter(Integer age);

    //Before 同 age<?1
    List<Person> findByAgeBefore(Integer age);


    //IsNull 同 age is null
    List<Person> findByAgeIsNull(Integer age);

    //IsNotNull 同 age is not null
    List<Person> findByAgeIsNotNull(Integer age);

    //Like 同 where name like ?1
    List<Person> findByNameLike(String name);

    //NotLike 同 where name not like ?1
    List<Person> findByNameNotLike(String name);


    //StartingWith 同 where name not like ?1(前面加上%)
    List<Person> findByNameStartingWith(String name);

    //EndingWith 同 where name not like ?1(后面加上%)
    List<Person> findByNameEndingWith(String name);

    //Containing 同 where name not like ?1(前后面加上%)
    List<Person> findByNameContaining(String name);


    //OrderBy 同 where name =?1 order by age desc
    List<Person> findByNameOrderByAgeDesc(String name);

    //Not 同 where name <>?1
    List<Person> findByNameNot(String name);

    //In 同 where age in ?1
    List<Person> findByAgeIn(Collection<Age> ages);

    //NotIn 同 where age in ?1
    List<Person> findByAgeNotIn(Collection<Age> ages);


    //前10条数据
    List<Person> findFirst10ByName(String name);


    //前10条数据
    List<Person> findTop30ByName(String name);


}
