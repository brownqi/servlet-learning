package cn.brownqi.dao.impl;

import cn.brownqi.dao.BaseDao;
import cn.brownqi.dao.OrderDao;
import cn.brownqi.model.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into t_order values(?,?,?,?,?)";
        update(sql,order.getOrderId(),order.getUserId(),order.getGoodId(),order.getOrderCount(),order.getOrderState());
    }
}
