package es.uco.pw.servlets.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "cancelInscripcion", urlPatterns = "/cancelInscripcion")
public class cancelInscripcionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public cancelInscripcionServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean User = (CustomerBean) session.getAttribute("User");
        if (User == null || User.getRol() == null || !User.getRol().equals("USER")) {
            request.setAttribute("ACL", "No tienes permiso para realizar esta acción");
            request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
            return;
        }

        String asistenteIdStr = request.getParameter("asistenteId");
        String campamentoIdStr = request.getParameter("campamentoId");
        if (asistenteIdStr == null || campamentoIdStr == null) {
            request.getRequestDispatcher(getServletContext().getInitParameter("cancelInscripcionView")).forward(request, response);
            return;
        }

        int asistenteId = Integer.parseInt(asistenteIdStr);
        int campamentoId = Integer.parseInt(campamentoIdStr);
        GestorInscripciones gestorInscripciones = new GestorInscripciones();
        Inscripcion inscripcion = gestorInscripciones.getInscripcion(asistenteId, campamentoId);
        Campamento campamento = gestorCampamentos.getCampamento(campamentoId);

        if (inscripcion == null) {
            request.setAttribute("error", "La inscripción no existe");
            request.getRequestDispatcher(getServletContext().getInitParameter("cancelInscripcionView")).forward(request, response);
            return;
        }

        Date now = new Date();
        if (campamento.getFechaInicio().after(now) && campamento.isCancelable()) {
            gestorInscripciones.deleteInscripcion(asistenteId, campamentoId);
            request.setAttribute("message", "Inscripción cancelada con éxito");
        } else {
            request.setAttribute("error", "No se puede cancelar la inscripción");
        }

        request.getRequestDispatcher(getServletContext().getInitParameter("cancelInscripcionView")).forward(request, response);
    }
}