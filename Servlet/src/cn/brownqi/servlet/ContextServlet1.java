package cn.brownqi.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-15 23:04
 */
public class ContextServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext对象
        ServletContext context = getServletContext();
        System.out.println("保存之前Context1中获取域数据key1的值"+context.getAttribute("key1"));
        context.setAttribute("key1","value1");
        System.out.println("Context1中获取域数据key1的值"+context.getAttribute("key1"));
        System.out.println("Context1中获取域数据key1的值"+context.getAttribute("key1"));
        System.out.println("Context1中获取域数据key1的值"+context.getAttribute("key1"));
        System.out.println("Context1中获取域数据key1的值"+context.getAttribute("key1"));
        System.out.println("Context1中获取域数据key1的值"+context.getAttribute("key1"));
    }
}
