<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL常用标签</title>
</head>
<body>

<c:if test="true">
    <h1>我是真......</h1>
</c:if>

<hr>
<%
    ArrayList list = new ArrayList();
    list.add("aaaaa");
    list.add("bbbbb");
    list.add("ccccc");
    request.setAttribute("list", list);

    Calendar now = Calendar.getInstance();
    boolean ifFirstSunday = now.getFirstDayOfWeek() == Calendar.SUNDAY;
    int weekDay = now.get(Calendar.DAY_OF_WEEK);
    if (ifFirstSunday) {
        weekDay = weekDay - 1;
        if (weekDay == 0) {
            weekDay = 7;
        }
    }
    request.setAttribute("weekDay", weekDay);
%>

<c:if test="${not empty requestScope.list}">
    <c:forEach items="${requestScope.list}" var="str" varStatus="stu">
        ${stu.index}:${stu.count}:${str}<br>
    </c:forEach>
</c:if>

<hr>

<c:choose>
    <c:when test="${requestScope.weekDay == 1}"><h3>星期一</h3></c:when>
    <c:when test="${requestScope.weekDay == 2}"><h3>星期二</h3></c:when>
    <c:when test="${requestScope.weekDay == 3}"><h3>星期三</h3></c:when>
    <c:when test="${requestScope.weekDay == 4}"><h3>星期四</h3></c:when>
    <c:when test="${requestScope.weekDay == 5}"><h3>星期五</h3></c:when>
    <c:when test="${requestScope.weekDay == 6}"><h3>星期六</h3></c:when>
    <c:when test="${requestScope.weekDay == 7}"><h3>星期日</h3></c:when>
    <c:otherwise><h3>输出有误</h3></c:otherwise>
</c:choose>

</body>
</html>
