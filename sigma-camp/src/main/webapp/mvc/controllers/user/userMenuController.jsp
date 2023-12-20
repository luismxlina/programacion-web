<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@page import="java.time.format.DateTimeFormatter" %>
    <%@page import="java.time.LocalDate" %>
      <%@page import="java.time.LocalDateTime" %>
        <%@page import="es.uco.pw.business.inscripcion.handler.GestorInscripciones" %>
        <%@page import="es.uco.pw.business.campamento.handler.GestorCampamentos" %>
        <%@page import="java.util.ArrayList" %>
        <%@page import="es.uco.pw.business.campamento.models.campamento.Campamento" %>


          <jsp:useBean id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
          <!DOCTYPE html>
          <html>

          <head>
            <meta charset="UTF-8">
            <title>Cargando...</title>
          </head>

          <body>

            <%
              DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/YYYY");
              String userMenuViewPath=application.getInitParameter("userMenuView"); 
              ArrayList<Campamento> campamentos = null;
              campamentos = GestorCampamentos.getInstance().getCampamentosAsistente(User.getId());
              for (int i = 0; i < campamentos.size() - 1; i++) {
                for (int j = 0; j < campamentos.size() - i - 1; j++) {
                    if (campamentos.get(j).getFechaInicio().compareTo(campamentos.get(j + 1).getFechaInicio()) > 0) {
                        Campamento temp = campamentos.get(j);
                        campamentos.set(j, campamentos.get(j + 1));
                        campamentos.set(j + 1, temp);
                    }
                }
            }
              String todayDate= formatter1.format(LocalDate.now()).toString();
              request.setAttribute("campamentos", campamentos);
              request.setAttribute("fechaActual", todayDate);
            %>
              <jsp:forward page="<%=userMenuViewPath%>"/>

          </body>

          </html>