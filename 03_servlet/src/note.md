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

