<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento" %>

<!DOCTYPE html>
<html>
<head>
    <title>Campamentos Disponibles</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
<body>
    <!-- ACL -->
	<%String aclUser = application.getInitParameter("aclUser"); %>
	<jsp:include page="<%=aclUser%>"></jsp:include>
	<!-- ACL -->
	<aside>
        <jsp:include page="/include/sidebar.jsp"></jsp:include>
    </aside>
        <main>
    <jsp:include page="/include/header.jsp"></jsp:include>
    <div class="form-style-6">
    <% 
    String respuesta = (String) request.getAttribute("respuesta");
    if (respuesta != null && respuesta.equals("false")) { %>
        <h1>Introducir fechas</h1>
        <form class="search-form1"action="/sigma-camp/searchCampamentosFecha" method="GET">
            <label for="fechaInicio">Fecha de inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio">
            <label for="fechaFin">Fecha de fin:</label>
            <input type="date" id="fechaFin" name="fechaFin">
            <input type="submit" value="Buscar">
        </form>
    <% } else { %>
        <h1>Campamentos Disponibles</h1>
        <% 
        ArrayList<Campamento> campamentosInfantiles = (ArrayList<Campamento>) request.getAttribute("campamentosInfantiles");
        ArrayList<Campamento> campamentosJuveniles = (ArrayList<Campamento>) request.getAttribute("campamentosJuveniles");
        ArrayList<Campamento> campamentosAdolescentes = (ArrayList<Campamento>) request.getAttribute("campamentosAdolescentes");
        
        if (respuesta != null && respuesta.equals("empty")) { %>
            <p>No hay campamentos disponibles en las fechas seleccionadas.</p>
        <% } else { %>
            <% if (campamentosInfantiles != null && !campamentosInfantiles.isEmpty()) { %>
                <h2>Campamentos Infantiles</h2>
                <ul>
                    <% for (Campamento campamento : campamentosInfantiles) { %>
                        <li><%= campamento.getIdentificador() %> - <%= campamento.getNivel().toString() %></li>
                    <% } %>
                </ul>
            <% } %>
            
            <% if (campamentosJuveniles != null && !campamentosJuveniles.isEmpty()) { %>
                <h2>Campamentos Juveniles</h2>
                <ul>
                    <% for (Campamento campamento : campamentosJuveniles) { %>
                        <li><%= campamento.getIdentificador() %> - <%= campamento.getNivel().toString() %></li>
                    <% } %>
                </ul>
            <% } %>
            
            <% if (campamentosAdolescentes != null && !campamentosAdolescentes.isEmpty()) { %>
                <h2>Campamentos Adolescentes</h2>
                <ul>
                    <% for (Campamento campamento : campamentosAdolescentes) { %>
                        <li><%= campamento.getIdentificador() %> - <%= campamento.getNivel().toString() %></li>
                    <% } %>
                </ul>
            <% } %>
        <% } %>
    <% } %>
    <form class="search-form" action="/sigma-camp/calendarView" method="GET">
        <input type="submit" value="Volver al Calendario">
    </form>
    </div>
    </main>
</body>
</html>
