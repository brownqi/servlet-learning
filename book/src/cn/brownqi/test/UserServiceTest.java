package cn.brownqi.test;

import cn.brownqi.pojo.User;
import cn.brownqi.service.UserService;
import cn.brownqi.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 09:54
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"dodo1","123","dodo@xx.com"));
        userService.registUser(new User(null,"dodo2","123","dodo@xx.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"dodo1","123",null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("dodo1")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}