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
 * @date: 2020-03-15 20:53
 */
public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 获取 web.xml 中配置的上下文参数 context-param
        ServletContext servletContext1 = getServletConfig().getServletContext();
        ServletContext servletContext = getServletContext();
        String username = servletContext.getInitParameter("username");
        String username1 = servletContext1.getInitParameter("username");
        System.out.println(username);
        System.out.println(username1);

//        2. 获取当前的工程路径，格式：/工程路径
        System.out.println("当前工程路径："+ servletContext.getContextPath());

//        3. 获取工程部署后在服务器磁盘上的绝对路径
        /**
         *  / 斜杠被服务器解析地址为：http://ip:port/工程名/
         *  访问 http://ip:port/工程名,通过tomcat找到servlet.xml映射到IDEA代码的web目录
         *
         */
        System.out.println("工程部署的绝对路径"+servletContext.getRealPath("/"));
        System.out.println("工程下css目录的绝对路径"+servletContext.getRealPath("/css"));
        System.out.println("工程下imgs目录下的mario.png的绝对路径"+servletContext.getRealPath("/imgs/mario.png"));

//        4. 像 Map 一样存取数据

    }
}
