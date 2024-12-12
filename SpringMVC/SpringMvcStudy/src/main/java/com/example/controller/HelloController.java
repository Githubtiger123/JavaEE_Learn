package com.example.controller;

import com.example.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/")
    public String Hello() {
        return "index";
    }

    //通过文件名称访问
//    @RequestMapping("/")
//    public String Hello() {
//        return "index";
//    }

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }


    @RequestMapping("/index1")
    public String index1(Model model) {
        model.addAttribute("msg", "Hello World");
        return "index1";
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
//    @PostMapping() 直接指定method的注解
    public ModelAndView index2() {
        return new ModelAndView("index2");
    }

    @RequestMapping(value = "/index3", method = RequestMethod.GET, params = {"username", "passwd"})
//    http://localhost:8080/mvc/index3?username=123&passwd=12345 只能添加必要参数,
//    加感叹号为不允许携带 params = {"!username", "!passwd"}
    public ModelAndView indexParams() {
        return new ModelAndView("index2");
    }

    //获取请求中的参数 RequestParam注解将请求中的参数映射到实参中
    //http://localhost:8080/mvc/index4?username=124&passwd=admin
    @ResponseBody
    @GetMapping("/index4")
    public String index4(@RequestParam("username") String username, @RequestParam("passwd") String passwd) {
        System.out.println("username:" + username + "passed:" + passwd);

        return "index4";
    }

    //获取请求中的参数 RequestParam注解将请求中的参数映射到实参中
    //如果请求的名称和形参名称相同可以省略 RequestParam注解
    @GetMapping("/index5")
    @ResponseBody
    public String index5(String username, String passwd) {
        System.out.println("username:" + username + "passwd:" + passwd);

        return "index5";
    }

    //required设置非必要参数  defaultValue指定默认参数
    @GetMapping("/index6")
    @ResponseBody
    public String index6(@RequestParam(required = false) String username, @RequestParam(defaultValue = "admin") String passwd) {
        System.out.println("username:" + username + "passwd:" + passwd);

        return "index6";
    }

    //使用servlet原本的一些api
    @GetMapping("/index7")
    @ResponseBody
    public String index7(HttpServletRequest request) {
        System.out.println("接受到请求参数：" + request.getParameterMap().keySet());
        return "index7";
    }

    //直接映射成一个对象
    //http://localhost:8080/mvc/index8?username=admin&passwd=123456
    //User(username=admin, passwd=123456)
    @GetMapping("/index8")
    @ResponseBody
    public String index8(User user) {
        System.out.println(user);
        return "index8";
    }

    //获取请求头中参数
    @GetMapping("/index9")
    @ResponseBody
    public String index9(@RequestHeader("Connection") String connection) {
        System.out.println(connection);
        return "index9";
    }


}
