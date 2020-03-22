## HttpServletRequest 类
1. 作用
- 每次只要有请求进入Tomcat服务器，Tomcat服务器就会把请求过来的Http协议信息解析好封装到Request对象中。然后传递到service方法(doGet和doPost)中给我们使用。我们可以通过HttpServletRequest对象，获取到所有请求的信息。
2. HttpServletRequest类的常用方法

   | 方法                    | 作用                                 |
   | ----------------------- | ------------------------------------ |
   | getRequestURI()         | 获取请求的资源路径                   |
   | getRequestURL()         | 获取请求的统一资源定位符（绝对路径） |
   | getRemoteHost()         | 获取客户端的 ip 地址                 |
   | getHeader()             | 获取请求头                           |
   | getParameter()          | 获取请求的参数                       |
   | getParameterValues()    | 获取请求的参数（多个值的时候使用）   |
   | getMethod()             | 获取请求的方式 GET 或 POST           |
   | setAttribute(key,value) | 设置域数据                           |
   | getAttribute(key)       | 获取域数据                           |
   | getRequestDispatcher()  | 获取请求转发对象                     |

   

3. 请求的转发

   请求转发是指：服务器收到请求后，从一个资源跳转到另一个资源的操作。

   **请求转发的特点**：

   1. 浏览器地址栏没有变化
   2. 是一次请求
   3. 他们共享Request域中的数据
   4. 可以转发到WEB-INF目录下
   5. 不可以访问工程以外的资源

4. base标签的使用

   因为转发后地址不会发生改变，所以在页面使用相对路径进行跳转时会发生错误，应该在需要跳转的页面加上base标签。

5. Web中的相对路径和绝对路径

   - 相对路径；

     |        |                     |
     | ------ | ------------------- |
     | .      | 表示当前目录        |
     | ..     | 表示上一级目录      |
     | 资源名 | 表示当前目录/资源名 |

   - 绝对路径：

     http:ip:port/工程路径/资源路径

6. Web中 / 的不同意义

   - 在 web 中 / 斜杠是一种绝对路径。
   - / 斜杠，如果被**浏览器**解析，得到的地址是：http://ip:port/
   - / 斜杠，如果被**服务器**解析，得到的地址是：http://ip:port/工程路径
     - 特殊情况：response.sendRedirect("/");是把 / 斜杠发送给浏览器解析，得到http://ip:port/

## HttpServletResponse 类

1. HttpServletResponse 类的作用

   HttpServletResponse类和HttpServletRequest类一样。每次请求进来，Tomcat服务器都会创建一个Response对象传递给Servlet程序去使用。HttpServletRequest表示请求过来的信息，HttpServletResponse表示所有响应的信息。如果我们需要设置返回客户端的信息，都可以通过HttpServletResponse对象进行设置。

2. 两个输出流的说明

      - 字节流	getOutputStream();	常用于下载（传递二进制数据）

      - 字符流    getWriter();    常用于回传字符串（常用）

        **两个流同时只能使用一个。**
      
3. 如何往客户端回传数据

      要求：往客户端回传字符串数据。

4. 解决响应乱码问题 ： response.setContentType();

5. 请求重定向：

      - 请求重定向，是指客户端给服务器发请求，然后服务器告诉客户端说。我给你一些地址。你去新地址访问。叫请求的重定向（因为之前的地址可能已经被废弃）。

      - 重定向的特点：

        1. 浏览器的地址栏会发生变化
        2. 假设重定向一次，客户端会发送两次请求
        3. 不共享Request域中数据
        4. 不能访问WEB-INF下的资源
        5. 可以访问工程外部资源

      - 请求重定向的实现方法

        response.sendRedirect("location");

## JavaEE 三层架构

### 目录结构

- web层

  - cn.brownqi.web/servlet/controllercom

- service层

  - cn.brownqi.service	                                Service接口包

  - cn.brownqi.service.impl	                       Service接口实现类

- dao持久层

  - cn.brownqi.dao	                                      Dao接口包
  - cn.brownqi.dao.impl                                 Dao接口实现类

- 实体bean对象

  - cn.brownqi.pojo/entity/domain/bean    JavaBean

- 测试包

  - cn.brownqi.test/junit

- 工具类

  - cn.brownqi.utils

## 使用三层架构，完成注册、登陆功能

### 首先准备mysql表

```sql
DROP DATABASE IF EXISTS book;

CREATE DATABASE book;

USE book;

CREATE TABLE t_user(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(20) NOT NULL UNIQUE,
`password` VARCHAR(32) NOT NULL,
`email` VARCHAR(200)
);

INSERT INTO t_user(`username`,`password`,`email`) VALUES('admin','admin','admin@brownqi.cn');

SELECT * FROM t_user;
```

### 编写数据库表对应的 JavaBean 对象

```java
package cn.brownqi.pojo;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 22:27
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
```

### 编写工具类 JdbcUtils

