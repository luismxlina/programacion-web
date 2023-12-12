<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@page import="display.userbean.userBean"%>

<jsp:useBean id="userBean" class="display.userbean.userBean"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestor de campamentos</title>
</head>
<body>
	<% 
	if(userBean != null)
	{
		%>
		<form action="/Introduccion_practica_3/mvc/control/logoutController.jsp"
			method="post">
			<p>
				<input type="submit" value="Logout">
			</p>
		</form>
		<%
	}
	if(userBean == null || userBean.getNombre().equals("")) { 
	%>
	<h1>Bienvenido al Gestor de campamentos Campamentos</h1>
	<fieldset>
		<form action="/Introduccion_practica_3/mvc/control/loginController.jsp"
			method="post">
			<label for="correo">Correo electrónico:</label><br> <br /> <input
				type="email" name="correo" placeholder="example@gmail.com" required><br>
			<br /> <label for="passWord">Contraseña:</label><br> <br /> <input
				type="password" name="passWord" placeholder="contraseña" required><br>
			<br />
			<p>
				<input type="submit" value="Iniciar sesi&oacute;n">
			</p>
		</form>
		<form action="/Introduccion_practica_3/mvc/control/registroController.jsp"
			method="post">
			<p>
				<input type="submit" value="Registrarse">
			</p>
		</form>
	</fieldset>
	<% } else { 
	 %>
	<p>Login realizado correctamente</p>
	<% } %>

</body>
</html>