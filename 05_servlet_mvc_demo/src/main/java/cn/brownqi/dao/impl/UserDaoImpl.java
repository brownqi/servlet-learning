package cn.brownqi.dao.impl;

import cn.brownqi.dao.BaseDao;
import cn.brownqi.dao.UserDao;
import cn.brownqi.model.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String uName) {
        String sql = "select `userid`,`uname`,`upwd`,`ubirth` from t_user where uname = ?";
        return queryForOne(User.class, sql, uName);
    }

    @Override
    public User queryUserByUsernameAndPassword(String uName, String uPwd) {
        String sql = "select `userid`,`uname`,`upwd`,`ubirth` from t_user where uname = ? and upwd = ?";
        return queryForOne(User.class, sql, uName, uPwd);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`uname`,`upwd`,`ubirth`) values(?,?,?)";
        return update(sql,user.getuName(),user.getuPwd(),user.getuBirth());
    }
}
