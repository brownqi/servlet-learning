<%@ page import="cn.brownqi.model.Good" %><%--
  Created by IntelliJ IDEA.
  User: BrownQi
  Date: 2020/4/1
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情</title>
</head>
<body>
<h1>GoodDetails</h1>
<div>
    <%
        Good good = (Good) request.getAttribute("good");
    %>
    <form action="/orderServlet/addOrder" method="post">
        <p>GOOD-ID:<input type="text" name="goodid" value="<%=good.getGoodId()%>"></p>
        <p>GOOD-NAME:<%=good.getGoodName()%>
        </p>
        <p>GOOD-PRICE:<%=good.getGoodPrice()%>元</p>
        <p>GOOD-COUNT:<%=good.getGoodCount()%>个</p>
        <p>GOOD-ORDER-COUNT:<input type="number" name="ordercount" value="0" min="0"></p>
        <p>
            <input type="submit" value="加入购物车">
        </p>
    </form>
</div>
</body>
</html>
