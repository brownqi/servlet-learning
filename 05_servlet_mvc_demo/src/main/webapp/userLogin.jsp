<%--
  Created by IntelliJ IDEA.
  User: BrownQi
  Date: 2020/4/1
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆</title>
</head>
<body>
<h1>登陆</h1>
<form action="/userServlet/login" method="post">

    <p>
        <label for="uName">用户账号</label>
        <input type="text" id="uName" name="uName" placeholder="请输入注册账号">
    </p>

    <p>
        <label for="uPwd">密码</label>
        <input type="password" id="uPwd" name="uPwd" placeholder="请输入密码">
    </p>

    <p>
        <input type="submit" value="登陆">
    </p>
</form>

<p>
    <%=request.getAttribute("login-message")==null?"":request.getAttribute("login-message") %>
</p>

</body>
</html>
