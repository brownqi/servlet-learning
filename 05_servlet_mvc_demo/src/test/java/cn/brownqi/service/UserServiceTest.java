package cn.brownqi.service;

import cn.brownqi.model.User;
import cn.brownqi.service.impl.UserServiceImpl;
import cn.brownqi.utils.DateUtils;
import org.junit.Test;

import java.sql.Date;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        Date date1 = DateUtils.strToDate("2020-1-1");
        Date date2 = DateUtils.strToDate("2020-2-2");
        try {
            userService.addUser(new User(null,"dodo1","123",date1));
            userService.addUser(new User(null,"dodo2","123",date2));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void login() {
        try {
            System.out.println(userService.login(new User(null,"dodo1","123",null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("dodo3")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }

}
