package cn.brownqi.service;

import cn.brownqi.exception.LoginException;
import cn.brownqi.model.User;

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public int addUser(User user) throws Exception;

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登陆失败，返回有值，是登陆成功
     */
    public User login(User user) throws Exception;

    /**
     * 检查用户名是否可用
     * @param uName
     * @return 返回 true 表示用户名已存在，返回 false 表示用户名可用
     */
    public boolean existsUsername(String uName);

}
