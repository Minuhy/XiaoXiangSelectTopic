<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/admin/gateway.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="管理主页" name="pageTitle"/>
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
                        <div class="layui-panel padding-30all">
                            <h1 class="font-30p">管理首页</h1>
                            <div class="margin-10all">
                                <p class="p-2indent">欢迎您，系统管理员，你可以对系统中的所有数据进行修改。本页面展示一些系统统计信息。</p>
                                <p class="p-2indent">
                                    作为学生毕设系统管理员，需要与指导教师和学生进行沟通，解决毕设过程中出现的问题。还需要保证毕设资料的完整性和安全性，防止数据泄露或丢失。最后，还需要积极推动毕设工作的顺利进行，保证毕设工作的质量和进度，定期向指导教师和学生汇报毕设进展情况。
                                </p>
                                <p class="p-2indent">
                                    作为系统管理员，需要具备良好的文字表达能力、沟通能力、组织协调能力和团队合作精神，同时还需要具备较强的责任心、耐心和细心，以确保毕设工作的顺利进行。感谢您的支持和理解，遇到任何问题，请点击下方按钮联系我们。
                                </p>
                            </div>
                            <p class="text-r margin-20all">
                                <a id="linkWe" class="layui-btn">联系我们</a>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-md12 padding-30all">
                        <h1 class="font-30p margin-20down margin-10all">概览</h1>
                        <div class="layui-col-md3">
                            <div class="layui-panel margin-10all">
                                <div class="padding-30all text-c">
                                    <h2 class="font-40p">
                                    	<jsp:getProperty property="studentCount" name="adminIndex"/>
									</h2>
                                    <p class="font-16p">系统中学生总人数</p>
                                    <a class="layui-btn  layui-btn-primary" href="#">管理学生数据</a>
                                </div>
                            </div>
                        </div>
                         <div class="layui-col-md3">
                            <div class="layui-panel margin-10all">
                                <div class="padding-30all text-c">
                                    <h2 class="font-40p">
                                    	<jsp:getProperty property="teacherCount" name="adminIndex"/>
									</h2>
                                    <p class="font-16p">系统中教师总人数</p>
                                    <a class="layui-btn  layui-btn-primary" href="#">管理教师数据</a>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md3">
                            <div class="layui-panel margin-10all">
                                <div class="padding-30all text-c">
                                    <h2 class="font-40p">
                                    	<jsp:getProperty property="topicCount" name="adminIndex"/>
									</h2>
                                    <p class="font-16p">系统中选题总数量</p>
                                    <a class="layui-btn  layui-btn-primary" href="#">管理选题数据</a>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md3">
                            <div class="layui-panel margin-10all">
                                <div class="padding-30all text-c">
                                    <h2 class="font-40p">
                                    	<jsp:getProperty property="selectedCount" name="adminIndex"/>
									</h2>
                                    <p class="font-16p">系统中已选总数</p>
                                    <a class="layui-btn  layui-btn-primary" href="#">管理选题情况</a>
                                </div>
                            </div>
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
	</div>
</body>

</html>
