<%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/4/17
  Time: 15:50
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
    <link rel="stylesheet" href="/css/backend/TopicAudit.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/dcsuibian.js"></script>
    <script src="/js/backend/TopicAudit.js"></script>
    <title>文章审核</title>
</head>
<body>
<div class="container">
    <div class="row">
<%--        <div class="col-3 topic_list_item" style="background-color:lavender;"><a href="javascript:void(0);">--%>
<%--            <div class="img_box">--%>
<%--                <img src="https://img01.vgtime.com/game/cover/2020/04/17/200417152709221_u202875.jpg">--%>
<%--            </div>--%>
<%--            <div class="info_box">--%>
<%--                <p>6&nbsp;&nbsp;&nbsp;&nbsp;露璃娜专场！《宝可梦 剑/盾》动画第四集《晚波》上线</p>--%>
<%--            </div>--%>
<%--        </a></div>--%>
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
    </div>
    <div class="row">
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
    </div>
    <div class="row">
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
    </div>
    <div class="row">
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavenderblush;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
        <div class="col-3 topic_list_item" style="background-color:lavender;"><div class="img_box"></div><div class="info_box"><a><p>&nbsp;</p></a></div></div>
    </div>
</div>
<div class="modal fade" id="topicModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">模态框头部</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <div style="width: 1600px;height: 50px;background-color: red"></div>
            </div>
            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>
</body>
</html>
