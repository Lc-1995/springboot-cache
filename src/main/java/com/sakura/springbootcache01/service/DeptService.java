package com.sakura.springbootcache01.service;

import com.sakura.springbootcache01.entity.Department;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/23 11:47
 */
public interface DeptService {

    Department getDeptById(Integer id);

    void updateDept(Department department);
}
