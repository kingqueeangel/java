<%--
  Created by IntelliJ IDEA.
  User: aaa66
  Date: 2022-4-15
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/login/registerServlet" method="post">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="userName" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg">${register_msg}</span>
                </td>
            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img id="checkCodeImg" src="/login/checkCodeServlet">
                    <a href="#" id="changeImg" onclick="">看不清？</a>
                    <br>
                    <span id="checkCode_err" class="err_msg">${checkCode_msg}</span>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>
<script>
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/login/checkCodeServlet?"+new Date().getMilliseconds();
    };
    document.getElementById("checkCodeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/login/checkCodeServlet?"+new Date().getMilliseconds();
    };
    document.getElementById("username").onblur= function (){
        let username=this.value;
        let http;
        if (window.XMLHttpRequest) {
            http = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            http = new ActiveXObject("Microsoft.XMLHTTP");
        }

        //TODO AXIOS改造
        http.open("GET", "http://localhost/login/usernameServlet?username="+username);
        http.send();
        http.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText =="true"){
                    document.getElementById("username_err").innerText ="用户名已存在" ;
                }else{
                    document.getElementById("username_err").innerText = '';
                }
            }
        }
    };





</script>
</body>
</html>