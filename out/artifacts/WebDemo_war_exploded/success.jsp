<%@ page import="justdemo.login.domain.User" %><%--
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
        User user = (User) request.getAttribute("user");
    %>
    <h1><%=user.getUsername()%>欢迎您!</h1>
</body>
</html>
