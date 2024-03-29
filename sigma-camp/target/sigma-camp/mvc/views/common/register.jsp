<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro</title>
</head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
  <body>
<!-- ACL -->
<%String aclNew = application.getInitParameter("aclNew"); %>
<jsp:include page="<%=aclNew%>"></jsp:include>
<!-- ACL -->
    

    <div class="login-box">
	  <h2>Registro</h2>
	   <form id="formulario"  method="post" action= "${pageContext.request.contextPath}<%=application.getInitParameter("registerController")%>">
	       <div class="user-box">
	     	<input type="text" 	placeholder="Nombre" id="nombre" name="nombre">
	      	<label>Nombre</label>
	    </div>
		<div class="user-box">
	     	<input type="text" 	placeholder="Apellidos" id="apellidos" name="apellidos">
			<label>Apellidos</label>
		</div>
	       
	    <div class="user-box">
	      <input type="text" placeholder="Correo electrónico" name="email" id="email" >
	      <label>Email</label>
	    </div>
	    <div class="user-box">
	      <input type="password" placeholder="Contraseña" name="password" id="password">
	      <label>Password</label>
	    </div>
	     <div class="user-box">
	      <input  type="date" placeholder="LocalDate" id="date" name="date">
	      <label>Fecha de Nacimiento</label>
	    </div>

		<div class="user-box" onclick="marcarCheckBox()">
			<label for="requiereAtencion" id="labelAtencion">¿Requiere atención especial?
				<input type="checkbox" id="requiereAtencion" name="requiereAtencion" checked="checked">
			</label>
		</div>
		<div class="user-box1">
		<div class="submit">
	      <span></span>
	      <span></span>
	      <span></span>
	      <span></span>
	      <input class="sub" type="submit" id="submit" value="Registrarse">
		</div>
		</div>
	
	  </form>
	  	<div class="user-box1">
		<div class="submit" id="boxLinkSubmit">
	  		<a class="sub" id="linkSubmit" href="${pageContext.request.contextPath}<%= application.getInitParameter("loginController")%>">Iniciar Sesión</a>
		</div>
		</div>
		    <%
	if(request.getParameter("ErrorRegister") != null) {
    %>
		<p class="cajaRoja"> Error en la validacion del formulario, email ya registrado </p>
	<%
	}
    %>
	</div>
    
  </body>
  <script>
	function marcarCheckBox(){
		var checkBox = document.getElementById("requiereAtencion");
		var label = document.getElementById("labelAtencion");
		if (checkBox.checked == true){
			checkBox.checked = ""
		} else {
			checkBox.checked = "checked"
		}
	}
  </script>
 <script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>