package com.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public PasswordEncoder passwordEncoder() {
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        return new BCryptPasswordEncoder();
    }


    //记住我数据库存储
    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);  //开始自动创建数据库
        return jdbcTokenRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   PersistentTokenRepository tokenRepository) throws Exception {

        return http.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers("/static/**").permitAll();
                    auth.anyRequest().authenticated();
                }
        ).formLogin(conf->{
            conf.loginPage("/login");
            conf.loginProcessingUrl("/doLogin");
            conf.successForwardUrl("/borrow");
            conf.permitAll();
        }).csrf(AbstractHttpConfigurer::disable).rememberMe(conf->{
            conf.tokenRepository(tokenRepository);
            conf.tokenValiditySeconds(60 * 60 * 6); //记住我时间6小时
        }).build();
    }

}
