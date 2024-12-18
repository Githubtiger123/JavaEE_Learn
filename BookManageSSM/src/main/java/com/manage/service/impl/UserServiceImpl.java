package com.manage.service.impl;

import com.manage.entity.Account;
import com.manage.mapper.UserMapper;
import com.manage.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    //用户登录
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userMapper.findUSerById(username);
        if(account == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
}
