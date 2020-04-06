package cn.brownqi.controller.order;

import cn.brownqi.exception.NoLoginException;
import cn.brownqi.model.Good;
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
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/orderServlet/queryOrders")
public class QueryOrdersServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Order, Good> ordersMap = null;
        Result result = null;
        try{
            User user = (User) Optional.ofNullable(req.getSession().getAttribute("user")).orElseThrow(NoLoginException::new);
            ordersMap = orderService.queryOrders(user.getUserId());
            result = Result.OK(2000,"成功",ordersMap);
        }catch (NoLoginException e){
            e.printStackTrace();
            result = Result.ERROR(4001,e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            result = Result.ERROR(4000,e.getMessage());
        }finally {
            JSONUtil.writeJSON(resp,result);
        }
    }
}
