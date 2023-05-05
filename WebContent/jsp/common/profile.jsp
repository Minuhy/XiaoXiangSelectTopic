<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="修改基本资料" name="pageTitle"/>
	</jsp:include>
	<!-- 官网全局样式 -->
	<link href="<%= currentPath %>/common/static/css/global.css?t=27601" rel="stylesheet">
	
	<!-- 本页样式 -->
    <link rel="stylesheet" href="<%= currentPath %>/common/lectopic/css/profile.css">
</head>

<body>
	<div class="layui-layout layui-layout-admin">
		<%-- 导航栏 --%>
		<jsp:include page="/jsp/navigation.jsp">
			<jsp:param value="<%= role %>" name="role"/>
			<jsp:param value="my" name="panel"/>
			<jsp:param value="3" name="curPage"/>
		</jsp:include>
				
	    <!-- 主内容 开始 -->
	    <div class="layui-body site-demo">
	        <div class="layui-main">
	            <!-- 主内容 内容 开始 -->
	            <!-- **************************************************************************************************** -->
				
				<div class="layui-row">
                    <div class="layui-col-md12">
                        <div class="layui-panel padding-30all text-c">
                            <img id="userAvatarImg" alt="<%= userBean.getNick() %>的头像" src="<%= currentPath + userBean.getAvatarPath() %>"
                                width="120" height="120" class="img-circle img-thumbnail">

                            <div class="padding-20all">
                                <div id="chooseAvatar" data-method="chooseAvatar" class="layui-btn layui-btn-primary">
                                    更换头像</div>
                            </div>

                            <form class="layui-form layui-form-pane" action="<%= currentPath %>/user/profile" method="post">
                                <!-- 头像 -->
                            	<input 
	                            	value="<%= userBean.getAvatar() %>" 
	                            	id="avatarInput" 
	                            	name="avatar" 
	                            	type="hidden">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">昵称</label>
                                    <div class="layui-input-block">
                                        <input 
                                        	value="<%= userBean.getNick() %>"
                                        	type="text" 
                                        	name="nick" 
                                        	required 
                                        	placeholder="请设置昵称"
                                            autocomplete="off" 
                                            class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">性别</label>
                                    <div class="layui-input-block">
                                        <select name="sex" title="请设置性别">
                                            <option value="0" <%= ((userBean.getSex()!=1)&&(userBean.getSex()!=2))?"selected":"" %>>保密</option>
                                            <option value="1" <%= (userBean.getSex()==1)?"selected":"" %>>男</option>
                                            <option value="2" <%= (userBean.getSex()==2)?"selected":"" %>>女</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">邮箱</label>
                                    <div class="layui-input-block">
                                        <input 
                                        	value="<%= userBean.getEmail() %>"
	                                        type="text" 
	                                        name="email" 
	                                        placeholder="请设置邮箱" 
	                                        autocomplete="off"
                                            class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">手机</label>
                                    <div class="layui-input-block">
                                        <input 
                                        	value="<%= userBean.getPhone() %>"
                                        	type="text" 
                                        	name="phone" 
                                        	placeholder="请设置手机号"
                                            autocomplete="off" 
                                            class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item layui-form-text">
                                    <label class="layui-form-label">签名</label>
                                    <div class="layui-input-block">
                                        <textarea 
                                        	name="signature" 
                                        	placeholder="请设置签名" 
                                        	class="layui-textarea"><%= userBean.getSignature() %></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <input type="submit" class="layui-btn" value="保存基本资料">
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                        
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
		
		<!-- 本页JS -->
		<script src="<%= currentPath %>/common/lectopic/js/profile.js"></script>
	</div>
</body>

</html>
