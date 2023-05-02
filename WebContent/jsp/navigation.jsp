<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String currentPath = request.getContextPath(); %>
<%
String role = request.getParameter("role");
%>
<div class="navbar navbar-static-top margin-20down">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar btn-nav" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a style="text-align: center;" class="brand brand1 lead" href="<%= currentPath %>/index.jsp">
                <img src="<%= currentPath %>/common/lectopic/ico/apple-touch-icon-57-precomposed.gif" alt="潇湘毕设选题系统">
                潇湘毕设选题系统
            </a>
            <% if("student".equals(role)){ %>
            	<!-- 学生 -->
            <% }else if("teacher".equals(role)){ %>
            	<!-- 老师 -->
            <% }else if("admin".equals(role)){ %>
            	<!-- 管理员 -->
				<div class="nav-collapse in collapse">
	                <ul class="nav nav1">
	                    <li class="active"><a href="">首页</a></li>
	                    <li><a href="#about">学生管理</a></li>
	                    <li><a href="#contact">教师管理</a></li>
	                </ul>
	                <ul class="nav nav1 pull-right">
	                    <li><a href="#about">用户昵称</a></li>
	                    <li><a href="#about">设置</a></li>
	                    <li><a href="<%= currentPath %>/user/logout">退出</a></li>
	                </ul>
	            </div>
            <% } %>
        </div>
    </div>
</div>