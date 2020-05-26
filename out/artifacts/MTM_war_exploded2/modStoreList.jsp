<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <title>查询模块库存</title>
</head>
<body>

<div id="divWelcome">
    ${sessionScope.user.name},欢迎您
</div>
<div id="div1" class="container">
    <h3>模块库存信息</h3>
    <table class="table table-hover table-striped table-bordered">
        <tr class="success">
            <th>模块类型</th>
            <th>已测功能"返库"</th>
            <th>数量</th>
        </tr>
        <c:if test="${not empty requestScope.modStores}">
            <c:forEach items="${requestScope.modStores}" var="modStore">
                <tr>
                    <td>${modStore.type}</td>
                    <td>${modStore.description}</td>
                    <td>${modStore.quantity}</td>
                </tr>
            </c:forEach>
            <tr class="warning">
                <td colspan="2" style="text-align: right">总计</td>
                <td>${requestScope.quantity_sum}</td>
            </tr>
        </c:if>
    </table>
</div>

<div id="div2" class="container">
    <h3>客户信息</h3>
    <table class="table table-hover table-striped table-bordered">
        <tr class="success">
            <th>客户名称</th>
            <th>客户详情</th>
            <th>联系人</th>
            <th>电话</th>
            <th>地址</th>
        </tr>
        <c:if test="${not empty requestScope.customers}">
            <c:forEach items="${requestScope.customers}" var="customer">
                <tr>
                    <td>${customer.name}</td>
                    <td>${customer.description}</td>
                    <td>${customer.contact}</td>
                    <td>${customer.tel}</td>
                    <td>${customer.address}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<div id="div3" class="container">
    <h3>客户可用库存信息</h3>
    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/modStore" method="post">
            <div class="form-group">
                <label for="inputCustomerName">客户</label>
                <input type="text" name="cusName" value="${cusName}" class="form-control" id="inputCustomerName">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <table class="table table-hover table-striped table-bordered">
        <tr class="success">
            <th>客户名称</th>
            <th>模块型号</th>
            <th>需要功能</th>
            <th>预计合格率</th>
            <th>可选库存</th>
            <th>预计可用库存</th>
        </tr>
        <c:forEach items="${requestScope.pb.list}" var="cusModFunction">
            <tr>
                <td>${cusModFunction.name}</td>
                <td>${cusModFunction.type}</td>
                <td>${cusModFunction.description}</td>
                <td>${cusModFunction.prepass * 100}%</td>
                <td>${cusModFunction.alternative}</td>
                <td>${Math.round(cusModFunction.prepass * cusModFunction.alternative)}</td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage <= 1}">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage > 1}">
                    <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/modStore?currentPage=${pb.currentPage - 1}&row=3&cusName=${cusName}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/modStore?currentPage=${i}&row=3&cusName=${cusName}">${i}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/modStore?currentPage=${i}&row=3&cusName=${cusName}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${pb.currentPage >= pb.totalPage}">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage < pb.totalPage}">
                    <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/modStore?currentPage=${pb.currentPage + 1}&row=3&cusName=${cusName}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px; margin-left: 5px">
                        共条${requestScope.pb.totalCount}记录，共${requestScope.pb.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
</div>

</body>
</html>
