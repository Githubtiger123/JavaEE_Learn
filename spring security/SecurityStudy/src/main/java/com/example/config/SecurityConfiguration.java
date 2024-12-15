package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity //开启security功能
public class SecurityConfiguration {


    //基于内存验证
    @Bean   //UserDetailsService就是获取用户信息的服务
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        //每一个UserDetails就代表一个用户信息，其中包含用户的用户名和密码以及角色
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")  //角色目前我们不需要关心，随便写就行，后面会专门讲解
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN", "USER")
                .build();
        System.out.println(passwordEncoder.encode("password"));
        return new InMemoryUserDetailsManager(user, admin);
        //创建一个基于内存的用户信息管理器作为UserDetailsService
    }


    //加密密码
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
