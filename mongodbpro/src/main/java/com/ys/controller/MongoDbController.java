package com.ys.controller;

import com.ys.bean.Location;
import com.ys.bean.Person;
import com.ys.service.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by yushi on 2017/2/5.
 */
@RestController
public class MongoDbController {
    @Autowired
    Repository repository;

    @RequestMapping("/save")
    public Person save() {
        Person p = new Person("ys", 22);
        Collection<Location> locations = new LinkedHashSet<Location>();
        Location location1 = new Location("武汉", "2009");
        Location location2 = new Location("宜昌", "2010");
        Location location3 = new Location("伤害", "2011");
        Location location4 = new Location("北京", "2012");

        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);
        p.setLocations(locations);

        return repository.save(p);

    }


    @RequestMapping("/findByName")
    public List<Person> findByName(String name) {
        return repository.findByName(name);


    }

    @RequestMapping("/withQueryFindByAge")
    public List<Person> withQueryFindByAge(Integer age) {
        return repository.withQueryFindByAge(age);


    }
}
