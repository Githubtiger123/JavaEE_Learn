package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    //获取cookie值e
    @GetMapping("/index10")
    @ResponseBody
    public String index10(@CookieValue("JSESSIONID") String cookie) {
        System.out.println(cookie);
        return "index10";
    }

    //获取session
    @GetMapping("/index11")
    @ResponseBody
    public String index11(@SessionAttribute("test") String cookie) {
        System.out.println(cookie);
        return "index11";
    }

    //添加session
    @GetMapping("/save")
    @ResponseBody
    public String save(HttpSession session) {
        session.setAttribute("test", "123432312434");
        return "save";
    }

    //重定向 再return 返回的字符串里添加redirect前缀
    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/index";
    }

    //请求转发 再return 返回的字符串里添加forward前缀
    @GetMapping("/forward")
    public String forward() {
        return "forward:/index";
    }

    //RestFul风格
    //注意请求路径我们可以手动添加类似占位符一样的信息，这样占位符位置的所有内容都会被作为请求参数，而方法的形参列表中必须包括一个与占位符同名的并且添加了@PathVariable注解的参数，或是由@PathVariable注解指定为占位符名称：
    @GetMapping("/indexRestFul/{str}")
    @ResponseBody
    public String indexRestFul(@PathVariable("str") String str) {
        System.out.println(str);
        return "indexRestFul:" + str;
    }


    @GetMapping("/test")
    @ResponseBody
    public String test() {

        System.out.println("test请求执行");
        return "test";
    }


    @GetMapping("/test1")
    @ResponseBody
    public String test1() {

        System.out.println("test1请求执行");
        return "test1";
    }


    @GetMapping("/throwException")
    @ResponseBody
    public String throwException() {
        //触发异常
        if (true) throw new RuntimeException("触发异常");
        return "throwException";
    }


    @GetMapping(name = "/JSONTest", produces = "application/json")
    @ResponseBody
    public String JSONTest() {

        JSONObject object = new JSONObject();
        object.put("username", "admin");
        object.put("password", "12345");
        JSONArray array = new JSONArray();
        array.add(object);
        return array.toJSONString();
    }

    @GetMapping("/ObjecttoJSON")
    @ResponseBody
    public User ObjecttoJSON() {
        //直接返回user对象，直接通过spring返回为json
        User admin = new User("admin", "123456");
        return admin;
    }


}
