package com.javaweb.demoenvironmenttest;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext application = getServletContext();
        application.setAttribute("hello", "world");
        //假设再设置为别的
        application.setAttribute("hello", "world！！！！");
        //假设清除属性
        application.removeAttribute("hello");

    }
}
