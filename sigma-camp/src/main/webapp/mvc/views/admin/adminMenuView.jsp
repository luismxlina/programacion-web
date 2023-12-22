<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.AbstractMap.SimpleEntry"%>
<%@ page import="es.uco.pw.business.campamento.models.campamento.Campamento"%>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menú de administrador</title>
</head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">
  <body>
  
  
<main>
  <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
  <aside>
    <jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
</aside>
  	<div class="form-style">
	<%	if(request.getParameter("ACL")!= null){ %>
				<p class="cajaRoja">Acceso denegado</p>	
	<%	}else if (request.getAttribute("ACL")!=null){ %>
				<p class="cajaRoja">Acceso denegado</p>
	<%	} %>

<% 	if(request.getAttribute("conteos") != null && request.getAttribute("campamentos") != null){
    HashMap<Integer, SimpleEntry<Integer, Integer>> conteos = (HashMap<Integer, SimpleEntry<Integer, Integer>>) request.getAttribute("conteos");
    ArrayList<Campamento> campamentos = (ArrayList<Campamento>) request.getAttribute("campamentos");
%>
<table border="1" class="miTabla">
    <thead>
        <tr>
            <th>Identificador</th>
            <th>Fecha de Inicio</th>
            <th>Fecha de Fin</th>
            <th>Nivel</th>
            <th>Máximo de Asistentes</th>
            <th>Asistentes a Tiempo Completo</th>
            <th>Asistentes a Tiempo Parcial</th>
        </tr>
    </thead>
    <tbody>
        <% for (Campamento campamento : campamentos) {
            SimpleEntry<Integer, Integer> conteo = conteos.get(campamento.getIdentificador());
            int numInscripcionesParciales = conteo != null ? conteo.getKey() : 0;
            int numInscripcionesCompletas = conteo != null ? conteo.getValue() : 0;
        %>
            <tr>
                <td><%= campamento.getIdentificador() %></td>
                <td><%= campamento.getFechaInicio() %></td>
                <td><%= campamento.getFechaFin() %></td>
                <td><%= campamento.getNivel() %></td>
                <td><%= campamento.getMaxAsistentes() %></td>
                <td><%= numInscripcionesCompletas %></td>
                <td><%= numInscripcionesParciales %></td>
            </tr>
        <% } %>
    </tbody>
</table>
<% } %>
  	</div>
  	
	</main>
  </body>

</html>