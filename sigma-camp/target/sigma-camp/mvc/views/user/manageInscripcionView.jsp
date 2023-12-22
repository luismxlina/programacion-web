<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Inscriptions</title>
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
            <h1>Manage Inscriptions</h1>
            <% if(request.getAttribute("error") != null) { %>
                <div class="error"><%= request.getAttribute("error") %></div>
            <% } %>
            <% if(request.getAttribute("message") != null) { %>
                <div class="message"><%= request.getAttribute("message") %></div>
            <% } %>
            <% ArrayList<Campamento> campamentos = (ArrayList<Campamento>) request.getAttribute("campamentos");
            if(campamentos != null) {
                for(Campamento campamento : campamentos) { %>
                    <div>
                        <h2><%= campamento.getIdentificador() %></h2>
                        <p>Nivel: <%= campamento.getNivel() %></p>
                        <p>Nivel: <%= campamento.getFechainicio() %></p>
                    </div>
                <% }
            } %>
        </div>
    </main>
</body>
</html>