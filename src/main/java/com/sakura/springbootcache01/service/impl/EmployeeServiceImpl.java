package com.sakura.springbootcache01.service.impl;

import com.sakura.springbootcache01.dao.EmployeeMapper;
import com.sakura.springbootcache01.entity.Employee;
import com.sakura.springbootcache01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/21 17:42
 */
@Service
@CacheConfig(cacheNames = "{emp}", cacheManager = "empCacheManager") // 缓存的公共配置
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * @auther: Sakura
     *  默认的key就是参数
     *  key = "#root.methodName + '[' + #id + ']' "
     *  keyGenerator = "mykeyGenerator"
     *
     */
    @Override
    @Cacheable(/*cacheNames = "{emp}", key = "#root.methodName + '[' + #id + ']' ", condition = "#id > 1"*/)
    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }

    @Override
    @CachePut(key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        try {
            employeeMapper.updateEmp(employee);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteEmp(Integer id) {
        employeeMapper.deleteEmp(id);
    }

    @Override
    public void insertEmp(Employee employee) {
        try {
            employeeMapper.insertEmp(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
