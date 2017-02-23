package com.ys.service;

import com.ys.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by yushi on 2017/2/23.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    //暴露成REST资源 localhost:8080/persons/search/nameStartsWith?name=喻适
    @RestResource(path = "/nameStartsWith", rel = "/nameStartsWith")
    List<Person> findByNameStartsWith(@Param("name") String name);


}
