package com.example.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity //开启security功能
@EnableMethodSecurity // 开启注解权限控制
public class SecurityConfiguration {


    //基于内存验证
//    @Bean   //UserDetailsService就是获取用户信息的服务
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        //每一个UserDetails就代表一个用户信息，其中包含用户的用户名和密码以及角色
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")  //角色目前我们不需要关心，随便写就行，后面会专门讲解
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN", "USER")
//                .build();
//        System.out.println(passwordEncoder.encode("password"));
//        return new InMemoryUserDetailsManager(user, admin);
//        //创建一个基于内存的用户信息管理器作为UserDetailsService
//    }


    //加密密码
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ,PersistentTokenRepository repository) throws Exception {
        return http
                //以下是验证请求拦截和放行配置
                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/static/**").permitAll();
//                    auth.requestMatchers("/").hasAnyRole("user", "admin");
//                    auth.anyRequest().hasRole("admin");   //将所有请求全部拦截，一律需要验证
                    auth.requestMatchers("/static/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                //以下是表单登录相关配置
                .formLogin(conf -> {
                    conf.loginPage("/login");   //将登录页设置为我们自己的登录页面
                    conf.loginProcessingUrl("/doLogin"); //登录表单提交的地址，可以自定义
                    conf.defaultSuccessUrl("/");   //登录成功后跳转的页面
                    conf.permitAll();    //将登录相关的地址放行，否则未登录的用户连登录界面都进不去
                    //用户名和密码的表单字段名称，不过默认就是这个，可以不配置，除非有特殊需求
                    conf.usernameParameter("username");
                    conf.passwordParameter("password");
                })
                //以下是退出登录相关配置
                .logout(conf -> {
                    conf.logoutUrl("/doLogout");   //退出登录地址，跟上面一样可自定义
                    conf.logoutSuccessUrl("/login");  //退出登录成功后跳转的地址，这里设置为登录界面
                    conf.permitAll();
                }).csrf(conf -> {
                    conf.disable();
                    // 忽略其他路径的 CSRF 校验
//                    conf.ignoringRequestMatchers("/**");
//                    // 保留对 /index.html 和 /login.html 的 CSRF 校验
//                    conf.requireCsrfProtectionMatcher(new RequestMatcher() {
//                        @Override
//                        public boolean matches(HttpServletRequest request) {
//                            String requestURI = request.getRequestURI();
//                            return "/index".equals(requestURI) || "/login".equals(requestURI);
//                        }
//                    });
                }).rememberMe(conf -> {
//                    conf.alwaysRemember(false);  //这里不要开启始终记住，我们需要配置为用户自行勾选
//                    conf.rememberMeParameter("remember-me");   //记住我表单字段，默认就是这个，可以不配置
////                    conf.rememberMeCookieName("xxxx");  //记住我设置的Cookie名字，也可以自定义，不过没必要

                    //配置数据库
                    conf.tokenRepository(repository);
                    conf.tokenValiditySeconds(60 * 60 * 24 * 2);  // 设置记住时间为 2 天
                })

                .build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setCreateTableOnStartup(true); //在初始是创建存储cokie的数据库表,创建成功后要注释
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
