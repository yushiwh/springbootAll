package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;


    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }


    ///下面是注解的方法实现缓存
    @RequestMapping(value = "/api/city/{id}/zj", method = RequestMethod.GET)
    public City findOneCityzj(@PathVariable("id") Long id) {
        return cityService.findCityByIdZj(id);
    }


    ///下面是注解的方法实现缓存,看看不同的过期的时间的设置
    @RequestMapping(value = "/api/city/{id}/zj1", method = RequestMethod.GET)
    public City findOneCityzjOther(@PathVariable("id") Long id) {
        return cityService.findCityByIdZjOther(id);
    }


}
