<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp"%>
<%
if(userBean.getRole()!=RoleConfig.ADMIN){
	response.sendRedirect(currentPath + UriConfig.INDEX);
	return;
}
%>