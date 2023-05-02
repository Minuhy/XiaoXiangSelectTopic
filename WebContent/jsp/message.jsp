<%@page import="minuhy.xiaoxiang.lectopic.config.enums.MessageState"%>
<%@page import="minuhy.xiaoxiang.lectopic.util.TextUtil"%>
<%@page import="minuhy.xiaoxiang.lectopic.servlet.common.BaseServlet"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.ConstantsConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
		color = "text-success";
		break;
	case INFO:
		title = "信息";
		color = "text-info";
		break;
	case FAIL:
		title = "失败";
		color = "text-warning";
		break;
	default:
		title = "错误";
		color = "text-error";
		break;
}

%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="提示" name="pageTitle"/>
	</jsp:include>
</head>

<body>
    <div class="container margin-5p-up" >
    	<h1 class="padding-bottom text-center <%= color %>"><%= title %></h1>
        <div class="hero-unit text-center">
            <h2 class="padding-bottom"><%= info %></h2>
            <p>
                <a href="<%= uri %>" class="btn btn-primary btn-large margin-up">继续</a>
            </p>
            <% if(isAutoSkip){ %>
            	<p>三秒后自动继续</p>
            <% } %>
        </div>
    </div> <!-- /container -->

	<!-- 自动跳转 -->
	<% if(isAutoSkip){ %>
		<script type="text/javascript">
			setTimeout(function() {  
				location.href = "<%= uri %>";
			}, 3000);
		</script>
	<% } %>
	
	<!-- 页脚 -->
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
</body>

</html>
