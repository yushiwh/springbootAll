package com.ys.controller;

import com.ys.bean.Location;
import com.ys.bean.PersonMongo;
import com.ys.service.PersonRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by yushi on 2017/2/17.
 */
@RestController
public class MongoDbController {
    @Autowired
    PersonRepositoryMongo personRepositoryMongo;

    //测试保存数据
    @RequestMapping("/mongosave")
    public PersonMongo save(){
        PersonMongo personMongo = new PersonMongo("ys",22);

        Collection<Location> locations = new LinkedHashSet<Location>();

        Location location1 = new Location("上海","2009");

        return personMongo;

    }

}
