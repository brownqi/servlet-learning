package cn.brownqi.utils;

import org.junit.Test;

public class ConnectionTest {

    @Test
    public void connectionTest(){
        System.out.println(JdbcUtils.getConnection());
        System.out.println(JdbcUtils.getConnection());
        System.out.println(JdbcUtils.getConnection());
    }

}
