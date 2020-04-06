package cn.brownqi.controller.user;

import cn.brownqi.exception.LoginException;
import cn.brownqi.model.User;
import cn.brownqi.rest.Result;
import cn.brownqi.service.UserService;
import cn.brownqi.service.impl.UserServiceImpl;
import cn.brownqi.utils.JSONUtil;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ParameterUtils.newModel(req, User.class);
        UserService userService = new UserServiceImpl();
        Result result = null;
        try {
            User loginUser = userService.login(user);
            HttpSession session = req.getSession();
            session.setAttribute("user",loginUser);
            result = Result.OK(2000,"登陆成功",loginUser);
            resp.sendRedirect("/goodServlet/index");
        } catch (LoginException e) {
            e.printStackTrace();
            result = Result.ERROR(4002,e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.ERROR(4000,e.getMessage());
        }finally {
            JSONUtil.writeJSON(resp,result);
        }

    }
}
