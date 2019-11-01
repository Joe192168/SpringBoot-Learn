<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>" />
    <title>用户管理</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/static/layui/css/layui.css">
</head>
<body>
    <blockquote class="layui-elem-quote">
        <form action="" class="layui-form">
            <div class="layui-input-inline">
                <input type="text" name="search" required lay-verify="required" placeholder="请输入查询条件" autocomplete="off" class="layui-input"/>
            </div>
            <div class="layui-input-inline">
                <a href="javascript:;" class="layui-btn" lay-submit lay-filter="search-btn">查询</a>
                <a href="javascript:;" class="layui-btn layui-btn-normal add-btn">添加用户</a>
            </div>
        </form>
    </blockquote>

    <table id="userList" lay-filter="user-list"></table>

    <script type="text/javascript" src="static/layui/layui.js"></script>
    <script type="text/javascript" src="static/js/user.js" ></script>
    <script type="text/html" id="user-tool">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</body>
</html>
