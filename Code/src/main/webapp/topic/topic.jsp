<%@ page import="com.dcsuibian.dao.ITopicDao" %>
<%@ page import="com.dcsuibian.domain.Topic" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="com.vladsch.flexmark.util.data.MutableDataSet" %>
<%@ page import="com.vladsch.flexmark.parser.ParserEmulationProfile" %>
<%@ page import="com.vladsch.flexmark.parser.Parser" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.vladsch.flexmark.util.misc.Extension" %>
<%@ page import="com.vladsch.flexmark.ext.tables.TablesExtension" %>
<%@ page import="com.vladsch.flexmark.html.HtmlRenderer" %>
<%@ page import="com.vladsch.flexmark.util.ast.Node" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.dcsuibian.service.ITopicService" %>
<%@ page import="com.dcsuibian.service.ITopicCommentService" %>
<%@ page import="com.dcsuibian.domain.TopicCommentGroup" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/3/11
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    ITopicService topicService = ac.getBean("topicService", ITopicService.class);
    ITopicCommentService topicCommentService = ac.getBean("topicCommentService", ITopicCommentService.class);

    Topic topic=topicService.findById(Long.parseLong(request.getParameter("id")));
    List<TopicCommentGroup> tcgl = TopicCommentGroup.getTopicCommentGroupList(topicCommentService.findByTopicId(topic.getId()));
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/user.css">
    <link rel="stylesheet" href="/css/topic.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/showdown.min.js"></script>
    <script src="/js/dcsuibian.js"></script>
    <script src="/js/user.js"></script>
    <script src="/js/topic.js"></script>
    <title>topic</title>
</head>
<body>
<div class="user_nav">
    <div class="user_nav_content">
        <div>
            <a href="javascript:void(0)" id="user_main_display"><i class="fa fa-address-book-o"></i></a>
        </div>
    </div>
</div>
<div class="user_main">
    <div id="loginPanel">
        <h2>??????</h2>
        <form onsubmit="return false">
            <label>??????</label>
            <input class="input-group" type="text" id="login_phoneNumber" placeholder="??????????????????">
            <label>??????</label>
            <input class="input-group" type="password" id="login_password" placeholder="8????????????????????????">
            <div class="auto_login_btn">
                <input type="checkbox">?????????
            </div>
            <input class="btn btn-info" type="submit" value="??????" id="btn_login">
            <%--            <div class="forget_password">--%>
            <%--                <a class="forget_password_link" href="#">????????????</a>--%>
            <%--            </div>--%>
            <button role="button" class="btn btn-dark" id="register_now">????????????</button>
        </form>
    </div>
    <div id="registerPanel">
        <h2>?????????????????????</h2>
        <form onsubmit="return false">
            <label>?????????</label>
            <input class="input-group" type="text" id="register_phone_number" placeholder="">
            <%--            <label>?????????</label>--%>
            <%--            <input class="input-group" type="text" id="register_verify_code" placeholder="">--%>
            <label>??????</label>
            <input class="input-group" type="text" id="register_password" placeholder="">
            <%--            <label>??????</label>--%>
            <%--            <input class="input-group" type="text" id="register_password_again" placeholder="">--%>
            <label>??????</label>
            <input class="input-group" type="text" id="register_real_name" placeholder="">
            <label>?????????</label>
            <input class="input-group" type="text" id="register_identity_card" placeholder="">
            <input class="btn btn-info" type="submit" value="??????" id="btn_register">
            <button role="button" class="btn btn-dark" id="login_now">??????</button>
        </form>
    </div>
    <div id="loginedPanel">
        <h2>?????????</h2>
        <button type="button" class="btn btn-danger" id="btn_exit">????????????</button>
    </div>
</div>
<div class="head">
    <header>
        <div class="vg_logo">
            <img src="/image/vgtime_logo_1.PNG">
        </div>
        <div class="vg_head_message">
            <p>UCG????????????????????????QQ??? 566282315</p>
        </div>
        <div class="vg_head_nav">
            <ul class="vg_head_nav_list clearfix">
                <li class="vg_head_nav_list_item">
                    <a href="#">??????</a>
                </li>
                <li class="vg_head_nav_list_item">
                    <a href="#">??????</a>
                </li>
                <li class="vg_head_nav_list_item">
                    <a href="#">????????????</a>
                </li>
                <li class="vg_head_nav_list_item">
                    <a href="#">????????????</a>
                </li>
                <li class="vg_head_nav_list_item">
                    <a href="#">????????????</a>
                </li>
                <li class="vg_head_nav_list_item">
                    <a href="#">????????????</a>
                </li>
                <li class="vg_head_nav_list_item">
                    <a href="#">?????????</a>
                </li>
                <li class="vg_head_nav_list_item">
                    <a href="#">UCG??????</a>
                </li>
            </ul>
        </div>
    </header>
