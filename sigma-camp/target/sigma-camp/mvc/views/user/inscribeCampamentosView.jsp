<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento" %>

<!DOCTYPE html>
<html>
<head>
    <title>Inscripción a Campamentos</title>
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
        <div class="formulario__grupo" id="grupoAsociacion">
        <h1>Inscripción a Campamentos</h1>
        <form action="/inscribeCampamentosServlet" method="POST">
            <label for="nombreUsuario">Nombre de Usuario:</label>
            <input type="text" id="nombreUsuario" name="nombreUsuario">
            <label for="idCampamento">ID del Campamento:</label>
            <input type="text" id="idCampamento" name="idCampamento">
            <input type="submit" value="Inscribir">
        </form>
    </div>
    </div>
    </main>
</body>
</html>