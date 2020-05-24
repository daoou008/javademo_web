<%--
  Created by IntelliJ IDEA.
  User: SJ
  Date: 2020/5/24
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>登录</title>
    <style>
        .emptyHeader {
            margin-top: 200px;
        }

        .checkCodeWidth {
            width: 50%;
            float: left;
        }
    </style>
    <title>Login</title>
</head>
<body>
    <form id="formUser" action="/login" class="form-horizontal emptyHeader" method="post">
        <div class="form-group">
            <label for="inputUsername" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-2">
                <input type="text" name="username" class="form-control" id="inputUsername" placeholder="用户名">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-2">
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="密码">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCheckCode" class="col-sm-2 control-label">验证码</label>
            <div class="col-sm-2">
                <input type="text" name="checkCode" class="form-control checkCodeWidth" id="inputCheckCode" placeholder="验证码">
                <img src="/checkCode">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button type="submit" class="btn btn-default">登录</button>
            </div>
        </div>
        <%= request.getAttribute("checkCodeError") != null ? request.getAttribute("checkCodeError"): ""%>
        <%= request.getAttribute("userError") != null ? request.getAttribute("userError"): ""%>
    </form>
</body>
</html>
