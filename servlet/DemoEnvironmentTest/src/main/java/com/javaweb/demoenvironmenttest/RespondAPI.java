package com.javaweb.demoenvironmenttest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 设置响应相关的API
 */
@WebServlet("/RespondAPI")
public class RespondAPI extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //响应行相关
        resp.setStatus(200); //指定状态码
        resp.setHeader("aaa", "bbb");
        resp.setHeader("Content-Type", "text/html");
        resp.setContentType("text/html");
        resp.setHeader("Content-Length", "500");
        resp.setContentLength(500);


        //响应体相关
        PrintWriter out = resp.getWriter(); //字符流
        out.write("<h1>你好</h1>");
    }
}
