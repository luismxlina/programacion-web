<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <title>Crear Campamento</title>
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
        <h1>Crear Campamento</h1>
        <form class="search-form" id="formulario" action="/sigma-camp/addCampamento" method="GET">
            <div class="formulario__grupo" id="grupoCampamento">
                <label for="fechaInicio">Fecha de Inicio:</label><br>
                <input type="date" id="fechaInicio" name="fechaInicio" class="cajaBlanca"><br>
                <label for="fechaFin">Fecha de Fin:</label><br>
                <input type="date" id="fechaFin" name="fechaFin" class="cajaBlanca"><br>
                <label for="nivel">Nivel Educativo:</label><br>
                <select id="nivel" name="nivel" class="cajaBlanca">
                    <option value="INFANTIL">Infantil</option>
                    <option value="JUVENIL">Juvenil</option>
                    <option value="ADOLESCENTE">Adolescente</option>
                </select><br>
                <label for="maxAsistentes">Máximo de Asistentes:</label><br>
                <input type="number" id="maxAsistentes" name="maxAsistentes" class="cajaBlanca"><br>
                <input type="submit" value="Crear Campamento">
            </div>
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
</html>