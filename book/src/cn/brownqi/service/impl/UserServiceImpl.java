package cn.brownqi.service.impl;

import cn.brownqi.dao.UserDao;
import cn.brownqi.dao.impl.UserDaoImpl;
import cn.brownqi.pojo.User;
import cn.brownqi.service.UserService;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 09:47
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null){
            return false;
        }else{
            return true;
        }
    }
}
