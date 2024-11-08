package com.javaweb.demoenvironmenttest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 重定向是通过HttpServletResponse对象实现
 * 响应重定向是在服务端提示下的，客户端的行为
 * 客户端的地址栏是变化的
 * 客户端至少发送两次请求
 * 客户端产生多次请求
 * 请求产生了多次 后端就会有多个request对象此时请求中的参数不能继续自动传递
 * 自标资源资源可以视图资源
 * 自标资源不可以是WEB-INF下的资源
 * 目标资源可以是外部资源
 *
 * 重点：
 * 同样能够实现页面跳转，优先使用响应重定向，重定向无法实现（传递参数，访问inf）就是用请求转发
 */
@WebServlet("/RedirectA")
public class RedirectA extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("RedirectA");
        //响应重定向B
//        resp.sendRedirect("RedirectB");

        //重定向INF的静态自愿
//        resp.sendRedirect("WEB-INF/a.html"); //失败

        //重定向到其他链接
        resp.sendRedirect("https://chat.deepseek.com/"); //成功
    }
}