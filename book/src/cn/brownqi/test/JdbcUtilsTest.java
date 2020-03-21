package cn.brownqi.test;

import cn.brownqi.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 22:51
 */
public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }
    }
}
