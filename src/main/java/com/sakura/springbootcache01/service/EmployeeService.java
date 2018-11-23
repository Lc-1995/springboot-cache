package com.sakura.springbootcache01.service;

import com.sakura.springbootcache01.entity.Employee;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/21 17:41
 */
public interface EmployeeService {

    Employee getEmpById(Integer id);

    Employee updateEmp(Employee employee);

    void deleteEmp(Integer id);

    void insertEmp(Employee employee);
}
