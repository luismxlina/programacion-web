<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays, java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento, es.uco.pw.business.campamento.models.monitor.Monitor, es.uco.pw.business.campamento.models.actividad.Actividad" %>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <title>Asociar monitor a una actividad</title>
</head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
<body>
    <!-- ACL -->
    <%String aclAdmin = application.getInitParameter("aclAdmin"); %>
    <jsp:include page="<%=aclAdmin%>"></jsp:include>
    <!-- ACL -->
    <jsp:include page="/include/headerAdmin.jsp"></jsp:include>
    <div class="form-style-6">
        <%-- <h1>Asociar Monitor a Campamento</h1> --%>

        <form action="<%=request.getContextPath()%>/asociarMonitorActividad" method="GET">
            <div class="formulario__grupo" id="grupoAsociacion">
                <label for="idMonitor">Monitor:</label>
                <select id="idMonitor" name="idMonitor">
                    <% ArrayList<Monitor> monitores = (ArrayList<Monitor>) request.getAttribute("arrayMonitores");
                    if (monitores != null) {
                        for (Monitor monitor : monitores) { %>
                            <option value="<%=monitor.getIdentificador()%>"><%=monitor.getIdentificador() + " - " + monitor.getNombre() + " " + monitor.getApellidos() + " " + (monitor.getEsEducador() ? "(Educador)" : "(No Educador)")%></option>
                        <% }
                    } %>
                </select>
            </div>
            <div>
                <label for="nombreActividad">Actividad:</label>
                <select id="nombreActividad" name="nombreActividad">
                    <% ArrayList<Actividad> actividades = (ArrayList<Actividad>) request.getAttribute("arrayActividades");
                    if(actividades != null) {
                    for (Actividad actividad : actividades) { %>
                        <option value="<%=actividad.getNombreActividad()%>">
                            <%=actividad.getNombreActividad() + " - " + actividad.getNivel().toString()%>
                        </option>
                    <% } 
                    }%>
                </select>
            </div>
            <input type="submit" value="Asociar">
            <input type="reset" id="reset">
        </form>
    <%
    String responseAttribute = (String) request.getAttribute("response");
    if (responseAttribute != null) {
        String cssClass = responseAttribute.equals("success") ? "cajaBlanca" : "cajaRoja";
    %>
        <p class="<%=cssClass%>"><%=responseAttribute%></p>
    <%
        }
    %>
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>