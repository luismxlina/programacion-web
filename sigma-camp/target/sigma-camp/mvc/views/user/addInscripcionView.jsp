<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, es.uco.pw.business.campamento.models.campamento.Campamento, es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion" %>
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
            Inscripcion inscripcion = (Inscripcion) request.getAttribute("inscripcion");
            if(campamentosDisponibles != null) {
                for(Campamento campamento : campamentosDisponibles) { %>
                    <div>
                        <h2><%= campamento.getIdentificador() %></h2>
                        <p>Nivel: <%= campamento.getNivel() %></p>
                        <p>Fecha de inicio: <%= campamento.getFechaInicio() %></p>
                        <form action="<%=request.getContextPath()%>/addInscripcion" method="GET">
                            <input type="hidden" name="idCampamento" value="<%= campamento.getIdentificador() %>">
                            <input type="hidden" name="tipoInscripcion" value="COMPLETA">
                            <input type="submit" value="Ver inscripción completa">
                        </form>
                        <form action="<%=request.getContextPath()%>/addInscripcion" method="GET">
                            <input type="hidden" name="idCampamento" value="<%= campamento.getIdentificador() %>">
                            <input type="hidden" name="tipoInscripcion" value="PARCIAL">
                            <input type="submit" value="Ver inscripción parcial">
                        </form>
                    </div>
                <% }
            } else if(inscripcion != null) { %>
                <div>
                    <h2>Inscripción</h2>
                     <p>ID del Participante: <%= inscripcion.getId_Participante() %></p>
                    <p>ID del Campamento: <%= inscripcion.getId_Campamento() %></p>
                    <p>Fecha de Inscripción: <%= inscripcion.getFechaInscripcion() %></p>
                    <p>Precio: <%= inscripcion.getPrecio() %> €</p>
                    <p>Cancelable: <%= inscripcion.getCancelable() ? "Sí" : "No" %></p>
                    <form action="<%=request.getContextPath()%>/addInscripcion" method="GET">
                        <input type="hidden" name="idCampamento" value="<%= inscripcion.getId_Campamento() %>">
                        <input type="hidden" name="quiereInscribir" value="quiereInscribir">
                        <input type="submit" value="Confirmar inscripción">
                    </form>
                    <form action="<%=request.getContextPath()%>/addInscripcion" method="GET">
                        <input type="submit" value="Volver atrás">
                    </form> 
                </div>
            <% } %>
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
    </main>
</body>
</html>