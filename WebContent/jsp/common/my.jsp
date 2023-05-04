<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="我的资料" name="pageTitle"/>
	</jsp:include>
	<!-- 官网全局样式 -->
	<link href="<%= currentPath %>/common/static/css/global.css?t=27601" rel="stylesheet">
</head>

<body>
	<div class="layui-layout layui-layout-admin">
		<%-- 导航栏 --%>
		<jsp:include page="/jsp/navigation.jsp">
			<jsp:param value="<%= role %>" name="role"/>
			<jsp:param value="my" name="panel"/>
		</jsp:include>
		
		<jsp:useBean id="adminIndex" scope="application" class="minuhy.xiaoxiang.lectopic.bean.admin.AdminIndexBean"></jsp:useBean>
		
		<%
		adminIndex.updateData();
		%>
		
	    <!-- 主内容 开始 -->
	    <div class="layui-body site-demo">
	        <div class="layui-main">
	            <!-- 主内容 内容 开始 -->
	            <!-- **************************************************************************************************** -->
				<div class="layui-row">
                    <div class="layui-col-md12">
                        <div class="layui-panel padding-30all text-c">
                            <img alt="<%= userBean.getNick() %>的头像" src="<%= currentPath + userBean.getAvatarPath() %>" width="120" height="120" class="img-circle img-thumbnail">
                            <div class="padding-20all">
                                <h2 class="font-30p text-over">
                                    <%= userBean.getAccount() %>
                                </h2>
                                <% if(userBean.getRole() == RoleConfig.STUDENT){ %>
                                	<h6 class="font-w">学生</h6>
					            <% }else if(userBean.getRole() == RoleConfig.TEACHER){ %>
                                	<h6 class="font-w">教师</h6>
					            <% }else if(userBean.getRole() == RoleConfig.ADMIN){ %>
                                	<h6 class="font-w">管理员</h6>
					            <% } %>
                            </div>
                            <p>上一次登录网络节点地址：<%= userBean.getLastLoginIp() %></p>
                            <p>上一次登录时间：<%= userBean.getLastLoginTime() %></p>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-md12">
                        <h1 class="font-30p margin-20v-10h ">基本资料</h1>
                    </div>
                </div>
                <div class="layui-panel padding-20all">
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 layui-col-xs text-c">
                            <p class="font-20p">账号</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">
                            	<%= userBean.getAccount() %>
                            </p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 layui-col-xs text-c">
                            <p class="font-20p">昵称</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">
                            	<%= userBean.getNick() %>
                            </p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">性别</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">
                            	<%= userBean.getSexStr() %>
                            </p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">邮箱</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">
                            	<%= userBean.getEmail() %>
                                <% if(userBean.getRole() == RoleConfig.STUDENT) { %>
                                	<span class="color-gray font-14p">（仅自己和老师可见）</span>
                                <% } %>
                            </p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">手机</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">
                            	<%= userBean.getPhone() %>
                                <% if(userBean.getRole() == RoleConfig.STUDENT) { %>
                                	<span class="color-gray font-14p">（仅自己和老师可见）</span>
                                <% } %>
                            </p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">签名</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">
                            	<%= userBean.getSignature() %>
                            </p>
                        </div>
                    </div>
                </div>

                <% if(userBean.getRole() == RoleConfig.STUDENT) { %>
					<div class="layui-row">
	                    <div class="layui-col-md12">
	                        <h1 class="font-30p margin-20v-10h">学生资料<span class="color-gray font-14p">（仅自己和老师可见）</span></h1>
	                    </div>
	                </div>
	                <div class="layui-panel padding-20all">
	                    <div class="layui-row margin-10all">
	                        <div class="layui-col-md3 layui-col-xs4 text-c">
	                            <p class="font-20p">学号</p>
	                        </div>
	                        <div class="layui-col-md9 layui-col-xs8">
	                            <p class="font-20p">175646413154566</p>
	                        </div>
	                    </div>
	                    <div class="layui-row margin-10all">
	                        <div class="layui-col-md3 layui-col-xs4 text-c">
	                            <p class="font-20p">姓名</p>
	                        </div>
	                        <div class="layui-col-md9 layui-col-xs8">
	                            <p class="font-20p">玉米子</p>
	                        </div>
	                    </div>
	                    
	                    
	                    <div class="layui-row margin-10all">
	                        <div class="layui-col-md3 layui-col-xs4 text-c">
	                            <p class="font-20p">院系</p>
	                        </div>
	                        <div class="layui-col-md9 layui-col-xs8">
	                            <p class="font-20p">信息学院</p>
	                        </div>
	                    </div>
	                    <div class="layui-row margin-10all">
	                        <div class="layui-col-md3 layui-col-xs4 text-c">
	                            <p class="font-20p">专业</p>
	                        </div>
	                        <div class="layui-col-md9 layui-col-xs8">
	                            <p class="font-20p">计算机应用</p>
	                        </div>
	                    </div>
	                    
	                    
	                    <div class="layui-row margin-10all">
	                        <div class="layui-col-md3 layui-col-xs4 text-c">
	                            <p class="font-20p">年级</p>
	                        </div>
	                        <div class="layui-col-md9 layui-col-xs8">
	                            <p class="font-20p">2017级</p>
	                        </div>
	                    </div>
	                    <div class="layui-row margin-10all">
	                        <div class="layui-col-md3 layui-col-xs4 text-c">
	                            <p class="font-20p">班级</p>
	                        </div>
	                        <div class="layui-col-md9 layui-col-xs8">
	                            <p class="font-20p">1班</p>
	                        </div>
	                    </div>
	                    
	                </div>
				<% } else if(userBean.getRole() == RoleConfig.TEACHER){ %>

                
                <div class="layui-row">
                    <div class="layui-col-md12">
                        <h1 class="font-30p margin-20v-10h">教师资料</h1>
                    </div>
                </div>
                <div class="layui-panel padding-20all">
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">工号</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">11245</p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">姓名</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">玉米子</p>
                        </div>
                    </div>
                    
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">职称</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">讲师</p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">院系</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">信息学院</p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">教研室</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">计双击压缩等级卡萨丁就应教研室</p>
                        </div>
                    </div>
                    <div class="layui-row margin-10all">
                        <div class="layui-col-md3 layui-col-xs4 text-c">
                            <p class="font-20p">个人简介</p>
                        </div>
                        <div class="layui-col-md9 layui-col-xs8">
                            <p class="font-20p">ui 之所以赢得如此多人的青睐，更多是在于它“前后界面兼备”的能力。既可编织出绚丽的前台页面，又可满足繁杂的管理系统的界面需求。
                                layui 管理基本布局， 致力于让每一位开发者都能轻松搭建自己的管理系统模板。</p>
                        </div>
                    </div>
                </div>

	    		<% } %>
	    		
			    <div class="layui-hide-xs"  style="margin: 15px 0 100px; padding-bottom: 100px;">
					<!-- 留白 -->
			    </div>
    
	            <!-- **************************************************************************************************** -->
	            <!-- 主内容 内容 结束 -->
	        </div>
	    </div>
	    <!-- 主内容 结束 -->
	
		<!-- 页脚 -->
		<jsp:include page="/jsp/foot.jsp"></jsp:include>
	</div>
</body>

</html>
