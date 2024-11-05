package com.javaweb.demoenvironmenttest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 测试url-pattern规则
 * 1.一个<servlet>可以有多个<servlet-mapping>
 * 2.一个<servlet-name>可以有多个<url-pattern>
 * 3.url-pattern可以有多个匹配方式
 * 4.精确匹配/XXXX
 * 5.模糊匹配 * 是模糊匹配通配符
 * 6./* 是匹配/下任意内容
 * 7./XXX* 是匹配/XXX下任意内容
 * 8.匹配后缀注意不能以/开头 ，格式*XXX
 *
 */
public class TestURLPattern extends HttpServlet {

    int i = 0;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("第" + i + "次访问");
    }
}
