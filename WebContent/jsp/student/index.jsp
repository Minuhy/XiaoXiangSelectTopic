<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/student/gateway.jsp" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans-CN">

<head>
	<jsp:include page="/jsp/head.jsp">
		<jsp:param value="主页" name="pageTitle"/>
	</jsp:include>
</head>

<body>
	<%-- 导航栏 --%>
	<jsp:include page="/jsp/navigation.jsp">
		<jsp:param value="student" name="role"/>
	</jsp:include>
	
	
	
    <div class="container">
       
    </div> <!-- /container -->

	<!-- 页脚 -->
	<jsp:include page="/jsp/foot.jsp"></jsp:include>
</body>

</html>
