package cn.brownqi.service.impl;

import cn.brownqi.dao.OrderDao;
import cn.brownqi.dao.impl.OrderDaoImpl;
import cn.brownqi.model.Order;
import cn.brownqi.service.OrderService;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public synchronized void addOrder(Order order) throws Exception {
        order.setOrderState(0);

        String key = System.currentTimeMillis() + "-" + order.getUserId() + "-" + order.getGoodId();
        order.setOrderId(key);

        orderDao.saveOrder(order);
    }
}
