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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/userServlet/checkUserName/*")
public class UserCheckUserName extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uName = req.getParameter("uName");
        boolean flag = userService.existsUsername(uName);
        Map<String ,Boolean> map = new HashMap<>();
        map.put("result",flag);
        Result result = Result.OK(2000,"确认结果",map);
        JSONUtil.writeJSON(resp, result);
    }
}
