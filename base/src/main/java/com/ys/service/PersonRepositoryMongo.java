package com.ys.service;

import com.ys.bean.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * MongoDb的查询的接口
 * Created by yushi on 2017/2/17.
 */
public interface PersonRepositoryMongo extends MongoRepository<Person, String> {
    Person findByName(String name);//支持方法查询

    @Query("{'age':?0}")
//支持Query查询，查询参数构造JSON字符串即可
    List<Person> withQueryFindByAge(Integer age);


}
