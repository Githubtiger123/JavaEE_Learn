package com.javaweb.demoenvironmenttest;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/login1")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);

        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        PrintWriter out = resp.getWriter();
        //设置类型（默认为html）
        resp.setHeader("Content_Type", "text/html");
        //特有设置设置成别的会按别的解析
        resp.setContentType("text/css");


        if (username.equals("admin") && userPwd.equals("123456")) {
            out.println("<h1>OK</h1>");
        } else {
            out.println("<h1>ERROR</h1>");
        }


    }
}