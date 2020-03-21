package cn.brownqi.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 13:35
 */
public class ParameterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求的字符集未UTF-8,从而解决post请求的中文乱码问题
        request.setCharacterEncoding("UTF-8");
        System.out.println("--------------doPost--------------");
        //获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobby = request.getParameterValues("hobby");

        System.out.println("用户名" + username);
        System.out.println("密码" + password);
        System.out.println("爱好" + Arrays.asList(hobby));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------doGet--------------");
        //获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobby = request.getParameterValues("hobby");

        System.out.println("用户名" + username);
        System.out.println("密码" + password);
        System.out.println("爱好" + Arrays.asList(hobby));

    }
}
