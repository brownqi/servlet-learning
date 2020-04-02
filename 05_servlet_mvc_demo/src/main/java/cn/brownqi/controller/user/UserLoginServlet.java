package cn.brownqi.controller.user;

import cn.brownqi.model.User;
import cn.brownqi.service.UserService;
import cn.brownqi.service.impl.UserServiceImpl;
import cn.brownqi.utils.ParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ParameterUtils.newModel(req, User.class);
        UserService userService = new UserServiceImpl();
        if (userService.login(user) != null) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登陆失败");
        }
    }
}
