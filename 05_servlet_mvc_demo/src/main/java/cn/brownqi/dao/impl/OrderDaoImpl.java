package cn.brownqi.dao.impl;

import cn.brownqi.dao.BaseDao;
import cn.brownqi.dao.OrderDao;
import cn.brownqi.model.Good;
import cn.brownqi.model.Order;
import cn.brownqi.utils.JdbcUtils;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into t_order values(?,?,?,?,?)";
        update(sql,order.getOrderId(),order.getUserId(),order.getGoodId(),order.getOrderCount(),order.getOrderState());
    }

    @Override
    public Map<Order, Good> queryOrdersByID(Integer userId) throws Exception {
        String sql = "SELECT * FROM t_order o LEFT JOIN t_good g ON o.`goodid` = g.`goodid` WHERE o.`userid` = ?";
        Map<Order, Good> orderGoodMap = queryOrderGoodMap(sql, userId);
        return orderGoodMap;
    }

    @Override
    public void setOrderState(String orderId) throws Exception {
        String sql = "UPDATE t_order set orderstate = 1 where orderid = ?";
        update(sql,orderId);
    }

    /**
     * t_order 表 与 t_good 表 多表查询
     * @param sql
     * @param args
     * @return
     */
    private static Map<Order, Good> queryOrderGoodMap(String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();
        Map<Order,Good> map = new HashMap<>();
        try {
            ResultSetHandler<Map<Order,Good>> myHandler = new ResultSetHandler<Map<Order,Good>>() {
                @Override
                public Map<Order,Good> handle(ResultSet resultSet) throws SQLException {
                    while (resultSet.next()){
                        Order order = new Order(
                                resultSet.getString(1),
                                resultSet.getInt(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getInt(5)
                        );
                        Good good = new Good(
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getBigDecimal(8),
                                resultSet.getInt(9)
                        );
                        map.put(order,good);
                    }
                    return map;
                }
            };
            Map<Order,Good> query = queryRunner.query(connection, sql, myHandler, args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return null;
    }


}
