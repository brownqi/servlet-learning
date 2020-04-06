package cn.brownqi.service.impl;

import cn.brownqi.dao.OrderDao;
import cn.brownqi.dao.impl.OrderDaoImpl;
import cn.brownqi.model.Good;
import cn.brownqi.model.Order;
import cn.brownqi.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public synchronized void addOrder(Order order) throws Exception {
        order.setOrderState(0);

        String key = System.currentTimeMillis() + "-" + order.getUserId() + "-" + order.getGoodId();
        order.setOrderId(key);

        orderDao.saveOrder(order);
    }

    @Override
    public Map<Order, Good> queryOrders(Integer userId) throws Exception {
        return orderDao.queryOrdersByID(userId);
    }

    @Override
    public synchronized void payOrder(String orderId) throws Exception {
        orderDao.setOrderState(orderId);
    }

}
