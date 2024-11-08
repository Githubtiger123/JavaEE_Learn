package com.javaweb.demoenvironmenttest;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 测试servletContest
 * 1.只初始化一次全局共用同一个
 * 2.写在<context-param>中
 */

@WebServlet(value = "/context")
public class ContextTest extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取context三种方式
        ServletContext servletContext = getServletContext();
        ServletContext servletContext1 = getServletConfig().getServletContext();
        ServletContext servletContext2 = req.getServletContext();
        System.out.println(servletContext1 == servletContext2);
        System.out.println(servletContext == servletContext2);
        System.out.println("+++++++++++++++++++++++++++");
        //遍历所有context
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            System.out.print(name);
            System.out.println(servletContext.getInitParameter(name));
        }

        System.out.println("_________________________");
        //获取磁盘上文件夹/文件真实路径
        System.out.println(servletContext.getRealPath("static/gift.png"));
        System.out.println(servletContext.getRealPath("aaa/a.txt"));

    }
}
