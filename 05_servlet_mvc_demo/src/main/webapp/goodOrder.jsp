<%@ page import="cn.brownqi.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="cn.brownqi.model.Good" %><%--
  Created by IntelliJ IDEA.
  User: BrownQi
  Date: 2020/4/1
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品订单</title>
</head>
<body>
<%
    Map<Order, Good> orders = (Map<Order, Good>) request.getAttribute("orders");
%>

<table width="100%" style="text-align: center">
    <thead>
    <tr>
        <th>订单编号</th>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Map.Entry<Order,Good> entry : orders.entrySet()) {
            Order order = entry.getKey();
            Good good = entry.getValue();
    %>

    <tr>
        <td><%=order.getGoodId()%>
        </td>
        <td><%=good.getGoodName()%>
        </td>
        <td><%=good.getGoodPrice()%>
        </td>
        <td><%=order.getOrderCount()%>
        </td>
        <td>
            <%
                Integer orderState = order.getOrderState();
                String word = "";
                if (orderState == 1) {
                    word = "已支付";
                } else {
                    word = "<a href=\"/orderServlet/pay?orderid=" + order.getOrderId() + "\" >支付</a>";
                }
            %>
            <%=word%>
        </td>
    </tr>

    <%
        }
    %>

    </tbody>
</table>
</body>
</html>
