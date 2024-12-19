package com.manage.mapper;

import com.manage.entity.Account;
import com.manage.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where username = #{username}")
    Account findUSerByName(String username);

    @Select("select * from user")
    List<Student> getStudentList();
}
