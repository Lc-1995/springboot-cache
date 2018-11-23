package com.sakura.springbootcache01.service.impl;

import com.sakura.springbootcache01.dao.DeptMapper;
import com.sakura.springbootcache01.entity.Department;
import com.sakura.springbootcache01.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/23 11:48
 */
@Service
@CacheConfig(cacheNames = "{dept}", cacheManager = "deptCacheManager") // 自定义多个RedisCacheManager,需要指定使用
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    private RedisCacheManager cacheManager;

    @Override
    @Cacheable
    public Department getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void updateDept(Department department) {
        try {
            deptMapper.updateDept(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 编码的方式使用缓存
        Cache dept = cacheManager.getCache("{dept}");
        dept.put(department.getId(), department);
    }
}
