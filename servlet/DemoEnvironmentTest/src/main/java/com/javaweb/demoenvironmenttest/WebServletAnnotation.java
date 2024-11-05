package com.javaweb.demoenvironmenttest;

import jakarta.servlet.annotation.WebServlet;

/**
 * 1.name只是别名，无实际作用
 * 2.value和urlPatterns是互为别名属性
 * 3.value和urlPatterns是一个数组可以填多个值，对应多个url-pattern
 * 4.在注解中如果只写了一个参数且属性名位value则可以省略不写
 *
 */
@WebServlet(name = "otherName", urlPatterns = {"a", "b", "c"})
public class WebServletAnnotation {


}