</div>
<div class="main_body">
    <div class="main_center">
        <article>
            <h1 class="article_title"><%=topic.getTitle()%>
            </h1>
            <div class="author_editor_time">
                ?????????
                <span><%=topic.getAuthor().getName()%></span>
                ?????????
                <span><%=topic.getEditor().getName()%></span>
                <i class="fa fa-clock-o"></i>
                2020-03-10 18:00:00
            </div>
            <div class="resume">
                <p><%=topic.getResume()%>
                </p>
            </div>
            <div class="topic_content" id="content"><%=topic.getContent()%>
            </div>
        </article>
    </div>
    <div class="article_fot_box">
        <div class="article_fot_con">
            <div class="vg_baidu"></div>
            <div class="vg_comment_box">
                <div class="do_comment_box">
                    <div class="comment_textarea">
                        <img src="/image/tou.gif">
                        <input class="input-group input-group-sm" id="comment_topic_input" disabled placeholder="?????????">
                        <button type="button" class="btn btn-outline-primary" id="comment_topic_btn" disabled>??????</button>
                    </div>
                </div>
                <%--<ul class="comment_list">
                    <li>
                        <div class="comment_info">
                            <img class="comment_info_uimg" src="https://img01.vgtime.com/headpic/2017/06/03/170603092544393.jpg">
                            <span class="comment_info_uname">Vaerkily</span>
                        </div>
                        <div class="comment_box">
                            <p>??????switch??????1559?????????????????????????????????????????????????????????????????????</p>
                        </div>
                        <div class="comment_operate">
                            <span class="time">2020-04-18 17:16:23</span>
                            <span class="reply"><a href="javascript:void(0);">??????</a></span>
                        </div>
                        <div class="comment_reply_list">
                            <ul>
                                <li>
                                    <div class="comment_info">
                                        <img class="comment_info_uimg" src="https://img01.vgtime.com/headpic/2017/06/03/170603092544393.jpg">
                                        <span class="comment_info_uname">Vaerkily</span>
                                    </div>
                                    <div class="comment_box">
                                        <p>??????switch??????1559?????????????????????????????????????????????????????????????????????</p>
                                    </div>
                                    <div class="comment_operate">
                                        <span class="time">2020-04-18 17:16:23</span>
                                        <span class="reply"><a href="javascript:void(0);">??????</a></span>
                                    </div>
                                </li>
                                <li>
                                    <div class="comment_info">
                                        <img class="comment_info_uimg" src="https://img01.vgtime.com/headpic/2017/06/03/170603092544393.jpg">
                                        <span class="comment_info_uname">Vaerkily</span>
                                    </div>
                                    <div class="comment_box">
                                        <p>??????switch??????1559?????????????????????????????????????????????????????????????????????</p>
                                    </div>
                                    <div class="comment_operate">
                                        <span class="time">2020-04-18 17:16:23</span>
                                        <span class="reply"><a href="javascript:void(0);">??????</a></span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>--%>
                <%for(int i=0;i<tcgl.size();i++){%>
                <ul class="comment_list">
                    <li>
                        <div class="comment_info">
                            <img class="comment_info_uimg" src="<%=tcgl.get(i).getPrimaryComment().getSender().getProfilePicture()%>">
                            <span class="comment_info_uname"><%=tcgl.get(i).getPrimaryComment().getSender().getName()%></span>
                        </div>
                        <div class="comment_box">
                            <p><%=tcgl.get(i).getPrimaryComment().getContent()%></p>
                        </div>
                        <div class="comment_operate">
                            <span class="time"><%=tcgl.get(i).getPrimaryComment().getTime()%></span>
                            <span class="reply"><a href="javascript:void(0);" <%--data-toggle="modal" data-target="#myModal"--%> data="<%=tcgl.get(i).getPrimaryComment().getId()%>">??????</a></span>
                        </div>
                        <div class="comment_reply_list">
                            <ul>
                                <%for(int j=0;j<tcgl.get(i).getSecondaryComments().size();j++){%>
                                <li>
                                    <div class="comment_info">
                                        <img class="comment_info_uimg" src="<%=tcgl.get(i).getSecondaryComments().get(j).getSender().getProfilePicture()%>">
                                        <span class="comment_info_uname"><%=tcgl.get(i).getSecondaryComments().get(j).getSender().getName()%></span>
                                    </div>
                                    <div class="comment_box">
                                        <p>?????? <%=tcgl.get(i).getSecondaryComments().get(j).getReceiver().getName()%> ???<%=tcgl.get(i).getSecondaryComments().get(j).getContent()%></p>
                                    </div>
                                    <div class="comment_operate">
                                        <span class="time"><%=tcgl.get(i).getSecondaryComments().get(j).getTime()%></span>
                                        <span class="reply"><a href="javascript:void(0);" <%--data-toggle="modal" data-target="#myModal"--%> data="<%=tcgl.get(i).getSecondaryComments().get(j).getId()%>">??????</a></span>
                                    </div>
                                </li>
                                <%}%>
                            </ul>
                        </div>
                    </li>
                </ul>
                <%}%>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- ??????????????? -->
            <div class="modal-header">
                <h4 class="modal-title">???????????????</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- ??????????????? -->
            <div class="modal-body">
                <input type="text" class="input-group" id="comment_to_comment_input">
            </div>

            <!-- ??????????????? -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="comment_to_comment_btn">??????</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">??????</button>
            </div>

        </div>
    </div>
</div>
</body>
</html>
