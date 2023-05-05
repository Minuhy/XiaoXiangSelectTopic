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

obj = session.getAttribute(ConstantsConfig.SESSION_INDEX_PAGE);
if(obj instanceof Boolean && ((Boolean)obj)){
	response.sendRedirect(nextUrl);
	return;
}else{
	session.setAttribute(ConstantsConfig.SESSION_INDEX_PAGE,true);
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

    <div class="layui-container">
        <div class="layui-row text-c margin-20all">
            <h1 class="font-40p">欢迎</h1>
        </div>
        <div class="layui-row padding-5all">
            <div class="layui-col-md3">
                <div class="layui-panel margin-10all padding-20all text-c">
                    <img alt="<%= userBean.getNick() %>的头像" src="<%= currentPath + userBean.getAvatarPath() %>" width="120" height="120" class="img-circle img-thumbnail">
                    <div class="padding-20all">
                        <h2 class="font-30p text-over">
                        	<%= userBean.getNick() %>
                        </h2>
                    </div>
                </div>
            </div>
            <div class="layui-col-md9">
                <div class="layui-panel margin-10all padding-20all">
                    <ul class="layui-timeline">
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">基本信息</h3>
                                <% if(userBean.getRole() == RoleConfig.STUDENT){ %>
						            <div>学号：</div>
						            <div>姓名：</div>
						            <div>班级：</div>
						            <div>年级：</div>
						            <div>专业：</div>
						            <div>院系：</div>
					            <% }else if(userBean.getRole() == RoleConfig.TEACHER){ %>
						            <p>姓名：</p>
						            <p>职称：</p>
						            <p>教研室：</p>
						            <p>院系：</p>
					            <% }else if(userBean.getRole() == RoleConfig.ADMIN){ %>
					            	<p>管理员</p>
					            <% } %>
                            </div>
                        </li>
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">账号信息</h3>
                                <div>账号：<%= userBean.getAccount() %></div>
                                <div>上一次登录时间：<%= userBean.getLastLoginTime() %></div>
                                <div>上一次登录网点地址：<%= userBean.getLastLoginIp() %></div>
                            </div>
                        </li>
                        <li class="layui-timeline-item">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="layui-row text-c margin-20all">
            <a href="<%= nextUrl %>" class="layui-btn">继续</a>
            <p class="margin-20all color-gray">十秒后自动继续</p>
        </div>
    </div>
    
    <div class="layui-hide-xs"  style="margin: 15px 0 100px; padding-bottom: 100px;">
		<!-- 留白 -->
    </div>
    
	<div class="padding-30all">
		<hr>
	</div>
    
    <!-- 自动跳转 -->
	<script type="text/javascript">
		setTimeout(function() {  
			location.href = "<%= nextUrl %>";
		}, 10000);
	</script>

	<!-- 页脚 -->
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
</body>

</html>
