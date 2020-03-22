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
 * @date: 2020-03-22 14:04
 */
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code"); //验证码写死abcde

//        2. 检查验证码是否正确
        if ("abcde".equalsIgnoreCase(code)) {
//                - 正确
//                    - 检查用户名是否可用
            if (userService.existsUsername(username)) {
//                        - 不可用：跳回注册页面
                System.out.println("用户名"+username+"已存在！");
                request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);

            } else {
//                        - 可用：调用Service保存到数据库，跳转注册成功页面
                userService.registUser(new User(null,username,password,email));
                request.getRequestDispatcher("/pages/user/regist_success.html").forward(request,response);
            }
        } else {
//                - 不正确
//                    - 返回注册页面
            System.out.println("验证码" + code + "错误");
            request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
