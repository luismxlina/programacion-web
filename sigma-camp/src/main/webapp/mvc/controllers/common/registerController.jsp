<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page import="es.uco.pw.business.users.handler.user.GestorUsuarios" %>
		<%@ page import="es.uco.pw.business.users.models.usuario.Usuario" %>
			<%@ page import="es.uco.pw.business.users.models.asistente.Asistente" %>
				<%@ page import="es.uco.pw.business.users.handler.asistente.GestorAsistentes" %>
						<%@ page import="java.time.LocalDate" %>
							<%@ page import="java.text.SimpleDateFormat" %>
								<%@ page import="java.text.ParseException" %>
									<%@ page import="java.util.Date" %>

									<jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean">
									</jsp:useBean>

									<!DOCTYPE html>
									<html>

									<head>
										<meta charset="UTF-8">
										<title>Loading...</title>
									</head>

									<body>
										<!-- ACL -->
										<%String aclNew=application.getInitParameter("aclNew"); %>
											<jsp:include page="<%=aclNew%>"></jsp:include>
											<!-- ACL -->


											<% String registerViewPath=application.getInitParameter("register"); 
											String indexViewPath=application.getInitParameter("index"); 
											
											String email=request.getParameter("email");
											String password=request.getParameter("password"); 
											String fechaNacimientoStr=request.getParameter("date"); 
											String nombre=request.getParameter("nombre"); 
											Integer id = 1;
											
											
											
											String apellidos=request.getParameter("apellidos"); 
											Boolean requiereAtencion = "true".equals(request.getParameter("requiereAtencion"));
											Date fechaNacimiento = new Date();
											
											LocalDate inscripcion = LocalDate.now(); 
											Boolean parseError = false;
											
											if (fechaNacimientoStr != null) {
												SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
												try {
													fechaNacimiento = formatter.parse(fechaNacimientoStr);
												} catch (ParseException e) {%>
													parseError = true;
												<%}
											}
											if (parseError) {
											%>
												<jsp:forward page="<%=registerViewPath%>" />
											<%
											}
											Asistente asistente = new Asistente(nombre, apellidos, fechaNacimiento, requiereAtencion);
											id = 1;
											id = GestorAsistentes.getInstance().altaAsistente(asistente, email);
											if(id == -1) {%>
												<jsp:forward page="<%=registerViewPath%>">
													<jsp:param name="ErrorRegister" value="true" />
												</jsp:forward>
											<%}
											
											String rol="USER";

											if( email==null ){ %>
												<jsp:forward page="<%=registerViewPath%>" />
											<% } if( fechaNacimiento==null){ %>
												<jsp:forward page="<%=registerViewPath%>">
													<jsp:param name="ErrorRegister" value="true" />
												</jsp:forward>
												<% } LocalDate nacimiento = LocalDate.parse(fechaNacimientoStr); 
												String nombreCompleto=nombre+" "+apellidos;
												Usuario register = new Usuario(nombreCompleto,nacimiento,inscripcion,email,id,password,rol);
													if( ! GestorUsuarios.getInstance().addUser(register)){ %>
													<jsp:forward page="<%=registerViewPath%>">

														<jsp:param name="ErrorRegister" value="true" />

													</jsp:forward>
													<% }else{ 
														int antiguedad=register.antiquity(); 
														boolean isMayorEdad=register.isMayorEdad(); %>

														<jsp:setProperty property="id" value="<%=id%>"
															name="User" />

														<jsp:setProperty property="email" value="<%=email%>"
															name="User" />

														<jsp:setProperty property="fechaNacimiento"
															value="<%=nacimiento%>" name="User" />

														<jsp:setProperty property="fechaIncripcion"
															value="<%=inscripcion%>" name="User" />

														<jsp:setProperty property="nombreCompleto"
															value="<%=nombreCompleto%>" name="User" />

														<jsp:setProperty property="rol" value="<%=rol%>"
															name="User" />

														<jsp:setProperty property="password" value="<%=password%>"
															name="User" />

														<jsp:setProperty property="mayorEdad"
															value="<%=register.isMayorEdad()%>" name="User" />

														<jsp:forward page="<%=indexViewPath%>" />
														<% } %>
					</body>

					</html>