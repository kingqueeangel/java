<%--
  Created by IntelliJ IDEA.
  User: aaa66
  Date: 2022-4-14
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/login/loginServlet" id="form" method="post">
        <h1 id="loginMsg">LOGIN IN</h1>
        <div id="errorMsg">${login_msg} ${register_msg} </div>
        <p>Username:<input id="username" name="userName" type="text" value="${cookie.username.value}"></p>
        <p>Password:<input id="password" name="password" type="password" value="${cookie.password.value}"></p>
        <p>Remember:<input id="remember" name="remember" value="1" type="checkbox"

        ></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？</a>

        </div>
    </form>
</div>
<script>

    if (${cookie.password.value==null}) {
        document.getElementById("remember").checked = false;
    } else {
        document.getElementById("remember").checked = true;
    }

</script>

<%--<c:if test="${cookie.username!=null}"> checked
</c:if>
<c:if test="${cookie.username==null}"> checke=false
</c:if>--%>

</body>
</html>
