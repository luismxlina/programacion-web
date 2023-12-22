<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento" %>
<jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Campamentos</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/estilos.css">ç
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/buscarCampamentos.css">
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
    <h1 id="h1BuscaCamp">Buscar Campamentos</h1>
    <form action="/sigma-camp/searchCampamentosMix" method="get">
        <div class="centeredDiv">
            <label for="searchType">Buscar por:</label>
            <span class="iSelect">
            <select id="searchType" name="searchType" onchange="toggleInput(this.value)">
                <option value="nivel">Nivel Educativo</option>
                <option value="plazas">Número mínimo de plazas</option>
            </select>
        </div>
        </span>
        <div class="centeredDiv">
            <div id="nivelDiv">
                <label for="nivel">Nivel Educativo:</label>
                <span class="iSelect">
                    <select id="nivel" name="nivel">
                        <option value="">--Selecciona un nivel--</option>
                        <option value="INFANTIL">Infantil</option>
                        <option value="JUVENIL">Juvenil</option>
                        <option value="ADOLESCENTE">Adolescente</option>
                    </select>
                </span>
            </div>
            <div id="plazasDiv" style="display: none;">
                <label for="plazas">Número mínimo de plazas:</label>
                <input type="number" id="plazas" name="plazas" min="1">
            </div>
        </div>
        <div class="centeredDiv">
            <input id="submitBuscaCamp" type="submit" value="Buscar">
        </div>
    </form>
    <script>
        function toggleInput(value) {
            document.getElementById('nivelDiv').style.display = value === 'nivel' ? 'flex' : 'none';
            document.getElementById('plazasDiv').style.display = value === 'plazas' ? 'flex' : 'none';
        }
    </script>

    <h2>Resultados de la búsqueda:</h2>
    <ul>
        <%
            if (request.getAttribute("campamentos") != null) {
                ArrayList<Campamento> campamentos = (ArrayList<Campamento>) request.getAttribute("campamentos");
                for (Campamento campamento : campamentos) {
        %>
                   <li>
                        Identificador: <%= campamento.getIdentificador() %><br>
                        Fecha de inicio: <%= campamento.getFechaInicio() %><br>
                        Fecha de fin: <%= campamento.getFechaFin() %><br>
                        Nivel educativo: <%= campamento.getNivel() %><br>
                        Máximo de asistentes: <%= campamento.getMaxAsistentes() %>
                        <p></p>
                    </li>
        <%
                }
            }
        %>
    </ul>
</body>
</html>