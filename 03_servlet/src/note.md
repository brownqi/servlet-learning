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

   
