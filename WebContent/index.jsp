<%@ page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page import="minuhy.xiaoxiang.lectopic.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp" %>
<%
String nextUrl = currentPath + "/jsp/student/index.jsp";
if(userBean.getRole() == RoleConfig.TEACHER){
	nextUrl = currentPath + "/jsp/teacher/index.jsp";
}else if(userBean.getRole() == RoleConfig.ADMIN){ 
	nextUrl = currentPath + "/jsp/admin/index.jsp";
}
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="欢迎" name="pageTitle"/>
	</jsp:include>
</head>

<body>
    <div class="container">
       <h1 class="margin-10p-up text-center">欢迎</h1>
        <hr>
        <div class="hero-unit text-center">
            <img alt="用户头像" src="<%= currentPath + userBean.getAvatarPath() %>" width="100" height="100" class="img-circle">
            <h2><%= userBean.getNick() %></h2>
            <% 
            if(userBean.getRole() == RoleConfig.STUDENT){ 
            %>
	            <br>
	            <div>学号：</div>
	            <div>姓名：</div>
	            <div>班级：</div>
	            <div>年级：</div>
	            <div>专业：</div>
	            <div>院系：</div>
            <%
            }else if(userBean.getRole() == RoleConfig.TEACHER){ 
            %>
	            <br>
	            <p>姓名：</p>
	            <p>职称：</p>
	            <p>教研室：</p>
	            <p>院系：</p>
            <%
            }else if(userBean.getRole() == RoleConfig.ADMIN){ 
            %>
            	<p>管理员</p>
            <%
            } 
            %>
            <br>
            <div class="hidden-phone">账号：<%= userBean.getAccount() %></div>
            <div class="visible-phone">
	            <div>账号</div>
	            <div><%= userBean.getAccount() %></div>
            </div>
            <div class="hidden-phone">上一次登录时间：<%= userBean.getLastLoginTime() %></div>
            <div class="visible-phone">
	            <div>上一次登录时间</div>
	            <div><%= userBean.getLastLoginTime() %></div>
            </div>
            <div class="hidden-phone">上一次登录网点地址：<%= userBean.getLastLoginIp() %></div>
            <div class="visible-phone">
	            <div>上一次登录网点地址</div>
	            <div><%= userBean.getLastLoginIp() %></div>
            </div>
            <br>
            <p><a href="<%= nextUrl %>" class="btn btn-primary btn-large">继续</a></p>
		</div>
    </div> <!-- /container -->

	<!-- 页脚 -->
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
</body>

</html>
