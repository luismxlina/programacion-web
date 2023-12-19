<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@page import="java.time.format.DateTimeFormatter" %>
    <%@page import="java.time.LocalDate" %>
      <%@page import="java.time.LocalDateTime" %>
        <%@page import="es.uco.pw.business.inscripcion.handler.GestorInscripciones" %>

          <jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>Cargando...</title>
          </head>

          <body>

            <% String userMenuViewPath=application.getInitParameter("userMenuView"); ArrayList<Inscripcion>
              inscripciones = GestorInscripciones.getInstance().getAllInscripcionesBy(User.getEmail());

              String todayDate= formatter1.format(LocalDate.now()).toString();
              %>
              <jsp:forward page="<%=userMenuViewPath%>">
                <jsp:param name="inscripciones" value="<%=inscripciones%>" />
                <jsp:param name="fechaActual" value="<%=todayDate%>" />
              </jsp:forward>

          </body>

          </html>