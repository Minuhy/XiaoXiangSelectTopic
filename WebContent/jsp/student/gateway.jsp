<%@page import="minuhy.xiaoxiang.lectopic.config.RoleConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/user.jsp"%>
<%-- 验证学生权限的 --%>
<%
if(userBean.getRole() != RoleConfig.STUDENT){
	forwardInfoTipsPage("非学生账号，无法访问", UriConfig.INDEX);
	return;
}
%>