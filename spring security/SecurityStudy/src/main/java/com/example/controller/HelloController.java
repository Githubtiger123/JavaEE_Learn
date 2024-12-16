package com.example.controller;


import com.alibaba.fastjson2.JSONObject;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @PreAuthorize("hasAnyRole('admin','user')") //注解方式限制admin user才可以访问
    @PostAuthorize("hasAnyRole('admin','user')") //注解方式限制admin user才可以访问
    //post在执行此方法之后拦截返回的，pre是先判断，如果不行不会执行，直接拦截
    //可以直接加在bean管理的类上，不同权限的用户访问时会进行判断
    @GetMapping("/")
    public String hello() {

        return "index";
    }

    @ResponseBody
    @PostMapping("/pay")
    public JSONObject pay(@RequestParam String account) {
        JSONObject object = new JSONObject();
        //登录之后才能转账
        System.out.println("转账给" + account + "成功，交易已完成！");
        object.put("success", true);
        return object;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PreAuthorize("hasRole('admin')") //注解方式限制admin才可以访问
    @GetMapping("/chart")
    public String chart() {
        return "chart";
    }


}
