<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays, java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento, es.uco.pw.business.campamento.models.monitor.Monitor" %>
<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <title>Asociar monitor a un campamento</title>
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
        <h1>Asociar Monitor a Campamento</h1>

        <form action="<%=request.getContextPath()%>/asociarMonitor" method="GET">
            <div>
                <label for="idMonitor">Monitor:</label>
                <select id="idMonitor" name="idMonitor">
                    <% ArrayList<Monitor> monitores = (ArrayList<Monitor>) request.getAttribute("arrayMonitores");
                    if (monitores != null) {
                        
                    
                    for (Monitor monitor : monitores) { %>
                        <option value="<%=monitor.getIdentificador()%>"><%=monitor.getNombre()%></option>
                    <% }
                    } %>
                </select>
            </div>
            <div>
                <label for="idCampamento">Campamento:</label>
                <select id="idCampamento" name="idCampamento">
                    <% ArrayList<Campamento> campamentos = (ArrayList<Campamento>) request.getAttribute("arrayCampamentos");
                    if(campamentos != null) {
                    for (Campamento campamento : campamentos) { %>
                        <option value="<%=campamento.getIdentificador()%>">
                            <%=campamento.getIdentificador() + " - " + campamento.getNivel().toString()%>
                        </option>
                    <% } 
                    }%>
                </select>
            </div>
            <input type="submit" value="Asociar">
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