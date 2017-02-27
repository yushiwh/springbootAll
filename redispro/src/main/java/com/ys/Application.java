package com.ys;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }


    /**
     * springboot提供自动配置RedisTemplate，而RedisTemplate使用的是JdkSerializationRedisSerializer
     * JdkSerializationRedisSerializer采用的是二进制形式存储数据，不是很直观
     * 配置成RedisTemplate并且定义Serializer
     *
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();

        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);


        template.setValueSerializer(jackson2JsonRedisSerializer);//设置值采用jackson2JsonRedisSerializer

        template.setKeySerializer(new StringRedisSerializer());//设置key采用StringRedisSerializer

        template.afterPropertiesSet();
        return template;

    }
}
