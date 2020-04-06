package cn.brownqi.controller.order;

import cn.brownqi.exception.LoginException;
import cn.brownqi.exception.NoLoginException;
import cn.brownqi.model.Order;
import cn.brownqi.model.User;
import cn.brownqi.rest.Result;
import cn.brownqi.service.OrderService;
import cn.brownqi.service.impl.OrderServiceImpl;
import cn.brownqi.utils.JSONUtil;
import cn.brownqi.utils.ParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/orderServlet/addOrder")
public class AddOrderServlet extends HttpServlet {

    private OrderService orderService = new OrderServiceImpl();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        try{
            Order order = ParameterUtils.newModel(req, Order.class);
            HttpSession session = req.getSession();
            User user = (User) Optional.ofNullable(session.getAttribute("user")).orElseThrow(NoLoginException::new);
            order.setUserId(user.getUserId());
            orderService.addOrder(order);
            result = Result.OK(2000,"成功",order);
        }catch (NoLoginException e){
            e.printStackTrace();
            result = Result.ERROR(4001,e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            result = Result.ERROR(4000,e.getMessage());
        }finally {
            JSONUtil.writeJSON(resp,result);
        }
    }
}
