<%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/4/19
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/backend/TopicAuditList.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/dcsuibian.js"></script>
    <script src="/js/backend/TopicAuditList.js"></script>
    <title>文章审核</title>
</head>
<body>
<div class="Container">
    <div class="Header">
        <div class="logo" align="center">审核管理页面</div>
    </div>
    <div class="Content">
        <div class="Content-Up">
            <input type="text" class="Content-Up-input_text" id="searchText">
            <input type="radio" class="Content-Up-input_radio" name="status" value="提交" checked="checked">未审核
            <input type="radio" class="Content-Up-input_radio" name="status" value="发布">通过
            <input type="radio" class="Content-Up-input_radio" name="status" value="退回">退回
            <select id="SearchNewsType">
                <option value="全部" selected="selected">全部</option>
                <option value="新闻">新闻资讯</option>
                <option value="攻略">攻略资料</option>
                <option value="评测">深度评测</option>
                <option value="文化">游戏文化</option>
                <option value="动漫">动漫时光</option>
                <option value="书刊">最新书刊</option>
            </select>
            <select id="orderby">
                <option value="change_time DESC" selected="selected">时间从近到远</option>
                <option value="change_time ASC">时间从远到近</option>
            </select>
            <input type="button" id="searchButton" value="查询">
        </div>
        <div class="Content-Main" align="center">
        </div>
        <div class="Content-Down">
            <a id="previouspage" class="Content-Down-a" href="#" onclick="return false">上一页</a>
            <input type="text" id="currentpage" class="Content-Down-input_text">
            <label>/</label>
            <label id="lastpage">333</label>
            <a id="nextpage" class="Content-Down-a" href="#" onclick="return false">下一页</a>
        </div>
    </div>
    <div class="Clear">
    </div>
    <div class="Footer" align="center">
        版权所有 © 2016-2020
        计算机161 丁翀
    </div>
</div>
</body>
</html>
