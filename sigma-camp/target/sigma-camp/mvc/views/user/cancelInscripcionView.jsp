<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cancelar Inscripción a Campamentos</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
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
                <h1>Cancelar Inscripción a Campamentos</h1>
                <form class="search-form" action="/sigma-camp/cancelInscripcion" method="get">
                    <label for="asistenteId">ID del Asistente:</label>
                    <input type="number" id="asistenteId" name="asistenteId" required>
                    <label for="campamentoId">ID del Campamento:</label>
                    <input type="number" id="campamentoId" name="campamentoId" required>
                    <input type="submit" value="Cancelar Inscripción">
                </form>
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty message}">
                    <div class="message">${message}</div>
                </c:if>

        </div>
    </main>
</body>
</html> 