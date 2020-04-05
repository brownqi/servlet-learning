<%--
  Created by IntelliJ IDEA.
  User: BrownQi
  Date: 2020/4/1
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<h1>注册</h1>
<form action="/userServlet/addUser" method="post" onsubmit="return _submit()">

    <p>
        <label for="uName">用户账号</label>
        <input type="text" id="uName" name="uName" placeholder="请输入注册账号">
        <span id="user-message"></span>
    </p>

    <p>
        <label for="uPwd">密码</label>
        <input type="password" id="uPwd" name="uPwd" placeholder="请输入密码">
    </p>

    <p>
        <label for="re-uPwd">确认密码</label>
        <input type="password" id="re-uPwd" name="re-uPwd" placeholder="请确认密码">
    </p>

    <p id="pwd-check">
        两次密码输入需要相同
    </p>

    <p>
        <label for="uBirth">用户生日</label>
        <input type="date" id="uBirth" name="uBirth">
    </p>

    <p>
        <input type="submit" value="注册">
    </p>

    <p style="color:red">
        <%
            Object result = request.getAttribute("message");
            if (result != null) {
                String message = (String) result;
        %>
        <%=message%>
        <%
            }
        %>
    </p>

</form>

<script>
    let pwd_new = document.getElementById("re-uPwd");
    pwd_new.addEventListener("blur", function () {
        let pwd_value = document.getElementById("uPwd").value;
        let pwd_value_new = document.getElementById("re-uPwd").value;
        let pwd_check = document.getElementById("pwd-check");
        if (pwd_value !== pwd_value_new) {
            pwd_check.innerText = "两次密码输入不同";
        } else {
            pwd_check.innerText = "两次密码输入相同";
        }
    })

    function _submit() {
        let username = document.getElementById("uName").value;
        let password = document.getElementById("uPwd").value;
        let re_password = document.getElementById("re_uPwd").value;
        let userBirth = document.getElementById("uBirth").value;
        if (username.length === 0) return false;
        if (password.length === 0) return false;
        if (userBirth.length === 0) return false;
        if (password !== re_password) return false;
        return true;
    }

    let userInput = document.getElementById("uName");
    userInput.addEventListener("keyup", function (event) {
        let username = event.target.value;
        let user_message = document.getElementById("user-message");
        axios.get(`http://localhost:8080/userServlet/checkUserName`,{
            params: {
                uNmae: username
            }
        }).then(
            function (resp) {
                console.log(resp.data)
                if (resp.data.data.result) {
                    user_message.innerText = "用户名已存在";
                } else {
                    user_message.innerText = "用户名可用";
                }
            }
            )
    });


</script>

</body>
</html>
