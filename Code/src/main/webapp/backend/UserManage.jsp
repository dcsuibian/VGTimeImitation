<%@ page import="com.dcsuibian.domain.User" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.dcsuibian.service.IUserService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dcsuibian
  Date: 2020/4/2
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    IUserService userService = ac.getBean("userService", IUserService.class);
    List<User> users = userService.findAll();

    User currentLoginedUser = (User) session.getAttribute("user");
%>
<html>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/backend/UserManage.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="/js/dcsuibian.js"></script>
    <script src="/js/backend/UserManage.js"></script>
    <title>人员管理</title>
</head>
<body>
<div class="container">
    <div class="col-12">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>用户名</th>
                <th>身份</th>
            </tr>
            </thead>
            <tbody>
            <%for (User user : users) {
                if("root".equals(user.getRole())) {
                    continue;
                }
            %>
            <tr>
                <td><a href="#" data-toggle="modal" data-target="#userModal"><%=user.getId()%>
                </a>
                </td>
                <td><%=user.getName()%>
                    </a>
                </td>
                <td><%=user.getRole()%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<div class="modal fade" id="userModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">模态框头部</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <p></p>
                <select>
                    <%
                        if ("root".equals(currentLoginedUser.getRole())) {
                            out.println("<option value=\"总编\">总编</option>\n" +
                                    "                    <option value=\"副总编\">副总编</option>");
                        }else if("总编".equals(currentLoginedUser.getRole())){
                            out.println("<option value=\"副总编\">副总编</option>");
                        }
                    %>
                    <option value="编辑">编辑</option>
                    <option value="作者">作者</option>
                    <option value="普通用户">普通用户</option>
                </select>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="btn_change">确定</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>
</body>
</html>
