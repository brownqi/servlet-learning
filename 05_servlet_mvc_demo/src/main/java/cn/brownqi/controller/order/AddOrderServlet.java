package cn.brownqi.controller.order;

import cn.brownqi.exception.NoLoginException;
import cn.brownqi.model.Order;
import cn.brownqi.model.User;
import cn.brownqi.service.OrderService;
import cn.brownqi.service.impl.OrderServiceImpl;
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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Order order = ParameterUtils.newModel(req, Order.class);
            HttpSession session = req.getSession();
            User user = (User) Optional.ofNullable(session.getAttribute("user")).orElseThrow(NoLoginException::new);
            order.setUserId(user.getUserId());
            orderService.addOrder(order);

            req.getRequestDispatcher("/goodOrder.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("/userLogin.jsp");
        }
    }
}
