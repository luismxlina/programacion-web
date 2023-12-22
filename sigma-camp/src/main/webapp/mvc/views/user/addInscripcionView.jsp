<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento" %>
<jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <title>Unirse a Campamentos</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/marco.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <%String aclUser = application.getInitParameter("aclUser"); %>
    <jsp:include page="<%=aclUser%>"></jsp:include>
    <aside>
        <jsp:include page="/include/sidebar.jsp"></jsp:include>
    </aside>
    <main>
        <jsp:include page="/include/header.jsp"></jsp:include>
        <div class="form-style-6">
            <h1>Unirse a Campamentos</h1>
            <% ArrayList<Campamento> campamentosDisponibles = (ArrayList<Campamento>) request.getAttribute("campamentos");
            if(campamentosDisponibles != null) {
                for(Campamento campamento : campamentosDisponibles) { %>
                    <div>
                        <h2><%= campamento.getIdentificador() %></h2>
                        <p>Nivel: <%= campamento.getNivel() %></p>
                        <p>Fecha de inicio: <%= campamento.getFechaInicio() %></p>
                        <form action="<%=request.getContextPath()%>/addInscripcion" method="GET">
                            <input type="hidden" name="campamentoId" value="<%= campamento.getIdentificador() %>">
                            <input type="submit" value="Unirse al campamento">
                        </form>
                    </div>
                <% }
            } %>
        </div>
    </main>
</body>
</html>