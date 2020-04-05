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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userServlet/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ParameterUtils.newModel(req, User.class);
        UserService userService = new UserServiceImpl();
        try {
            User loginUser = userService.login(user);
            HttpSession session = req.getSession();
            session.setAttribute("user",loginUser);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("login-message",e.getMessage());
            req.getRequestDispatcher("/userLogin.jsp").forward(req,resp);
        }

    }
}
