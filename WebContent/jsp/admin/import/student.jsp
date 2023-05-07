<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/admin/gateway.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="学生资料导入" name="pageTitle"/>
	</jsp:include>
	<!-- 官网全局样式 -->
	<link href="<%= currentPath %>/common/static/css/global.css?t=27601" rel="stylesheet">
</head>

<body>
	<div class="layui-layout layui-layout-admin">
		<%-- 导航栏 --%>
		<jsp:include page="/jsp/navigation.jsp">
			<jsp:param value="<%= role %>" name="role"/>
			<jsp:param value="<%= role %>" name="panel"/>
			<jsp:param value="3.1" name="curPage"/>
		</jsp:include>
				
	    <!-- 主内容 开始 -->
	    <div class="layui-body site-demo">
	        <div class="layui-main">
	            <!-- 主内容 内容 开始 -->
	            <!-- **************************************************************************************************** -->
				
				 
                        
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
