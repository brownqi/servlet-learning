package cn.brownqi.service;

import cn.brownqi.model.Good;
import cn.brownqi.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void addOrder(Order order) throws Exception;

    Map<Order, Good> queryOrders(Integer userId) throws Exception;

    void payOrder(String orderId) throws Exception;
}
