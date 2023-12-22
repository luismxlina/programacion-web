<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.campamento.models.actividad.NivelEducativo" %>
<%@ page import="es.uco.pw.business.campamento.models.actividad.Horario" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <title>Crear Actividad</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
<body>
    <!-- ACL -->
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
    <jsp:include page="<%=aclAdmin%>"></jsp:include>
    <!-- ACL -->
    <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
    <aside>
        <jsp:include page="/include/sidebarAdmin.jsp"></jsp:include>
    </aside>
    <div class="form-style-6">
        <form id="formulario" action="/sigma-camp/addActividad" method="GET">
            <div class="formulario__grupo" id="grupoActividad">
                <label for="nombre">Nombre:</label><br>
                <input type="text" id="nombre" name="nombre" class="cajaBlanca"><br>
                <label for="nivel">Nivel Educativo:</label><br>
                <select id="nivel" name="nivel" class="cajaBlanca">
                    <option value="INFANTIL">Infantil</option>
                    <option value="JUVENIL">Juvenil</option>
                    <option value="ADOLESCENTE">Adolescente</option>
                </select><br>
                <label for="hora">Horario:</label><br>
                <select id="hora" name="hora" class="cajaBlanca">
                      <option value="MANANA">Mañana</option>
                      <option value="TARDE">Tarde</option>
                </select><br>
                <label for="maxParticipantes">Max Participantes:</label><br>
                <input type="number" id="maxParticipantes" name="maxParticipantes" class="cajaBlanca"><br>
                <label for="numMonitores">Num Monitores:</label><br>
                <input type="number" id="numMonitores" name="numMonitores" class="cajaBlanca"><br>
                <input type="submit" id="submit" value="Crear Actividad"><br><br>
                <input type="reset" id="reset">
            </form>
            <%
            if(request.getAttribute("response") != null){
                if(request.getAttribute("response") == "success"){
            %>
                    <p class="cajaBlanca"><%=request.getAttribute("response")%></p>
                <%}else{%>
                    <p class="cajaRoja"><%=request.getAttribute("response")%></p>
            <% 
                }
            } 
            %>
            
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>