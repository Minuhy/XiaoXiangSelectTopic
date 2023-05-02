<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String currentPath = request.getContextPath();%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="潇湘毕设选题系统 - 程序设计：信息学院计应教研室玉米子">
<meta name="author" content="玉米子">
<title><%= request.getParameter("pageTitle") + " - 毕设选题系统" %></title>

<!-- 引入样式 -->
<link href="<%= currentPath %>/common/bootstrap-2.3.2/css/bootstrap.css" rel="stylesheet">
<link href="<%= currentPath %>/common/bootstrap-2.3.2/css/bootstrap-responsive.css" rel="stylesheet">
<link href="<%= currentPath %>/common/lectopic/css/common.css" rel="stylesheet">

<!-- HTML5填充程序，用于IE6-8对HTML5元素的支持 -->
<!--[if lt IE 9]>
    <script src="<%= currentPath %>/common/html5shiv-3.6.2pre/html5shiv.js"></script>
<![endif]-->

<!-- 图标 -->
<link rel="shortcut icon" href="<%= currentPath %>/common/lectopic/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%= currentPath %>/common/lectopic/ico/apple-touch-icon-144-precomposed.gif">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%= currentPath %>/common/lectopic/ico/apple-touch-icon-114-precomposed.gif">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%= currentPath %>/common/lectopic/ico/apple-touch-icon-72-precomposed.gif">
<link rel="apple-touch-icon-precomposed" href="<%= currentPath %>/common/lectopic/ico/apple-touch-icon-57-precomposed.gif">

