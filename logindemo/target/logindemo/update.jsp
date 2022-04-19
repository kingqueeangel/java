<%--
  Created by IntelliJ IDEA.
  User: aaa66
  Date: 2022-4-14
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>修改品牌</title>
</head>
<body>
<h3>修改品牌</h3>
<form action="/login/updateServlet" method="post">
    品牌名称：<input name="brandName" value="${brand.brandName}"><br>
    企业名称：<input name="companyName" value="${brand.companyName}"><br>
    排序：<input name="ordered" value="${brand.ordered}"><br>
    描述信息：<textarea rows="5" cols="20" name="description">${brand.description}</textarea><br>
    状态：

    <input type="radio" name="status" value="0" id="on"
    <c:if test="${brand.status==0}">
           checked=true
    </c:if> >禁用
    <input type="radio" name="status" value="1" id="off"
    <c:if test="${brand.status==1}">
           checked=true
    </c:if> >启用<br>
    <input type="hidden" name="id" value="${brand.id}">
    <input type="hidden" name="user" value="${brand.user}">
    <input type="submit" value="提交">

</form>
</body>
</html>