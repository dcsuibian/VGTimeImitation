<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="com.dcsuibian.dao.ITopicDao" %>
<%@ page import="com.dcsuibian.dao.IUserDao" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.dcsuibian.domain.Topic" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/3/10
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
    SqlSession sqlSession = factory.openSession();
    ITopicDao topicDao = sqlSession.getMapper(ITopicDao.class);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/user.css">
    <link rel="stylesheet" href="/css/index.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/dcsuibian.js"></script>
    <script src="/js/user.js"></script>
    <script src="/js/index.js"></script>
    <title>游戏时光</title>
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
        <h2>我的</h2>
        <form onsubmit="return false">
            <label>账号</label>
            <input class="input-group" type="text" id="login_phoneNumber" placeholder="请输入手机号">
            <label>密码</label>
            <input class="input-group" type="password" id="login_password" placeholder="8位以上字母或数字">
            <div class="auto_login_btn">
                <input type="checkbox">记住我
            </div>
            <input class="btn btn-info" type="submit" value="登录" id="btn_login">
            <%--            <div class="forget_password">--%>
            <%--                <a class="forget_password_link" href="#">忘记密码</a>--%>
            <%--            </div>--%>
            <button role="button" class="btn btn-dark" id="register_now">现在注册</button>
        </form>
    </div>
    <div id="registerPanel">
        <h2>使用手机号注册</h2>
        <form onsubmit="return false">
            <label>手机号</label>
            <input class="input-group" type="text" id="register_phone_number" placeholder="">
            <%--            <label>验证码</label>--%>
            <%--            <input class="input-group" type="text" id="register_verify_code" placeholder="">--%>
            <label>密码</label>
            <input class="input-group" type="text" id="register_password" placeholder="">
            <%--            <label>密码</label>--%>
            <%--            <input class="input-group" type="text" id="register_password_again" placeholder="">--%>
            <label>姓名</label>
            <input class="input-group" type="text" id="register_real_name" placeholder="">
            <label>身份证</label>
            <input class="input-group" type="text" id="register_identity_card" placeholder="">
            <input class="btn btn-info" type="submit" value="注册" id="btn_register">
            <button role="button" class="btn btn-dark" id="login_now">登录</button>
        </form>
    </div>
    <div id="loginedPanel">
        <h2>已登录</h2>
        <button type="button" class="btn btn-danger" id="btn_exit">退出登录</button>
    </div>
