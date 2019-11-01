<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>添加用户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/static/layui/css/layui.css">
    <style>
        .sys-add-edit{ padding-top: 25px;text-align: center; }
        .sys-input-size{width: 380px}
    </style>
</head>
<body class="layui-container sys-add-edit">
    <form class="layui-form">
        <c:if test="${!empty user.id}">
            <div class="layui-form-item">
                <div class="layui-form-label">用户编号:</div>
                <div class="layui-input-block">
                    <input class="layui-input layui-disabled sys-input-size" name="id" readonly value="${user.id}" />
                </div>
            </div>
        </c:if>
        <div class="layui-form-item">
            <div class="layui-form-label">用户名称:</div>
            <div class="layui-input-block">
                 <input class="layui-input sys-input-size" placeholder="请输入用户名称" required lay-verify="required" name="username" value="${user.username}" />
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-form-label">用户密码:</div>
            <div class="layui-input-block">
                <input class="layui-input sys-input-size" placeholder="请输入用户密码" required lay-verify="required" name="password" value="${user.password}" />
            </div>
        </div>
        <div class="layui-form-item">
            <c:choose>
                <c:when test="${!empty user.id}">
                    <button class="layui-btn" lay-submit lay-filter="edit-submit">修改</button>
                </c:when>
                <c:otherwise>
                    <button class="layui-btn" lay-submit lay-filter="add-submit">添加</button>
                </c:otherwise>
            </c:choose>
            <input type="reset" class="layui-btn layui-btn-normal" value="重置" />
        </div>
    </form>

    <script type="text/javascript" src="/static/layui/layui.js"></script>
    <script type="text/javascript" src="/static/js/user.js" ></script>

</body>
</html>
