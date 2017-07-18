package org.spring.springboot.service;

import org.spring.springboot.domain.City;

import java.util.List;

/**
 * 城市业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface CityService {
    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    City findCityById(Long id);

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    Long updateCity(City city);

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    Long deleteCity(Long id);


    /**
     * 注解的方法
     *
     * @param id
     * @return
     */
    City findCityByIdZj(Long id);


    City findCityByIdZjOther(Long id);

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    Long updateCityZj(City city);

    Long deleteCityZj(Long id);
}