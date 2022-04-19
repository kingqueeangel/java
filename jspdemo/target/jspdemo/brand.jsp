<%--
  Created by IntelliJ IDEA.
  User: aaa66
  Date: 2022-4-14
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ye</title>
</head>
<body>
<input type="button" value="新增" id="add"><br>
<hr>
<table border="1" cellspacing="0" width="80%" h>
    <tr>
        <th height="50">序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>

    </tr>


    <c:forEach items="${brands}" var="brand" varStatus="status">
        <tr align="center">
                <%-- <td height="50">${brand.id}</td>--%>
            <td height="50">${status.count}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.description}</td>
            <c:if test="${brand.status==1}">
                <td>启用</td>
            </c:if>
            <c:if test="${brand.status!=1}">
                <td>禁用</td>
            </c:if>
            <td><a href="/selectByIdServlet?id=${brand.id}">修改</a> <a href="/deleteByIdServlet?id=${brand.id}" onclick="return confirm('确认删除吗？');" >删除</a></td>

        </tr>

    </c:forEach>
</table>

<script>
    document.getElementById("add").onclick = function () {
        location.href = "/addBrand.jsp"
    }

</script>
</body>
</html>
