<%@ page import="minuhy.xiaoxiang.lectopic.config.enums.MessageState"%>
<%@ page import="minuhy.xiaoxiang.lectopic.config.UriConfig"%>
<%@ page import="minuhy.xiaoxiang.lectopic.config.ConstantsConfig"%>
<%@ page import="minuhy.xiaoxiang.lectopic.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 本JSP做用户认证，包含本JSP则只有登录的用户才可以访问，本JSP同时包含base.jsp --%>
<%@ include file="/jsp/base.jsp" %>
<%
String currentPath = request.getContextPath();
UserBean userBean = null;
Object obj = session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);
if(obj instanceof UserBean && ((UserBean) obj).getId() > 0){
	userBean = (UserBean)obj;
}else{
	forwardInfoTipsPage("请登录后再访问", UriConfig.LOGIN);
	return;
}
%>