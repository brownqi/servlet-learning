package cn.brownqi.controller.user;


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
import java.io.IOException;

@WebServlet("/userServlet/addUser")
public class UserRegistServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ParameterUtils.newModel(req,User.class);
        UserService userService = new UserServiceImpl();
        Result result = null;

        try{
            user = userService.addUser(user);
            result = Result.OK(2000,"注册成功",user);
        }catch (Exception e){
            result = Result.ERROR(4000,e.getMessage());
        }finally {
            JSONUtil.writeJSON(resp,result);
        }

    }
}
