<%@ page import="justdemo.login.domain.User" errorPage="500.jsp" %><%--
  Created by IntelliJ IDEA.
  User: SJ
  Date: 2020/5/24
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
    <%
        /*User user = (User) request.getAttribute("user");*/
        User user = (User) request.getSession().getAttribute("user");
    %>
    <%--<h1><%=user.getUsername()%>欢迎您!</h1>--%>
    <h1>${sessionScope.user.username}欢迎您!</h1>
</body>
</html>
