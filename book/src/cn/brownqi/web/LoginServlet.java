package cn.brownqi.web;

import cn.brownqi.pojo.User;
import cn.brownqi.service.UserService;
import cn.brownqi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 16:38
 */
public class LoginServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        2. 调用userService.login()登陆
        User loginUser = userService.login(new User(null, username, password, null));
//        3. 根据userService.login()方法返回结果判断登陆是否成功
        if (loginUser == null) {
//                - 失败：跳回登陆页面
            System.out.println("用户名或密码错误");
            request.getRequestDispatcher("pages/user/login.html").forward(request,response);
        } else {
//                - 成功：跳转成功页面
            System.out.println("登陆成功");
            request.getRequestDispatcher("pages/user/login_success.html").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
