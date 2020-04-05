package cn.brownqi.listener;

import cn.brownqi.utils.JdbcUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContentListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化
        JdbcUtils.initDataSource();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
