package com.sakura.springbootcache01.controller;

import com.sakura.springbootcache01.entity.Department;
import com.sakura.springbootcache01.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/23 11:50
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDeptById(@PathVariable("id") Integer id) {
        return deptService.getDeptById(id);
    }

    @PutMapping("/dept")
    public String updateDeptById(Department department) {
        deptService.updateDept(department);
        return "success";
    }
}
