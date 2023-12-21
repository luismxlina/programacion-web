<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="es.uco.pw.business.users.handler.user.GestorUsuarios" %>
<%@ page import="es.uco.pw.business.users.handler.asistente.GestorAsistentes" %>
<%@ page import="es.uco.pw.business.users.models.usuario.Usuario" %>
<%@ page import="es.uco.pw.business.users.models.asistente.Asistente" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Date" %>

<jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Loading...</title>
</head>
<body>
    <% 
        String modifyUserViewPath=application.getInitParameter("modifyUserView");
        String indexViewPath=application.getInitParameter("index");
        Integer id=User.getId();
        String email=User.getEmail(); 
        String rol=User.getRol(); 
        LocalDate inscripcion=User.getFechaIncripcion(); 
        String password=request.getParameter("password"); 
        String fechaNacimientoStr=request.getParameter("date");;
        String nombre=request.getParameter("nombre"); 
        String apellidos=request.getParameter("apellidos");
		String nombreCompleto= nombre + " " + apellidos;
        Date fechaNacimiento = new Date();
		Boolean requiereAtencion = "true".equals(request.getParameter("requiereAtencion"));
        
        if( password==null && fechaNacimientoStr==null && nombre==null && apellidos==null){ 
    %>
        <jsp:forward page="<%=modifyUserViewPath%>" />
    <% } 

    if(password==null){ 
        password=User.getPassword(); 
    } 
    
    if(fechaNacimientoStr==null){
        fechaNacimientoStr=User.getFechaNacimiento().toString(); 
    }
    
    if(nombreCompleto==null){
        nombreCompleto=User.getNombreCompleto(); 
    }
    
    LocalDate nacimiento=LocalDate.parse(fechaNacimientoStr);
    Usuario usuario=new Usuario(nombreCompleto,nacimiento,inscripcion,email,id,password,rol);
	Asistente asistente = GestorAsistentes.getInstance().getAsistente(id);
	Boolean parseError = false;
	if (fechaNacimientoStr != null) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fechaNacimiento = formatter.parse(fechaNacimientoStr);
		} catch (ParseException e) {%>
			parseError = true;
		<%}
	}
	asistente.setNombre(nombre);
	asistente.setApellidos(apellidos);
	asistente.setFechaNacimiento(fechaNacimiento);
	asistente.setRequiereAtencion(requiereAtencion);
    
    if( !GestorUsuarios.getInstance().editUser(usuario) && !GestorAsistentes.getInstance().updateAsistente(asistente)){ 
    %>
        <jsp:forward page="<%=modifyUserViewPath%>">
            <jsp:param name="ErrorModificacion" value="true" />
        </jsp:forward>
    <% } else { %>
        <jsp:setProperty property="fechaNacimiento" value="<%=nacimiento%>" name="User" />
        <jsp:setProperty property="fechaIncripcion" value="<%=inscripcion%>" name="User" />
        <jsp:setProperty property="nombreCompleto" value="<%=nombreCompleto%>" name="User" />
        <jsp:setProperty property="password" value="<%=password%>" name="User" />
        <jsp:setProperty property="id" value="<%=id%>" name="User" />
        <jsp:setProperty property="mayorEdad" value="<%=usuario.isMayorEdad()%>" name="User" />

        <jsp:forward page="<%=indexViewPath%>" />
    <% } %>
</body>
</html>
