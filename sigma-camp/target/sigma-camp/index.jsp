<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@page import="es.uco.pw.business.inscripcion.handler.GestorInscripciones" %>
		<%@page import="es.uco.pw.business.users.handler.user.GestorUsuarios" %>
			<%@page import="es.uco.pw.business.users.models.usuario.Usuario" %>
				<%@page import="java.time.LocalDate" %>
					<%@page import="java.time.LocalDateTime" %>

						<jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean">
						</jsp:useBean>

						<!DOCTYPE html>
						<html>

						<head>
							<meta charset="UTF-8">
							<title>PW</title>
						</head>
						<link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
						<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">

						<body>
							<div class="login-box">
								<% if(request.getParameter("ACL")!=null){ %>
									<p class="cajaRoja">Acceso denegado></p>
									<% }else if (request.getAttribute("ACL")!=null){ %>
										<p class="cajaRoja">Acceso denegado</p>
										<% } if(User.getEmail()==null || User.getRol()==null){ %>
											<h2> Campamento de verano</h2>
											<h3 style="text-align:center;"> <img src="/sigma-camp/images/sun-color-icon.svg"
													class="bi d-block mx-auto mb-1" width="24"
													height="24"></h3>
											<div class="user-box">
												<div class="submit">
													<span></span>
													<span></span>
													<span></span>
													<span></span>
													<a href="${pageContext.request.contextPath}<%=application.getInitParameter("registerController")%>">Registrarse</a>
												</div>
											</div>
											<div class="user-box">
												<div class="submit">
													<span></span>
													<span></span>
													<span></span>
													<span></span>
													<a href="${pageContext.request.contextPath}<%=application.getInitParameter("loginController")%>">Iniciar Sesión</a>
												</div>
											</div>
											<% }else if(User.getRol().equals("ADMIN")){ 
												String adminMenu=application.getInitParameter("adminMenuController"); %>
												<jsp:forward page="<%=adminMenu%>" />
												<% }else if(User.getRol().equals("USER")){ 
													String userMenu=application.getInitParameter("userMenuController"); %>
													<jsp:forward page="<%=userMenu%>" />
													<% } %>
							</div>

						</body>

						</html>