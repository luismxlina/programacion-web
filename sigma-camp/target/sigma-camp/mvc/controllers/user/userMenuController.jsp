<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@page import="java.time.format.DateTimeFormatter" %>
    <%@page import="java.time.LocalDate" %>
      <%@page import="java.time.LocalDateTime" %>
        <%@page import="es.uco.pw.business.inscripcion.handler.GestorInscripciones" %>
        <%@page import="es.uco.pw.business.campamento.handler.GestorCampamentos" %>

          <jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>Cargando...</title>
          </head>

          <body>

            <% String userMenuViewPath=application.getInitParameter("userMenuView"); 
              ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentosAsistente(User.getId());
              String todayDate= formatter1.format(LocalDate.now()).toString();
              request.setAttribute("campamentos", campamentos);
              request.setAttribute("fechaActual", todayDate);
            %>
              <jsp:forward page="<%=userMenuViewPath%>"/>

          </body>

          </html>