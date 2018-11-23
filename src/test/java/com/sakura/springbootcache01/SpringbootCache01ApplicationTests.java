package com.sakura.springbootcache01;

import com.sakura.springbootcache01.dao.EmployeeMapper;
import com.sakura.springbootcache01.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCache01ApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 操作字符串

    @Autowired
    private RedisTemplate redisTemplate;// 操作对象

    @Autowired
    private RedisTemplate<Object,Employee> empRedisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testInsertEmp() {
        Employee employee = new Employee(1,"妮蔻","1231332@lol.com",0,1);
        employeeMapper.insertEmp(employee);
    }

    @Test
    public void testStringRedis() {
        /*stringRedisTemplate.opsForValue().append("test","12312");*/
        String test = stringRedisTemplate.opsForValue().get("test");
        System.out.println("返回的数据:" + test);
    }

    @Test
    public void testMyRedis() {
        Employee emp = employeeMapper.getEmpById(1);
        empRedisTemplate.opsForValue().set("emp",emp);
    }
}
