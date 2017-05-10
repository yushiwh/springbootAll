package org.spring.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.CityDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    public City findCityById(Long id) {
        // 从缓存中获取城市信息
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        // 从 DB 中获取城市信息
        City city = cityDao.findById(id);

        // 插入缓存
        operations.set(key, city, 10, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());

        return city;
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    /**
     * 更新城市逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     */
    @Override
    public Long updateCity(City city) {
        Long ret = cityDao.updateCity(city);

        // 缓存存在，删除缓存
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }

        return ret;
    }

    @Override
    public Long deleteCity(Long id) {

        Long ret = cityDao.deleteCity(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        return ret;
    }

    /**
     * 注解的方法实现redis的缓存
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "citycache", keyGenerator = "yushiKeyGenerator")
    // @Cacheable(key = "#p0.toString()",value = "citycache" )
    public City findCityByIdZj(Long id) {
        LOGGER.info("无缓存的时候调用这里");
        // 从 DB 中获取城市信息
        City city = cityDao.findById(id);
        return city;
    }

    /**
     * 不同的value得到的不同的过期时间
     *
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "othercitycache", keyGenerator = "yushiKeyGenerator")
    public City findCityByIdZjOther(Long id) {
        LOGGER.info("无缓存的时候调用这里");
        // 从 DB 中获取城市信息
        City city = cityDao.findById(id);
        return city;
    }


    @CachePut(value = "othercitycache", keyGenerator = "yushiKeyGenerator")// 更新othercitycache 缓存
    @Override
    public Long updateCityZj(City city) {
        Long ret = cityDao.updateCity(city);
        LOGGER.info("更新缓存");
        return ret;
    }

    @CacheEvict(value = "othercitycache", keyGenerator = "yushiKeyGenerator")//清空 othercitycache 缓存
    @Override
    public Long deleteCityZj(Long id) {
        Long ret = cityDao.deleteCity(id);
        LOGGER.info("清除缓存" + id);
        return ret;
    }
}
