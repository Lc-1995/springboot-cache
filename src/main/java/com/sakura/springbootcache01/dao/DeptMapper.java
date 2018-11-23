package com.sakura.springbootcache01.dao;

import com.sakura.springbootcache01.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @Author: Sakura
 * @Description:
 * @Date: 2018/11/23 11:46
 */
@Mapper
@Component(value = "deptMapper")
public interface DeptMapper {

    @Select("select * from department where id = #{id}")
    Department getDeptById(Integer id);

    @Update("update department set departmentName = #{departmentName} where id = #{id}")
    void updateDept(Department department);
}
