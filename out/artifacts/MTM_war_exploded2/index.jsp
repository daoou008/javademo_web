<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <title>首页</title>
</head>
<body>
<div class="container" style="max-width: 500px" >
    <%--<a href="${pageContext.request.contextPath}/modStore" style="..."><h1>查询模块库存</h1></a>--%>
    <%--改造成首页为用户登录页面--%>
        <h3 style="text-align: center">管理员登录</h3>
        <form id="formUser" action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="inputUsername">用户名</label>
                <input type="text" name="username" class="form-control" id="inputUsername" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label for="inputPassword">密码</label>
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="请输入密码">
            </div>
            <hr/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">登录</button>
            </div>

            <%--显示出错信息--%>
            <div class="alert alert-warning alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert">
                    <span>x</span>
                </button>
                <strong>${login_msg}</strong>
            </div>
        </form>
</div>
</body>
</html>
