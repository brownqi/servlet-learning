package cn.brownqi.service.impl;

import cn.brownqi.dao.UserDao;
import cn.brownqi.dao.impl.UserDaoImpl;
import cn.brownqi.model.User;
import cn.brownqi.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public int addUser(User user) {
        synchronized (user.getuName().intern()) { //str.intern() 会先判断 str 在字符串常量池中是否存在，若不存在则 在常量池中添加 str 返回该常量池中str的地址
            return userDao.saveUser(user);
        }
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getuName(), user.getuPwd());
    }

    @Override
    public boolean existsUsername(String uName) {
        if (userDao.queryUserByUsername(uName) == null) {
            return false;
        } else {
            return true;
        }
    }
}
