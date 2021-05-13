<%@ page import="com.dcsuibian.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/4/2
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
User user=(User)session.getAttribute("user");
%>
<html>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/backend/index.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <title>后台</title>
</head>
<body>
<div class="jumbotron text-center" style="margin-bottom: 0">
    <h1>vgtime后台管理页面</h1>
    <p>欢迎，<%=user.getName()%>！！！</p>
</div>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/backend/index.jsp">后台</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/backend/TopicEditList.jsp">文章编辑</a>
            </li>
            <%
                if("root".equals(user.getRole())||"总编".equals(user.getRole())||"副总编".equals(user.getRole())){
                    out.println("<li class=\"nav-item\">\n" +
                            "                <a class=\"nav-link\" href=\"/backend/UserManage.jsp\">人员管理</a>\n" +
                            "            </li>\n" +
                            "            <li class=\"nav-item\">\n" +
                            "                <a class=\"nav-link\" href=\"/backend/TopicAuditList.jsp\">稿件审核</a>\n" +
                            "            </li>");
                }
            %>
        </ul>
    </div>
</nav>

</body>
</html>
