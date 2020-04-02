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

@WebServlet("/addUser")
public class UserRegistServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ParameterUtils.newModel(req,User.class);
        UserService userService = new UserServiceImpl();
        if (userService.existsUsername(user.getuName())){
            System.err.println("用户名已存在");
            req.setAttribute("errorMessage","用户名已存在");
        }else{
            userService.addUser(user);
            req.getRequestDispatcher("/UserLogin.jsp").forward(req,resp);
        }
    }
}
