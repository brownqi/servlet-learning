package cn.brownqi.test;

import cn.brownqi.dao.UserDao;
import cn.brownqi.dao.impl.UserDaoImpl;
import cn.brownqi.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 00:38
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
//        System.out.println(userDao.queryUserByUsername("admin"));
//        System.out.println(userDao.queryUserByUsername("brownqi"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin1", "admin") == null) {
            System.out.println("用户名或密码错误，登陆失败");
        } else {
            System.out.println("查询成功");
        }

    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "brownqi", "123", "123@xx.com")));
    }
}