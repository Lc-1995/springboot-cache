package com.sakura.springbootcache01.cacheConfig;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: Sakura
 * @Description: 自定义缓存主键生成
 * @Date: 2018/11/22 10:46
 */
@Configuration
public class MykeyGenerator {

    @Bean("myGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName() + "[" + Arrays.asList(params).toString() + "]";
            }
        };
    }
}
