package com.javaweb.demoenvironmenttest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * 测试请求相关的API
 */
@WebServlet("/RequestAPI")
public class RequestAPI extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //行相关
        System.out.println(req.getMethod()); //获取请求方法
        System.out.println(req.getScheme()); //获取请求协议
        System.out.println(req.getProtocol());//协议版本
        System.out.println(req.getRequestURI());
        System.out.println(req.getRequestURL());
        System.out.println(req.getLocalPort());  //返回服务器上接收请求的端口号。
        System.out.println(req.getServerPort()); //返回请求中指定的服务器端口号。
        System.out.println(req.getRemotePort()); //返回发送请求的客户端的端口号。

        //头相关
        System.out.println("____________头相关_____________");
        //根据名字获取某个请求头
        System.out.println("**Accept:" + req.getHeader("accept"));

        //获取请求头所有名字，根据名子打印所有属性
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String parameterName = headerNames.nextElement();
            System.out.println(parameterName + ":" + req.getHeader(parameterName));
        }

        //体相关
        System.out.println("____________体相关_____________");
        //根据名字获取单个属性值
        System.out.println("username:" + req.getParameter("username"));
        //获取单个属性值（数组）
        String[] hobbies = req.getParameterValues("hobby");
        System.out.println(Arrays.toString(hobbies));
        //获取所有属性值并打印
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = req.getParameterValues(parameterName);
            if (parameterValues.length == 1) {
                System.out.println(parameterName + ":" + parameterValues[0]);
            } else {
                System.out.println(parameterName + ":" + Arrays.toString(parameterValues));
            }
        }
        //返沪所有参数的map集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((k, v) -> {
            if (v.length == 1) {
                System.out.println(k + ":" + v[0]);
            } else {
                System.out.println(k + ":" + Arrays.toString(v));
            }
        });

    }
}
