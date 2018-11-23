package com.sakura.springbootcache01.cacheConfig;

import com.sakura.springbootcache01.entity.Department;
import com.sakura.springbootcache01.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;


/**
 * @Author: Sakura
 * @Description: 1.使用注入的方式操作Redis：
 *                  默认使用jdk自带的序列化Value的方式,保存位Json数据需要自定义RedisTemplate
 *               2.使用注解方式操作Redis：
 *                  序列化Value是默认使用的jdk提供的,保存为Json数据需要自定义RedisCacheManager
 * @Date: 2018/11/23 10:04
 */
@Configuration
public class MyRedisConfig {

    /**
     * @auther: Sakura
     * @date: 2018/11/23 14:03
     * @Description: empRedisTemplate
     */
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Employee> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    /**
     * @auther: Sakura
     * @date: 2018/11/23 14:05
     * @Description: empCacheManager
     */
    @Primary // 有多个RedisCacheManager需要指定一个默认的
    @Bean
    public RedisCacheManager empCacheManager(RedisConnectionFactory connectionFactory) {
        return getRedisCacheManagerT(connectionFactory, Employee.class);
    }

    /**
     * @auther: Sakura
     * @date: 2018/11/23 14:05
     * @Description: deptCacheManager
     */
    @Bean
    public RedisCacheManager deptCacheManager(RedisConnectionFactory connectionFactory) {
        return getRedisCacheManagerT(connectionFactory, Department.class);
    }

    /**
     *
     * @Description: 抽取的自定义RedisCacheManager公共方法
     * @auther: Sakura
     * @date: 2018/11/23 14:35
     * @param: [connectionFactory,
     *          clazz:传入的实体类]
     */
    private RedisCacheManager getRedisCacheManagerT(RedisConnectionFactory connectionFactory, Class clazz) {
        // 初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        // 使用Jackson序列化
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(clazz);

        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);

        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }

}
