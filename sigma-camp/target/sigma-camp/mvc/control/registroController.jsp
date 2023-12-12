<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="display.userbean.userBean" %>

<jsp:useBean  id="userBean" scope="session" class="display.userbean.userBean"></jsp:useBean>

<%

if(userBean!=null)
{
	//Cogemos los parámetros del formulario de registro
	//Creamos un objeto DTO con todos esos parámetros de usuario
	//se lo pasamos a un dao que lo inserta en la BD
	
}
%>