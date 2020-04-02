package cn.brownqi.dao;

import cn.brownqi.model.User;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param uName 用户名
     * @return 如果返回null，说明没有这个用户。
     */
    User queryUserByUsername(String uName);

    /**
     * 根据用户名和密码查询用户信息
     * @param uName
     * @param uPwd
     * @return 如果返回null，说明用户名或密码错误。
     */
    User queryUserByUsernameAndPassword(String uName,String uPwd);

    /**
     * 保存用户信息
     * @param user
     * @return 返回 -1 表示操作失败，其他是sql语句影响的行数
     */
    int saveUser(User user);

}
