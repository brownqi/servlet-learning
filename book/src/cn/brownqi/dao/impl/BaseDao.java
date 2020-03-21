package cn.brownqi.dao.impl;

import cn.brownqi.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 23:03
 */
public abstract class BaseDao {

    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用来执行：insert、update、delete语句
     * @return 如果返回-1，说明执行失败。返回其他表示影响的行数
     */
    public int update(String sql,Object ... args){

        Connection conn = JdbcUtils.getConnection();
        try {
            queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }



}
