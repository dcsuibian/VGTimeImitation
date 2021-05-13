<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/3/9
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>查询所有的账户</h3>
    <c:forEach items="${list}" var="user">
        ${user.name}
    </c:forEach>
</body>
</html>
