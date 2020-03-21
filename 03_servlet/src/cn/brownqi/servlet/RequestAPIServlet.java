package cn.brownqi.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 10:33
 */
public class RequestAPIServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            | getRequestURI()         | 获取请求的资源路径                   |
        System.out.println("URI => "+ req.getRequestURI());

//            | getRequestURL()         | 获取请求的统一资源定位符（绝对路径） |
        System.out.println("URL => "+ req.getRequestURL());

//            | getRemoteHost()         | 获取客户端的 ip 地址                 |
        /**
         *
         */
        System.out.println("客户端 ip 地址 => "+req.getRemoteHost());

//            | getHeader()             | 获取请求头                           |
        System.out.println("请求头User-Agent => "+req.getHeader("User-Agent"));

//            | getParameter()          | 获取请求的参数                       |
//            | getParameterValues()    | 获取请求的参数（多个值的时候使用）   |
//            | getMethod()             | 获取请求的方式 GET 或 POST           |
        System.out.println("请求的方式 => " + req.getMethod());

//            | setAttribute(key,value) | 设置域数据                           |
//            | getAttribute(key)       | 获取域数据                           |
//            | getRequestDispatcher()  | 获取请求转发对象                     |
    }
}
