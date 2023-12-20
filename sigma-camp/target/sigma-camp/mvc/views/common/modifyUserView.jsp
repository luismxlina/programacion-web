
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Modificar  Usuario</title>
</head>
<!-- ACL -->
<%
	String returnPath = application.getInitParameter("index");

	if(User.getRol() == null ){
		%>
		<jsp:forward page="<%= returnPath %>">
		  <jsp:param name="ACL" value="Not allowed to go there" />
		</jsp:forward>
		<%
	}
	%>
<!-- ACL -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
  <body>
  <aside>
		<%if(User.getRol().equals("USER")){ %>
	    	<jsp:include page="/include/sidebar.jsp"></jsp:include>
		<%}else{ %>
	    	<jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
		<%} %>
    </aside>
	<main>
		<%if(User.getRol().equals("USER")){ %>
 			<jsp:include page="/include/header.jsp"></jsp:include>
		<%}else{ %>
  			<jsp:include page="/include/headerAdmin.jsp"></jsp:include>
		<%} %>
	   
    <div class="form-style-6">
    <%
	if(request.getParameter("ErrorModificacion") != null) {
    %>
		<p class="cajaRoja"> Error en la validacion del formulario </p>
	<%
	}
    %>
      <form id="formulario"  method="post" action= "<%=application.getInitParameter("modifyUserControllerr")%>">
        <div class="formulario__grupo" id="grupoUsuario">
            <input class="cajaBlanca" type="text" 	placeholder="Nombre" id="nombre" name="nombre">
        </div>
        <div class="formulario__grupo" id="grupoUsuario">
            <input class="cajaBlanca" type="text" 	placeholder="Apellidos" id="apellidos" name="apellidos">
        </div>
        <input class="cajaBlanca" type="password" placeholder="password" id="password" name="password"><br>
        <label>Fecha de nacimiento</label>
        <input class="cajaBlanca" type="date" placeholder="LocalDate" id="date" name="date"><br>
		<div class="user-box">
			<input type="checkbox" id="requiereAtencion" name="requiereAtencion" value="true">
			<label for="requiereAtencion">Requiere atención especial</label>
		</div>
        <input type="submit" id="submit" value="Submit"><br><br>
        <input type="reset" id="reset">
      </form> 
      
    </div>
	   
  </main>
  </body>
  <script src="${pageContext.request.contextPath}/js/script.js"></script>

