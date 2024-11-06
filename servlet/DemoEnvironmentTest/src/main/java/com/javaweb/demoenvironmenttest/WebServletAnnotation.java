package com.javaweb.demoenvironmenttest;

import jakarta.servlet.annotation.WebServlet;

/**
 * 1.name只是别名，无实际作用
 * 2.value和urlPatterns是互为别名属性
 * 3.value和urlPatterns是一个数组可以填多个值，对应多个url-pattern
 * 4.在注解中如果只写了一个参数且属性名位value则可以省略不写
 * 5.loadOnStartup属性是设置在servlet启动时实例化servlet对象，-1为不初始化，第一次调用时在进行初始化，其余为初始化的顺序
 * 6.initParams配置config键值对
 *
 */
@WebServlet(name = "otherName", urlPatterns = {"/a", "/b", "/c"},loadOnStartup = 6)
public class WebServletAnnotation {


}
