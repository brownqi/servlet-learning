package cn.brownqi.dao;

import cn.brownqi.pojo.User;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 00:05
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户。
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null，说明用户名或密码错误。
     */
    User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回 -1 表示操作失败，其他是sql语句影响的行数
     */
    int saveUser(User user);



}
