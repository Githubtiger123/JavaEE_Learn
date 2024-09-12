package com.learn.mapper;

import com.learn.model.department;
import com.learn.model.employee;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface departmentAnnotationMapper {


    @Results({
            @Result(id = true,column = "department_id",property = "department_id"),
            @Result(column = "department_name",property = "department_name"),
            @Result(column = "manager_id",property = "manager_id"),
            @Result(column = "department_id",property = "employees",many =
            @Many(select = "getEmployees")),
    })
    @Select("select * from departments where department_id = #{departmentId}")
    department getDepartmentById(int departmentId);

    @Select("select * from employees where department_id = #{departmentId}")
    List<employee> getEmployees(int departmentId);
}
