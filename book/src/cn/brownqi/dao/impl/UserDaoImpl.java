package cn.brownqi.dao.impl;

import cn.brownqi.dao.UserDao;
import cn.brownqi.pojo.User;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 00:29
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) VALUES(?,?,?);";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }


}
