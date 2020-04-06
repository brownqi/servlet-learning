package cn.brownqi.dao;

import cn.brownqi.model.Good;
import cn.brownqi.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    void saveOrder(Order order);

    Map<Order, Good> queryOrdersByID(Integer userId) throws Exception;

}
