<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@page import="es.uco.pw.business.inscripcion.handler.GestorInscripciones" %>
		<%@ page import="es.uco.pw.business.users.handler.user.GestorUsuarios" %>
			<%@ page import="es.uco.pw.business.users.models.usuario.Usuario" %>
				<%@page import="java.time.LocalDate" %>
					<%@page import="java.time.LocalDateTime" %>
						<%@page import="java.util.ArrayList" %>
							<%@page import="es.uco.pw.business.campamento.models.campamento.Campamento" %>

						<jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean">
						</jsp:useBean>

						<!DOCTYPE html>
						<html>

						<head>
							<meta charset="UTF-8">
							<title>Menú de usuario</title>
						</head>
						<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
						<link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">

						<body>
							<!-- ACL -->
							<%String aclUser=application.getInitParameter("aclUser"); %>
								<jsp:include page="<%=aclUser%>"></jsp:include>
								<!-- ACL -->

								<aside>
									<jsp:include page="/include/sidebar.jsp"></jsp:include>
								</aside>
								<main>
									<jsp:include page="/include/header.jsp"></jsp:include>
									<div class="form-style-6">

										<p>Bienvenido
											<jsp:getProperty property="nombreCompleto" name="User" />
										</p>
										<p>
											Hoy es <%=request.getAttribute("fechaActual")%>
										</p>
										<p>Tu antiguedad es:
											<jsp:getProperty property="antiguedad" name="User" /> años
										</p>

										<% if(request.getAttribute("campamentos") != (null)){
											ArrayList<Campamento> campamentos = (ArrayList<Campamento>) request.getAttribute("campamentos");
											for (Campamento campamento : campamentos) {
												%>
												<p>
													Identificador: <%= campamento.getIdentificador() %>
												</p>
												<p>
													Fecha de inicio: <%= campamento.getFechaInicio() %>
												</p>
													<%}
											} %>

												<% if(request.getParameter("ACL")!=null){ %>
													<p class="cajaRoja">Acceso denegado</p>
													<% }else if (request.getAttribute("ACL")!=null){ %>
														<p class="cajaRoja">Acceso denegado</p>

														<% } %>
									</div>
									<jsp:include page="/include/footer.html"></jsp:include>
								</main>
						</body>

						</html>