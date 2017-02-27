package com.ys.service;

import com.ys.bean.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by yushi on 2017/2/27.
 */
public interface Repository extends MongoRepository<Person, String> {


    List<Person>  findByName(String name);//支持方法名查询

    @Query("{'age':?0}")
//支持@Query查询，查询的参数构造JSON字符串即可
    List<Person> withQueryFindByAge(Integer age);

}
