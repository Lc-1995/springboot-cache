package com.sakura.springbootcache01.dao;

import com.sakura.springbootcache01.entity.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/21 17:19
 */
@Mapper
@Component(value = "employeeMapper")
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName}, email=#{email}, gender=#{gender}, d_id=#{dId} where id = #{id}")
    void updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    void deleteEmp(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    void insertEmp(Employee employee);
}
