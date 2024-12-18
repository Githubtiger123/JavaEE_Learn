package com.manage.mapper;

import com.manage.entity.Account;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where username = #{username}")
    Account findUSerById(String username);
}
