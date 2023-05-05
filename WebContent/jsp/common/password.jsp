<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="修改登录密码" name="pageTitle"/>
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
			<jsp:param value="6" name="curPage"/>
		</jsp:include>
				
	    <!-- 主内容 开始 -->
	    <div class="layui-body site-demo">
	        <div class="layui-main">
	            <!-- 主内容 内容 开始 -->
	            <!-- **************************************************************************************************** -->
				
				 <div class="layui-row">
                    <div class="layui-panel padding-30all text-c">
                        <form class="layui-form layui-form-pane" action="<%= currentPath %>/user/password" method="post">
                            <div class="layui-form-item">
                                <label class="layui-form-label">账号</label>
                                <div class="layui-input-block">
                                    <input value="<%= userBean.getAccount() %>" type="text" readonly autocomplete="off" 
                                    	class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">原密码</label>
                                <div class="layui-input-block">
                                    <input type="password" name="rawPassword" autocomplete="off" placeholder="请输入原密码"
                                        class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">新密码</label>
                                <div class="layui-input-block">
                                    <input type="password" name="newPassword" autocomplete="off" placeholder="请设置新密码"
                                        class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">确认新密码</label>
                                <div class="layui-input-block">
                                    <input type="password" name="renewPassword" autocomplete="off" placeholder="请确认新密码"
                                        class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item margin-20all">
                                <input type="submit" class="layui-btn" value="确认修改登录密码">
                            </div>
                        </form>
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
	</div>
</body>

</html>
