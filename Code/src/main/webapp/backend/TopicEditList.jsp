<%@ page import="com.dcsuibian.domain.User" %>
<%@ page import="java.util.PrimitiveIterator" %>
<%@ page import="com.dcsuibian.dao.ITopicDao" %>
<%@ page import="com.dcsuibian.service.ITopicService" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.dcsuibian.service.impl.TopicServiceImpl" %>
<%@ page import="com.dcsuibian.domain.Topic" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %><%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/4/2
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user=(User)session.getAttribute("user");
    InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
    SqlSession sqlSession =factory.openSession();
    ITopicDao topicDao= sqlSession.getMapper(ITopicDao.class);
    List<Topic> topicList = topicDao.findByEditor(user);
%>
<html>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/backend/TopicEditList.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/backend/TopicEditList.js"></script>
    <title>后台</title>
</head>
<body>
<%--<nav class="navbar navbar-expand-sm bg-dark navbar-dark">--%>
<%--    <a class="navbar-brand" href="/backend/index.jsp">后台</a>--%>
<%--    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">--%>
<%--        <span class="navbar-toggler-icon"></span>--%>
<%--    </button>--%>
<%--    <div class="collapse navbar-collapse" id="collapsibleNavbar">--%>
<%--        <ul class="navbar-nav">--%>
<%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="/backend/TopicEditList.jsp">文章编辑</a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</nav>--%>
<div class="container">
    <div class="col-12">
        <h1>以下是您（<%=user.getName()%>）负责的文章</h1>
        <button type="button" id="createTopic" class="btn btn-primary">新建</button>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>标题</th>
                <th></th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(Topic topic:topicList){
            %>
            <tr>
                <td><a href="/backend/TopicEdit.jsp?id=<%=topic.getId()%>"><%=topic.getId()%></a></td>
                <td><a href="/backend/TopicEdit.jsp?id=<%=topic.getId()%>"><%=topic.getTitle()%></a></td>
                <td></td>
                <td><%=topic.getStatus()%></td>
            </tr>
            <%
                }
            %>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
