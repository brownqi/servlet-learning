package cn.brownqi.service.impl;

import cn.brownqi.dao.UserDao;
import cn.brownqi.dao.impl.UserDaoImpl;
import cn.brownqi.exception.LoginException;
import cn.brownqi.model.User;
import cn.brownqi.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User addUser(User user) throws Exception {
        synchronized (user.getuName().intern()) { //str.intern() 会先判断 str 在字符串常量池中是否存在，若不存在则 在常量池中添加 str 返回该常量池中str的地址
            if (existsUsername(user.getuName())) {
                throw new RuntimeException("用户已存在");
            } else {
                userDao.saveUser(user);
                return user;
            }
        }
    }

    @Override
    public User login(User user) throws Exception {
        Optional<User> userOpt =
                Optional.ofNullable(
                        userDao.queryUserByUsernameAndPassword(user.getuName(), user.getuPwd()));
        return userOpt.orElseThrow(LoginException::new);
    }

    @Override
    public boolean existsUsername(String uName) {
        return userDao.queryUserByUsername(uName) != null;
    }
}
