package com.javaweb.demoenvironmenttest;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//请求转发是通过 `HttpServletRequest` 对象实现的
//请求转发是服务器内部行为，对客户端是屏蔽的
//客户端只产生了一次请求
//服务端只产生了一对 `request` 和 `response` 对象
//客户端的地址栏是不变的
//请求的参数是可以继续传递的
//目标资源可以是 Servlet 动态资源也可以是 HTML 静态资源
//目标资源可以是 WEB-INF 下的受保护的资源
//该方式也是 WEB-INF 下的资源的唯一访问方式
//目标资源不可以是外部的资源
@WebServlet("/ForwardA")
public class ForwardA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("访问ForwardA");
        //进行请求转发
//        RequestDispatcher forwardB = req.getRequestDispatcher("ForwardB");
//        forwardB.forward(req, resp);
        //转发都保护的inf文件夹
        RequestDispatcher inf = req.getRequestDispatcher("WEB-INF/a.html");
        inf.forward(req, resp);
    }
}
