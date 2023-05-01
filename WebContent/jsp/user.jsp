<%@page import="minuhy.xiaoxiang.lectopic.config.enums.MessageState"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.UriConfig"%>
<%@page import="minuhy.xiaoxiang.lectopic.config.ConstantsConfig"%>
<%@page import="minuhy.xiaoxiang.lectopic.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String currentPath = request.getContextPath();
UserBean userBean = null;
Object obj = session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);
if(obj instanceof UserBean){
	userBean = (UserBean)obj;
}else{
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_STATE, MessageState.INFO);
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_INFO, "请登录后再访问");
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_URI, currentPath + UriConfig.LOGIN);
	request.setAttribute(ConstantsConfig.REQUEST_TIPS_SKIP, true);
	request.getRequestDispatcher(UriConfig.MESSAGE).include(request, response);
	return;
}
%>