</div>
<div class="main_body">
    <div class="main_center">
        <%
            List<Topic> latestTopics = topicDao.queryByConditions(null, null, null, "发布", null, "change_time DESC", 0L, 5L);
            if (latestTopics.size() > 0) {
        %>
        <div class="focus_box">
            <%
                for (int i = 0; i < 5; i++) {
                    Topic topic = i < latestTopics.size() ? latestTopics.get(i) : latestTopics.get(latestTopics.size() - 1);
            %>
            <div><a href="<%="/topic/topic.jsp?id="+topic.getId()%>"><img
                    src="<%=topic.getCoverImage()%>"
                    alt=""></a></div>
            <%
                }
            %>
        </div>
        <%
            }
            List<Topic> news = topicDao.queryByConditions(null, null, "新闻", "发布", null, "change_time DESC", 0L, 16L);
            if (news.size() > 0) {
        %>
        <div class="news_box">
            <h2 class="vg_title">新闻资讯</h2>
            <div class="news_list">
                <div class="topic_list_first">
                    <div class="img_box">
                        <a href="<%="/topic/topic.jsp?id="+news.get(0).getId()%>">
                            <img src="<%=news.get(0).getCoverImage()%>">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="<%="/topic/topic.jsp?id="+news.get(0).getId()%>"><h2><%=news.get(0).getTitle()%>
                        </h2></a>
                        <p><%=news.get(0).getResume()%>
                        </p>
                        <div class="fot_box"><%=news.get(0).getAuthor().getName()%>
                        </div>
                    </div>
                </div>
                <%
                    if (news.size() > 1) {
                        for (int i = 1; i < 4; i++) {
                            Topic topic = i < news.size() ? news.get(i) : news.get(news.size() - 1);
                %>
                <div class="topic_list_big">
                    <div class="img_box">
                        <a href="<%="/topic/topic.jsp?id="+topic.getId()%>">
                            <img src="<%=topic.getCoverImage()%>">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="<%="/topic/topic.jsp?id="+topic.getId()%>"><h2><%=topic.getTitle()%>
                        </h2></a>
                        <p><%=topic.getResume()%>
                        </p>
                        <div class="fot_box"><%=topic.getAuthor().getName()%>
                        </div>
                    </div>
                </div>
                <%
                    }
                    if (news.size() > 4) {
                %>
                <div class="news_list_normal_list">
                    <%
                        for (int i = 4; i < 16; i++) {
                            Topic topic = i < news.size() ? news.get(i) : news.get(news.size() - 1);
                    %>
                    <div class="news_list_normal">
                        <div class="img_box">
                            <a href="<%="/topic/topic.jsp?id="+topic.getId()%>">
                                <img src="<%=topic.getCoverImage()%>">
                            </a>
                        </div>
                        <div class="info_box">
                            <a href="<%="/topic/topic.jsp?id="+topic.getId()%>"><h2><%=topic.getTitle()%>
                            </h2></a>
                            <div class="fot_box"><%=topic.getAuthor().getName()%>
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <%
            }
        %>
        <%--<div class="news_box">
            <h2 class="vg_title">新闻资讯</h2>
            <div class="news_list">
                <div class="topic_list_first">
                    <div class="img_box">
                        <a href="<%="/topic/topic.jsp?id="+latestTopics.get(0).getId()%>">
                            <img src="https://img01.vgtime.com/game/cover/2020/04/13/200413132920414_u524.jpg">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="<%="/topic/topic.jsp?id="+latestTopics.get(0).getId()%>"><h2>
                            首款《半衰期：爱莉克斯》免VR游玩MOD来了</h2></a>
                        <p>当然体验上相比VR还是会大打折扣的。</p>
                        <div class="fot_box">苏活</div>
                    </div>
                </div>
                <div class="topic_list_big">
                    <div class="img_box">
                        <a href="#">
                            <img src="https://img01.vgtime.com/game/cover/2020/04/13/20041313353895_u2505.jpg">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="#"><h2>《生化危机3 重制版》发售五天全球累计出货量突破200万</h2></a>
                        <p>《生化危机2 重制版》累计出货突破650万。</p>
                        <div class="fot_box">沁雅畅慧</div>
                    </div>
                </div>
                <div class="topic_list_big">
                    <div class="img_box">
                        <a href="#">
                            <img src="https://img01.vgtime.com/game/cover/2020/04/13/20041313353895_u2505.jpg">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="#"><h2>《生化危机3 重制版》发售五天全球累计出货量突破200万</h2></a>
                        <p>《生化危机2 重制版》累计出货突破650万。</p>
                        <div class="fot_box">沁雅畅慧</div>
                    </div>
                </div>
                <div class="topic_list_big">
                    <div class="img_box">
                        <a href="#">
                            <img src="https://img01.vgtime.com/game/cover/2020/04/13/20041313353895_u2505.jpg">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="#"><h2>《生化危机3 重制版》发售五天全球累计出货量突破200万</h2></a>
                        <p>《生化危机2 重制版》累计出货突破650万。</p>
                        <div class="fot_box">沁雅畅慧</div>
                    </div>
                </div>
                <div class="news_list_normal_list">
                    <%for (int i = 0; i < 12; i++) {%>
                    <div class="news_list_normal">
                        <div class="img_box">
                            <a href="#">
                                <img src="https://img01.vgtime.com/game/cover/2020/04/13/20041313353895_u2505.jpg">
                            </a>
                        </div>
                        <div class="info_box">
                            <a href="#"><h2>《生化危机3 重制版》发售五天全球累计出货量突破200万</h2></a>
                            <div class="fot_box">沁雅畅慧</div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>--%>


        <%
            for (int i = 0; i < 4; i++) {
                String title;
                String type;
                switch (i) {
                    case 0:
                        title = "攻略资料";
                        type = "攻略";
                        break;
                    case 1:
                        title = "深度评测";
                        type = "评测";
                        break;
                    case 2:
                        title = "游戏文化";
                        type = "文化";
                        break;
                    case 3:
                        title = "动漫时光";
                        type = "动漫";
                        break;
                    default:
                        title = "";
                        type = "";
                        break;
                }
                List<Topic> topicList = topicDao.queryByConditions(null, null, type, "发布", null, "change_time DESC", 0L, 4L);
                if (topicList.size() > 0) {
        %>
        <div class="topic_box">
            <h2 class="vg_title"><%=title%>
            </h2>
            <div class="topic_list">
                <div class="topic_list_first">
                    <div class="img_box">
                        <a href="<%="/topic/topic.jsp?id="+topicList.get(0).getId()%>">
                            <img src="<%=topicList.get(0).getCoverImage()%>">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="<%="/topic/topic.jsp?id="+news.get(0).getId()%>"><h2><%=topicList.get(0).getTitle()%>
                        </h2></a>
                        <p><%=topicList.get(0).getResume()%>
                        </p>
                        <div class="fot_box"><%=topicList.get(0).getAuthor().getName()%>
                        </div>
                    </div>
                </div>
                <%
                    if (topicList.size() > 1) {
                        for (int j = 1; j < 4; j++) {
                            Topic topic = j < topicList.size() ? topicList.get(j) : topicList.get(topicList.size() - 1);
                %>
                <div class="topic_list_big">
                    <div class="img_box">
                        <a href="<%="/topic/topic.jsp?id="+topic.getId()%>">
                            <img src="<%=topic.getCoverImage()%>">
                        </a>
                    </div>
                    <div class="info_box">
                        <a href="<%="/topic/topic.jsp?id="+topic.getId()%>"><h2><%=topic.getTitle()%>
                        </h2></a>
                        <p><%=topic.getResume()%>
                        </p>
                        <div class="fot_box"><%=topic.getAuthor().getName()%>
                        </div>
                    </div>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
<%
    sqlSession.commit();
    sqlSession.close();
    in.close();
%>
