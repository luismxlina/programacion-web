<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dar de alta monitor</title>
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
        <h1>Añadir monitor</h1>
        <form id="formulario" action="/sigma-camp/addMonitor" method="GET">
            <div class="formulario__grupo" id="grupoMonitor">
                <label for="nombre">Nombre:</label><br>
                <input type="text" id="nombre" name="nombre" class="cajaBlanca"><br>
                <label for="apellidos">Apellidos:</label><br>
                <input type="text" id="apellidos" name="apellidos" class="cajaBlanca"><br>
                <label for="esEducador">Es Educador:</label><br>
                <input type="checkbox" id="esEducador" name="esEducador" class="cajaBlanca"><br>
                <input type="submit" id="submit" value="Crear Actividad"><br><br>
                <input type="reset" id="reset">
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
<script src="${pageContext.request.contextPath}/js/script.js"></script> 
</html>