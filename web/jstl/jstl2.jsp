<%@ page import="java.util.ArrayList" %>
<%@ page import="justdemo.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ListTest</title>
</head>
<body>
<%
    ArrayList<User> users = new ArrayList<>();
    users.add(new User("张三", 25, "男"));
    users.add(new User("李四", 24, "女"));
    users.add(new User("王五", 23, "男"));
    users.add(new User("赵六", 22, "女"));
    users.add(new User("洪七", 21, "男"));
    users.add(new User("腊八", 20, "女"));
    request.setAttribute("users", users);
%>

<table border="1" width="500" align="center">
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>
    <c:if test="${not empty requestScope.users}">
        <c:forEach items="${requestScope.users}" var="user" varStatus="s">
            <c:if test="${s.count % 2 == 1}">
                <tr style="background-color: pink">
            </c:if>
            <c:if test="${s.count % 2 == 0}">
                <tr style="background-color: cornflowerblue">
            </c:if>
                <td>${user.userName}</td>
                <td>${user.age}</td>
                <td>${user.gender}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
