<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String currentPath = request.getContextPath();%>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="潇湘毕设选题系统 - 程序设计：信息学院计应教研室">
<meta name="author" content="玉米子">

<script>
	; !function () { self !== parent && (location.href = "about:blank") }();
</script>

<title><%= request.getParameter("pageTitle") + " - 毕设选题系统" %></title>

<!-- 引入样式 -->
<link rel="stylesheet" href="<%= currentPath %>/common/layui-2.7.6/css/layui.css">
<link rel="stylesheet" href="<%= currentPath %>/common/lectopic/css/common.css">

<!-- 图标 -->
<link rel="shortcut icon" href="<%= currentPath %>/common/lectopic/favicon/favicon.ico">

