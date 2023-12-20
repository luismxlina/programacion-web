<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page import="es.uco.pw.business.users.handler.user.GestorUsuarios" %>
		<%@ page import="es.uco.pw.business.users.models.usuario.Usuario" %>
			<%@ page import="es.uco.pw.business.users.handler.asistente.GestorAsistentes" %>
					<%@ page import="java.time.LocalDate" %>
						<%@ page import="java.util.ArrayList" %>
							<%@ page import="java.util.HashSet" %>

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
										HashSet<Integer> idSet = new HashSet<>();
										ArrayList<Integer> ids = GestorAsistentes.getInstance().getAllIds();
										for (int i = 0; i < ids.size(); i++) { idSet.add(ids.get(i)); } 

										Random random=new Random(); 
										Integer id; 
										
										do { id=random.nextInt(1000000); }

										while (idSet.contains(id));
										idSet.add(id); 
										
										String password=request.getParameter("password"); 
										String fechaNacimiento=request.getParameter("date"); 
										String nombreCompleto=request.getParameter("nombrecompleto"); 
										LocalDate inscripcion=LocalDate.now(); 
										String rol="USER"; 
										if( email==null ){ %>
											<jsp:forward page="<%=registerViewPath%>" />
														<% } if( fechaNacimiento==null){ %>
															<jsp:forward page="<%=registerViewPath%>">
																<jsp:param name="ErrorRegister" value="true" />
															</jsp:forward>
															<% } LocalDate nacimiento = LocalDate.parse(fechaNacimiento); 
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

																	<jsp:setProperty property="antiguedad"
																		value="<%=register.antiquity()%>" name="User" />

																	<jsp:setProperty property="mayorEdad"
																		value="<%=register.isMayorEdad()%>" name="User" />

																	<jsp:forward page="<%=indexViewPath%>" />
																	<% } %>
								</body>

								</html>