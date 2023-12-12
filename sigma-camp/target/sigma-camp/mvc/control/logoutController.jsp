<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="display.userbean.userBean"%>
<jsp:useBean id="userBean" scope="session"
	class="display.userbean.userBean" />

<%
String nextPage = "";
String textNextPage = "";

if (userBean != null) {
	request.getSession().removeAttribute("userBean");
	userBean = null;
	nextPage = "../../index.jsp";
	textNextPage = "Logout correcto";
	request.getSession().invalidate();
}
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=textNextPage%>" name="mensaje" />
</jsp:forward>