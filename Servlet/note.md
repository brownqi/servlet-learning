# Servlet

## Servlet技术
1. 什么是Servlet
    - Servlet 是Java EE规范之一，规范就是**接口**
    - Servlet 是JavaWeb三大组件之一。三大组件分别实：Servlet程序、Filter过滤器、Listener监听器。
    - Servlet 是运行在服务器上得一个Java小程序，**它可以接收客户端发送过来的请求，并响应数据给客户端**。
2. 手动实现Servlet程序
    - 编写一个类，**实现Servlet接口**
    - 实现 service 方法，处理请求，并响应数据
    - 到web.xml中取配置 servlet 程序得访问地址
    ```xml
        <!-- servlet标签给Tomcat配置Servlet程序-->
    <servlet>
        <!--name标签 给 Servlet 程序起一个别名（一般是类名）-->
        <servlet-name>HelloServlet</servlet-name>
        <!--class标签 是 Servlet 程序的全类名-->
        <servlet-class>cn.brownqi.servlet.HelloServlet</servlet-class>
    </servlet>
    
        <!--servlet-mapping标签给 Servlet 程序配置访问地址-->
    <servlet-mapping>
        <!--name标签的作用是告诉服务器，当前配置的地址给哪个Servlet程序使用-->
        <servlet-name>HelloServlet</servlet-name>
        <!--url-pattern标签配置访问地址
            / 斜杠在服务器解析的时候，表示地址为：http://ip:port/工程路径/
            /hello 表示地址为：http://ip:port/工程路径/hello/
        -->
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    ```
3. Servlet的生命周期
    1. 执行 Servlet 构造器方法
    2. 执行 init 初始化方法
    3. 执行service 方法
    4. 执行destroy 销毁方法
    - 构造器与init方法，是在第一次访问的时候，创建Servlett，被调用
    - service方法，在初始化之后每次访问都会被调用
    - destroy方法，在web工程停止的时候调用
4. 

## ServletConfig类
Servlet 程序的配置信息类。
Servlet 程序和 ServletConfig 对象都是由 Tomcat 负责创建，我们负责使用
Servlet 程序默认是第一次访问的时候创建，ServletConfig是每个 Servlet 程序创建时，就创建一个对应的 ServletConfig 对象。

### ServletConfig类的三大作用
1. 可以获取 Servlet 程序的别名 servlet-name 的值
2. 获取初始化参数 init-param
3. 获取 ServletContext 对象

### ServletConfig类使用注意事项
在 我们的 Servlet 程序（继承自HttpServlet类）重写init方法要注意不要缺少super.init(config);，否则ServletConfig对象无法传入。

## ServletContext类
### 什么是 ServletContext
1. ServletContext 是一个接口，它表示 Servlet 上下文对象
2. 一个 web 工程，只有一个 ServletContext 对象实例
3. ServletContext 对象是一个**域对象**。
- 什么是域对象？
    - 域对象，是可以像 Map 一样存取数据的对象，叫域对象。
    - 这里的域指的是存取数据的操作范围,整个web工程。

    | \  | 存数据  |  取数据 | 删除数据  |
    |---|---|---|---|
    | Map  | put()  | get()  | remove()  |
    | 域对象  |  setAttribute() | getAttribute()  | removeAttribute()  |
### ServletContext 类的四个作用
1. 获取 web.xml 中配置的上下文参数 context-param
2. 获取当前的工程路径，格式：/工程路径
3. 获取工程部署后在服务器磁盘上的绝对路径
4. 像 Map 一样存取数据

## Http协议
### 什么是HTTP协议
1. 什么是协议？
    - 协议是指双方，或多方，相互约定好，大家都需要遵守的规则则，叫协议
2. 所谓HTTP协议，就是指**客户端**和**服务器**之间通信时，发送的数据，需要遵守的规则，叫HTTP协议。
3. HTTP协议中的数据又叫**报文**

### 请求的HTTP协议格式
- 客户端给服务器发送数据叫请求
- 服务器给客户端回传数据叫响应
1. **请求**又分为 `GET` 请求,和 `POST` 请求两种
    1. GET 请求
        1. 请求行
            1. 请求的方式:`GET`
            2. 请求的资源路径[+?+请求参数]
            3. 请求的协议版本号:`HTTP/1.1]`
        2. 请求头
            1. key:value 组成,不同的键值对表示不同的含义
    2. POST 请求
        1. 请求行
            1. 请求的方式:`POST`
            2. 请求的资源路径[+?+请求参数]
            3. 请求的协议版本号:`HTTP/1.1]`
        2. 请求头
            1. key:value 
        - 空行
        3. **请求体** : 发送给服务器的数据
    3. 常用的请求头的说明
        - Accept:表示客户端可以接收的数据类型
        - Accept-Language:表示客户端可以接收的语言类型
        - User-Agent:表示客户端浏览器的信息
        - Host:表示请求时的服务器ip和端口号
    4. 哪些是GET请求,哪些是POST请求
        1. GET请求有哪些:
            - form标签:method=get
            - a标签
            - link标签引入css
            - script标签引入js文件
            - img标签引入图片
            - iframe引入html页面
            - 在浏览器地址栏输入地址后敲回车
        2. POST请求有哪些:
            - form标签:method=post
### 响应的HTTP协议格式
1. 响应行
    1. 响应的协议和版本号:HTTP/1.1
    2. 响应的状态码
    3. 响应状态描述符
2. 响应头
    1. key:value 不同的响应头,有其不同的含义
- 空行
3. 响应体:回传给客户端的数据

### 常用的响应码说明
- 200:表示请求
- 302:表示请求重定向
- 404:表示请求服务器已经收到了,但是请求的数据不存在(请求地址错误)
- 500:表示服务器已经收到请求,但是服务器内部错误(代码错误)

### MIME类型说明
- MIME是HTTP协议中数据类型
- MIME的英文全称是"Multipurpose Internet Mail Extensions"多功能Internet邮件扩充服务.MIME类型的格式是"大类型/小类型",并与某一种文件的扩展名相对应