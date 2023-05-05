<%@page import="minuhy.xiaoxiang.lectopic.config.enums.MessageState"%>
<%@page import="minuhy.xiaoxiang.lectopic.util.TextUtil"%>
<%@page import="minuhy.xiaoxiang.lectopic.servlet.common.BaseServlet"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.ConstantsConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String currentPath = request.getContextPath();

String title = "";
String color = "";
String info = TextUtil.getString(request.getAttribute(ConstantsConfig.REQUEST_TIPS_INFO), "");
String uri = TextUtil.getString(request.getAttribute(ConstantsConfig.REQUEST_TIPS_URI), "");

Boolean isAutoSkip = false;

MessageState sta = MessageState.None;

Object obj = request.getAttribute(ConstantsConfig.REQUEST_TIPS_STATE);
if(obj instanceof MessageState){
	sta = (MessageState) obj;
}

obj = request.getAttribute(ConstantsConfig.REQUEST_TIPS_SKIP);
if(obj instanceof Boolean){
	isAutoSkip = (Boolean) obj;
}

switch(sta) {
	case SUCCESS:
		title = "成功";
		color = "msg-title-bg-success";
		break;
	case INFO:
		title = "信息";
		color = "msg-title-bg-info";
		break;
	case FAIL:
		title = "失败";
		color = "msg-title-bg-warning";
		break;
	default:
		title = "错误";
		color = "msg-title-bg-error";
		break;
}

%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="提示" name="pageTitle"/>
	</jsp:include>
	
    <link rel="stylesheet" href="<%= currentPath %>/common/lectopic/css/message.css">
</head>

<body>

	<!-- 中间布局 -->
    <div class="layui-container">
        <div class="layui-row">
            <div class="layui-col-md10 layui-col-md-offset1 msg-panel">
                <!-- 卡片视图 -->
                <div class="layui-card msg-card">
                    <div class="layui-card-header msg-title <%= color %>">
                        <h1><%= title %></h1>
                    </div>
                    <div class="layui-card-body msg-content">
                        <p><%= info %></p>
                    </div>
                    <a href="<%= uri %>" class="layui-btn">继续</a>
                    <% if(isAutoSkip){ %>
		            	<p class="color-gray auto-text">三秒后自动继续</p>
		            <% } %>
                </div>
                <!-- 卡片视图 -->
            </div>
        </div>
    </div>
    <!-- 中间布局 -->


	<!-- 自动跳转 -->
	<% if(isAutoSkip){ %>
		<script type="text/javascript">
			setTimeout(function() {  
				location.href = "<%= uri %>";
			}, 3000);
		</script>
	<% } %>
	
    
	<div class="padding-30all">
		<hr>
	</div>
	
	<!-- 页脚 -->
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
</body>

</html>
