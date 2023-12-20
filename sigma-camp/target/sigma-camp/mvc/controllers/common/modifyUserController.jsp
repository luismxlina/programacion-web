<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page import="es.uco.pw.business.users.handler.user.GestorUsuarios" %>
		<%@ page import="es.uco.pw.business.users.models.usuario.Usuario" %>
			<%@ page import="java.time.LocalDate" %>

				<jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">
					<title>Loading...</title>
				</head>

				<body>
					<% String modifyUserViewPath=application.getInitParameter("modifyUserView"); String
						indexViewPath=application.getInitParameter("index"); Integer id=User.getId(); String
						email=User.getEmail(); String rol=User.getRol(); LocalDate
						inscripcion=User.getFechaIncripcion(); String password=request.getParameter("password"); String
						fechaNacimiento=request.getParameter("date");; String
						nombreCompleto=request.getParameter("nombrecompleto"); if( password==null &&
						fechaNacimiento==null && nombreCompleto==null){ %>
						<jsp:forward page="<%=modifyUserViewPath%>" />
						<% } if(password==null){ password=User.getPassword(); } if(fechaNacimiento==null){
							fechaNacimiento=User.getFechaNacimiento().toString(); } if(nombreCompleto==null){
							nombreCompleto=User.getNombreCompleto(); } LocalDate
							nacimiento=LocalDate.parse(fechaNacimiento); Usuario usuario=new
							Usuario(nombreCompleto,nacimiento,inscripcion,email,id,password,rol); if( !
							UsuarioHandler.getInstance().editUser(usuario)){ %>
							<jsp:forward page="<%=modifyUserViewPath%>">
								<jsp:param name="ErrorModificacion" value="true" />
							</jsp:forward>
							<% }else{ %>
								<jsp:setProperty property="fechaNacimiento" value="<%=nacimiento%>" name="User" />
								<jsp:setProperty property="fechaIncripcion" value="<%=inscripcion%>" name="User" />
								<jsp:setProperty property="nombreCompleto" value="<%=nombreCompleto%>" name="User" />
								<jsp:setProperty property="password" value="<%=password%>" name="User" />
								<jsp:setProperty property="id" value="<%=password%>" name="User" />
								<jsp:setProperty property="antiguedad" value="<%=modify.antiquity()%>" name="User" />
								<jsp:setProperty property="mayorEdad" value="<%=modify.isMayorEdad()%>" name="User" />

								<jsp:forward page="<%=indexViewPath%>" />
								<% } %>
				</body>