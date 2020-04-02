package cn.brownqi.dao;

import cn.brownqi.dao.impl.UserDaoImpl;
import cn.brownqi.model.User;
import cn.brownqi.utils.DateUtils;
import org.junit.Test;

import java.sql.Date;

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
        if (userDao.queryUserByUsernameAndPassword("admin", "123") == null) {
            System.out.println("用户名或密码错误，登陆失败");
        } else {
            System.out.println("查询成功");
        }

    }

    @Test
    public void saveUser() {
        Date date = DateUtils.strToDate("2020-3-3");
        System.out.println(userDao.saveUser(new User(null, "brownqi", "123", date)));
    }

}
