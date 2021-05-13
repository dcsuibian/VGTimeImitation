<%@ page import="com.dcsuibian.domain.Topic" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.dcsuibian.dao.ITopicDao" %>
<%@ page import="javax.swing.text.Style" %><%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/4/10
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
    SqlSession sqlSession =factory.openSession();
    ITopicDao topicDao = sqlSession.getMapper(ITopicDao.class);
    Topic topic=topicDao.findById(Integer.parseInt(request.getParameter("id")));
    sqlSession.close();
    in.close();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <%--    <link rel="stylesheet" href="/css/backend/TopicEdit.css">--%>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/dcsuibian.js"></script>
    <script src="/js/backend/TopicEdit.js"></script>
    <title>新闻编辑</title>
</head>
<body>
<div class="container">
    <div class="col-12">
        <form>
            <div class="form-group">
                <label>作者：</label>
                <input class="form-control" type="text" value="<%=topic.getAuthor().getName()%>" id="author_name">
            </div>
            <div class="form-group">
                <label>标题：</label>
                <input class="form-control" type="text" value="<%=topic.getTitle()%>" id="title">
            </div>
            <div class="form-group">
                <label>简介：</label>
                <input class="form-control" type="text" value="<%=topic.getResume()%>" id="resume">
            </div>
            <div class="form-group">
                <label>类型：</label>
<%--                <input class="form-control" type="text" value="<%=topic.getType()%>" id="type">--%>
                <select id="type">
                    <option value="新闻" <%if("新闻".equals(topic.getType()))out.println("selected");%>>新闻资讯</option>
                    <option value="攻略" <%if("攻略".equals(topic.getType()))out.println("selected");%>>攻略资料</option>
                    <option value="评测" <%if("评测".equals(topic.getType()))out.println("selected");%>>深度评测</option>
                    <option value="文化" <%if("文化".equals(topic.getType()))out.println("selected");%>>游戏文化</option>
                    <option value="动漫" <%if("动漫".equals(topic.getType()))out.println("selected");%>>动漫时光</option>
                    <option value="书刊" <%if("书刊".equals(topic.getType()))out.println("selected");%>>最新书刊</option>
                </select>
            </div>
            <div class="form-group">
                <label>封面链接：</label>
                <input class="form-control" type="text" value="<%=topic.getCoverImage()%>" id="cover_image">
            </div>
            <div class="form-group">
                <label>内容：</label>
                <textarea class="form-control" style="height: 500px" id="content"><%=topic.getContent()%></textarea>
            </div>
            <button type="button" class="btn btn-success" id="btn-save">草稿</button>
            <button type="button" class="btn btn-primary" id="btn-submit">提交</button>
            <button type="button" class="btn btn-secondary" id="btn-clear">清空</button>
        </form>
    </div>
</div>
</body>
</html>
