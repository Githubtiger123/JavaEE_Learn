package com.learn.mapper;

import com.learn.model.department;
import com.learn.model.employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

//设置某个类的二级缓存
//@CacheNamespace(readWrite = false)
@CacheNamespace()
public interface employeeAnnotationMapper {


    @Insert("insert into employees(employee_id,last_name,email,department_id,salary)" + "values (#{employeeId},#{lastName},#{email},#{departmentId},#{salary})")
    int addEmployee(employee employee);


    @Results({@Result(id = true, column = "employee_id", property = "employeeId"), @Result(column = "last_name", property = "lastName"), @Result(column = "email", property = "email"), @Result(column = "department_id", property = "departmentId"), @Result(column = "salary", property = "salary")})
    @Select("select * from employees")
    List<employee> getAllEmployee();

    @Select("select * from employees where employee_id = #{employeeId}")
    employee getEmployeeById(int employeeId);

    @Results({@Result(id = true, column = "employee_id", property = "employeeId"), @Result(column = "last_name", property = "lastName"), @Result(column = "email", property = "email"), @Result(column = "department_id", property = "departmentId"), @Result(column = "salary", property = "salary"), @Result(column = "department_id", property = "department", one = @One(select = "getDepartmentById"))})
    @Select("select * from employees where employee_id = #{eid}")
    employee getEmployeeByAndDept(int eid);

    @Results({@Result(id = true, column = "department_id", property = "department_id"), @Result(column = "department_name", property = "department_name"), @Result(column = "manager_id", property = "manager_id"),

    })
    @Select("select * from departments where department_id = #{departmentId}")
    department getDepartmentById(int departmentId);

    @ResultMap("OnlyDeptMap")
    @Select("select * from departments where department_id = #{departmentId}")
    department getDepartmentById2(int departmentId);

    @ConstructorArgs({@Arg(column = "employee_id", javaType = Integer.class), @Arg(column = "last_name", javaType = String.class), @Arg(column = "email", javaType = String.class)})
    @Select("select * from employees where employee_id = #{employeeId} and last_name = #{lastName}")
    employee getEmployeeConstruct(@Param("employeeId") int employeeId, @Param("lastName") String lastName);

    @Insert("insert into employees values (#{employeeId},#{employee.lastName},#{employee.email},#{employee.departmentId},#{employee.salary})")
    int addEmployeeConstruct(@Param("employeeId") int employeeId, @Param("employee") employee employee);

    //单独设置每个方法的二级缓存
    @Options(useCache = false, flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from employees where employee_id = #{employeeId}")
    int testCache(int employeeId);
}
