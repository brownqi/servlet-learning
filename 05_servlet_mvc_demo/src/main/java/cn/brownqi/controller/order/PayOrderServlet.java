package cn.brownqi.controller.order;

import cn.brownqi.model.Order;
import cn.brownqi.service.OrderService;
import cn.brownqi.service.impl.OrderServiceImpl;
import cn.brownqi.utils.ParameterUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orderServlet/pay/*")
public class PayOrderServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = ParameterUtils.newModel(req,Order.class);
        try {
            orderService.payOrder(order.getOrderId());
            resp.sendRedirect("/orderServlet/queryOrders");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