```java
package cn.brownqi.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 22:32
 */
public class JdbcUtils {
    public static void main(String[] args) {

    }

    private static DruidDataSource dataSource;
    static {
        try {
            Properties properties = new Properties();
            //读取 jdbc.properties 属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //流中加载数据
            properties.load(inputStream);
            //创建了数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null，说明获取连接失败，有值就是获取连接成功
     */
    public static Connection getConnection(){

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
    public static void close(Connection conn){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

```

### 编写 BaseDao

```java
package cn.brownqi.dao.impl;

import cn.brownqi.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-21 23:03
 */
public abstract class BaseDao {

    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用来执行：insert、update、delete语句
     *
     * @return 如果返回-1，说明执行失败。返回其他表示影响的行数
     */
    public int update(String sql, Object... args) {

        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询返回一个JavaBean的sql语句
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 查询返回多个JavaBean的sql语句
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     *
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }
        return null;
    }

}

```

### 编写 UserDao 和测试

#### UserDao

```java
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

```

#### UserDaoImpl实现类

````java
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

````

#### UserDao测试类

```java
package cn.brownqi.test;

import cn.brownqi.dao.UserDao;
import cn.brownqi.dao.impl.UserDaoImpl;
import cn.brownqi.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 00:38
 */
public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
//        System.out.println(userDao.queryUserByUsername("admin"));
//        System.out.println(userDao.queryUserByUsername("brownqi"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin1", "admin") == null) {
            System.out.println("用户名或密码错误，登陆失败");
        } else {
            System.out.println("查询成功");
        }

    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "brownqi", "123", "123@xx.com")));
    }
}
```

### 编写UserService 和 测试

#### UserService

```java
package cn.brownqi.service;

import cn.brownqi.pojo.User;

/**
 * @Description:
 * @Author: BrownQi
 * @date: 2020-03-22 09:40
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登陆失败，返回有值，是登陆成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回 true 表示用户名已存在，返回 false 表示用户名可用
     */
    public boolean existsUsername(String username);

}

```

#### UserServiceImpl

```java
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

```

#### UserServiceTest

```java
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
```

### Servlet

#### RegistServlet程序

- 结构
  1. 获取请求参数
  2. 检查验证码是否正确
     - 正确
       - 检查用户名是否可用
         - 可用：调用Service保存到数据库，跳转注册成功页面
         - 不可用：跳回注册页面
     - 不正确
       - 返回注册页面
  
- 实现

  ```java
  package cn.brownqi.web;
  
  import cn.brownqi.pojo.User;
  import cn.brownqi.service.UserService;
  import cn.brownqi.service.impl.UserServiceImpl;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  /**
   * @Description:
   * @Author: BrownQi
   * @date: 2020-03-22 14:04
   */
  public class RegistServlet extends HttpServlet {
      private UserService userService = new UserServiceImpl();
  
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        1. 获取请求参数
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          String email = request.getParameter("email");
          String code = request.getParameter("code"); //验证码写死abcde
  
  //        2. 检查验证码是否正确
          if ("abcde".equalsIgnoreCase(code)) {
  //                - 正确
  //                    - 检查用户名是否可用
              if (userService.existsUsername(username)) {
  //                        - 不可用：跳回注册页面
                  System.out.println("用户名"+username+"已存在！");
                  request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
  
              } else {
  //                        - 可用：调用Service保存到数据库，跳转注册成功页面
                  userService.registUser(new User(null,username,password,email));
                  request.getRequestDispatcher("/pages/user/regist_success.html").forward(request,response);
              }
          } else {
  //                - 不正确
  //                    - 返回注册页面
              System.out.println("验证码" + code + "错误");
              request.getRequestDispatcher("/pages/user/regist.html").forward(request, response);
          }
      }
  
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
      }
  }
  
  ```

#### LoginServlet程序

- 结构

  1. 获取请求的参数
  2. 调用userService.login()登陆
  3. 根据userService.login()方法返回结果判断登陆是否成功
     - 成功：跳转成功页面
     - 失败：跳回登陆页面

- 实现

  ```java
  package cn.brownqi.web;
  
  import cn.brownqi.pojo.User;
  import cn.brownqi.service.UserService;
  import cn.brownqi.service.impl.UserServiceImpl;
  
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import java.io.IOException;
  
  /**
   * @Description:
   * @Author: BrownQi
   * @date: 2020-03-22 16:38
   */
  public class LoginServlet extends HttpServlet {
  
      UserService userService = new UserServiceImpl();
  
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  //        1. 获取请求的参数
          String username = request.getParameter("username");
          String password = request.getParameter("password");
  //        2. 调用userService.login()登陆
          User loginUser = userService.login(new User(null, username, password, null));
  //        3. 根据userService.login()方法返回结果判断登陆是否成功
          if (loginUser == null) {
  //                - 失败：跳回登陆页面
              System.out.println("用户名或密码错误");
              request.getRequestDispatcher("pages/user/login.html").forward(request,response);
          } else {
  //                - 成功：跳转成功页面
              System.out.println("登陆成功");
              request.getRequestDispatcher("pages/user/login_success.html").forward(request,response);
          }
  
  
      }
  
      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
      }
  }
  
  ```

  

