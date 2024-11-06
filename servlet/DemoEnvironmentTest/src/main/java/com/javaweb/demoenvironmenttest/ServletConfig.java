package com.javaweb.demoenvironmenttest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 对servlet的config设置
 * 也可以在注解中配置，详见注解开发
 *
 */
@WebServlet(value = "/configTest", initParams = {
        @WebInitParam(name = "KeyA", value = "KeyB"),
        @WebInitParam(name = "KeyB", value = "KeyC"),
        @WebInitParam(name = "KeyD", value = "KeyE"),
        @WebInitParam(name = "KeyF", value = "KeyG"),

})
public class ServletConfig extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //读取config属性（通过key读取value）
        jakarta.servlet.ServletConfig servletConfig = getServletConfig();
        String value = servletConfig.getInitParameter("key");
        System.out.println("key: key, value:" + value);

        System.out.println("____________________________");
        //读取全部config
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();//返回把迭代器
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            System.out.println(s + ": " + servletConfig.getInitParameter(s));
        }

    }
}
