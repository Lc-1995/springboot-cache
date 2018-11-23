package com.sakura.springbootcache01.controller;

import com.sakura.springbootcache01.entity.Employee;
import com.sakura.springbootcache01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/21 17:43
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id) {
        return employeeService.getEmpById(id);
    }

    @PostMapping("/emp")
    public void insertEmp(Employee employee) {
        employeeService.insertEmp(employee);
    }

    @PutMapping("/emp")
    public void updateEmp(Employee employee) {
        employeeService.updateEmp(employee);
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }
}
