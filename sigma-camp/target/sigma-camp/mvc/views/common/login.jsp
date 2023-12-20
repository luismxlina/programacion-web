<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Iniciar Sesión</title>
</head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
  <body>
  
<!-- ACL -->
<%String aclNew = application.getInitParameter("aclNew"); %>
<jsp:include page="<%=aclNew%>"></jsp:include>
<!-- ACL -->

	
	<div class="login-box">
	  <h2>Iniciar Sesión</h2>
	   <form id="formulario" method="post" action= "${pageContext.request.contextPath}<%=application.getInitParameter("loginController")%>">  
	    	<div class="user-box">
	      		<input type="text" placeholder="Correo electrónico" name="email" id="email" >
	      		<label>email</label>
	    	</div>
	   	 	<div class="user-box">
	      		<input type="password" placeholder="Contraseña" name="password" id="password">
	     		<label>password</label>
	    	</div>
    		<div class="submit">
		      <span></span>
		      <span></span>
		      <span></span>
		      <span></span>
      		  <input class="sub" type="submit" id="submit" value="Iniciar Sesión">
			</div>
	    	
	  </form>
	  <div>
		<a class="sub" href="${pageContext.request.contextPath}<%= application.getInitParameter("registerController")%>">Registrarse</a>
	  </div>
	  	<%if(request.getParameter("Errorlogin") != null) {%>
			<p class="cajaRoja"> Email o contraseña incorrectas. </p>
	<%}	%>
	</div>



   </body>
  <script src="${pageContext.request.contextPath}/js/script.js"></script>
  <script src="${pageContext.request.contextPath}/js/login.js"></script>

</html>