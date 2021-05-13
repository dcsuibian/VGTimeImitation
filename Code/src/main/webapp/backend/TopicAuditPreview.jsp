<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.dcsuibian.service.ITopicService" %>
<%@ page import="com.dcsuibian.domain.Topic" %><%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/4/18
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationContext.xml");
    ITopicService topicService = applicationContext.getBean("topicService", ITopicService.class);
    Topic topic = topicService.findById(Integer.parseInt(request.getParameter("id")));
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/backend/TopicAuditPreview.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/showdown.min.js"></script>
    <script src="/js/dcsuibian.js"></script>
    <script src="/js/backend/TopicAuditPreview.js"></script>
    <title>文章审核</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <h1><%=topic.getTitle()%></h1>
        </div>
    </div>
    <div class="row"><div class="col-12">&nbsp;</div></div>
    <div class="row">
        <div class="col-8 offset-2" id="content"><%=topic.getContent()%></div>
    </div>
    <div class="row">
        <div class="col-1 offset-3">
            <button type="button" class="btn btn-primary" id="pass">通过</button>
        </div>
        <div class="col-1 offset-3">
            <button type="button" class="btn btn-secondary" id="reject">退回</button>
        </div>
    </div>
</div>
</body>
</html>
