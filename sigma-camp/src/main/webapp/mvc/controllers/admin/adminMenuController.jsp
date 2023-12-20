<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion" %>
<%@page import="es.uco.pw.business.inscripcion.handler.GestorInscripciones" %>
<%@page import="es.uco.pw.business.campamento.models.campamento.Campamento" %>
<%@page import="es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion" %>
<%@page import="es.uco.pw.business.campamento.handler.GestorCampamentos" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.AbstractMap.SimpleEntry"%>

<jsp:useBean  id="User" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cargando...</title>
</head>
<body>

<%
String adminMenuViewPath = application.getInitParameter("adminMenuView");
// Crear el HashMap
HashMap<Integer, SimpleEntry<Integer, Integer>> conteos = new HashMap<>();

ArrayList<Inscripcion> inscripciones = GestorInscripciones.getInstance().getInscripciones();
ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
// Recorrer las inscripciones
for (Inscripcion inscripcion : inscripciones) {
    int idCampamento = inscripcion.getId_Campamento();
    TipoInscripcion tipoInscripcion = inscripcion.getTipoInscripcion();

    // Obtener el conteo actual para este campamento, o crear uno nuevo si no existe
    SimpleEntry<Integer, Integer> conteo = conteos.getOrDefault(idCampamento, new SimpleEntry<>(0, 0));

    // Incrementar el conteo correspondiente
    if (tipoInscripcion == TipoInscripcion.PARCIAL) {
        conteo = new SimpleEntry<>(conteo.getKey() + 1, conteo.getValue());
    } else if (tipoInscripcion == TipoInscripcion.COMPLETA) {
        conteo = new SimpleEntry<>(conteo.getKey(), conteo.getValue() + 1);
    }

    // Guardar el conteo actualizado
    conteos.put(idCampamento, conteo);
}

// Transmitir los datos a la vista
request.setAttribute("conteos", conteos);
request.setAttribute("campamentos", campamentos);
%>
<jsp:forward page="<%=adminMenuViewPath%>"/>

</body>
</html>