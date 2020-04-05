<%@ page import="cn.brownqi.model.User" %>
<%@ page import="cn.brownqi.model.Good" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: BrownQi
  Date: 2020/4/1
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1>主页：<%=session.getAttribute("user")==null?"未登录":((User)session.getAttribute("user")).getuName()%></h1>
<p>
    <a href="/userAdd.jsp">注册</a>
    <a href="/userLogin.jsp">登陆</a>
</p>


<p>
<form action="/goodServlet/search" method="get">
    <input type="text" name="goodname">
    <input type="submit" value="搜索"></form>
</p>

<table width="100%" style="text-align: center">
    <thead>
    <tr>
        <th>GOOD-ID</th>
        <th>GOOD-NAME</th>
        <th>GOOD-PRICE</th>
        <th>GOOD-COUNT</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Good> list = (List<Good>) request.getAttribute("goods");
        if (list.size() > 0){
            for (int i = 0 ;i < list.size() ;i++){
                Good good = list.get(i);
    %>
    <tr>
        <td><%=good.getGoodId()%></td>
        <td><a href="/good/detail?goodid=<%=good.getGoodId()%>"><%=good.getGoodName()%></a></td>
        <td><%=good.getGoodPrice()%></td>
        <td><%=good.getGoodCount()%></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table >

</body>
</html>
