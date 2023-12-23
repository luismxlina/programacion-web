package es.uco.pw.servlets.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCreator;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTardia;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTemprana;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "addInscripcion", urlPatterns = "/addInscripcion")
public class addInscripcion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addInscripcion() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean User = (CustomerBean) session.getAttribute("User");
        if (User == null || User.getRol() == null || !User.getRol().equals("USER")) {
            request.setAttribute("ACL", "Not allowed to go there");
            request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
            return;
        }
        String idCampamentoStr = request.getParameter("idCampamento");
        String tipoInscripcionStr = request.getParameter("tipoInscripcion");
        String quiereInscribir = request.getParameter("quiereInscribir");

        if (idCampamentoStr == null && tipoInscripcionStr == null && quiereInscribir == null) {
            ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentosByPlazas(1);
            request.setAttribute("campamentos", campamentos);
            request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(request,
                    response);
            return;
        }

        Integer idAsistente = User.getId();
        Integer idCampamento = Integer.parseInt(idCampamentoStr);
        TipoInscripcion tipoInscripcion = tipoInscripcionStr != null ? TipoInscripcion.valueOf(tipoInscripcionStr)
                : null;
        Boolean temprana = GestorInscripciones.getInstance().getEsTemprana(new Date(),
                GestorCampamentos.getInstance().getCampamento(idCampamento).getFechaInicio());
        InscripcionCreator creator;

        if (temprana) {
            creator = new InscripcionTemprana();
        } else {
            creator = new InscripcionTardia();
        }

        Inscripcion inscripcion;
        if (tipoInscripcion != null && tipoInscripcion.equals(TipoInscripcion.COMPLETA)) {
            inscripcion = creator.registrarInscripcionCompleta(idAsistente, idCampamento, new Date());
            inscripcion.setTipoInscripcion(TipoInscripcion.COMPLETA);
        } else {
            inscripcion = creator.registrarInscripcionParcial(idAsistente, idCampamento, new Date());
            inscripcion.setTipoInscripcion(TipoInscripcion.PARCIAL);
        }
        try {
            if (quiereInscribir != null && quiereInscribir.equals("quiereInscribir")) {
                GestorInscripciones.getInstance().addInscripcion(inscripcion);
                request.setAttribute("response", "success");
                request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(
                        request,
                        response);
                return;
            }
            request.setAttribute("inscripcion", inscripcion);
            request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(
                    request,
                    response);
            return;

        } catch (Exception e) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(
                    request,
                    response);
            return;
        }
    }
}