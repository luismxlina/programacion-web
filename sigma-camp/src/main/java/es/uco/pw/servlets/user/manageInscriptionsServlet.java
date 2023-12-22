package es.uco.pw.servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.display.javabean.CustomerBean;
import es.uco.pw.business.campamento.models.campamento.Campamento;

@WebServlet(name = "manageInscriptions", urlPatterns = "/manageInscriptions")
public class manageInscriptionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public manageInscriptionsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String campamentoIdStr = request.getParameter("campamentoId");
        HttpSession session = request.getSession(true);
        CustomerBean User = (CustomerBean) session.getAttribute("User");

        if (User == null || User.getRol() == null || !User.getRol().equals("USER")) {
            request.setAttribute("ACL", "Not allowed to go there");
            request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
            return;
        }
        Integer asistenteId = User.getId();

        if (asistenteId == null) {
            request.setAttribute("error", "ID de asistente no proporcionado");
            request.getRequestDispatcher(getServletContext().getInitParameter("manageInscriptionsView")).forward(
                    request,
                    response);
            return;
        }

        if (campamentoIdStr != null && !campamentoIdStr.isEmpty()) {
            int campamentoId = Integer.parseInt(campamentoIdStr);
            try {
                GestorInscripciones.getInstance().deleteInscripcion(asistenteId, campamentoId);
                request.setAttribute("message", "Inscripción eliminada");
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentosAsistente(asistenteId);
        ArrayList<Inscripcion> inscripciones = GestorInscripciones.getInstance().getInscripcionesAsistente(asistenteId);
        ArrayList<Campamento> cancelableCampamentos = new ArrayList<Campamento>();

        for (Campamento campamento : campamentos) {
            for (Inscripcion inscripcion : inscripciones) {
                if (inscripcion.getId_Campamento() == campamento.getIdentificador()) {
                    if (inscripcion.getCancelable()) {
                        cancelableCampamentos.add(campamento);
                    }
                    break;
                }
            }
        }

        request.setAttribute("cancelableCampamentos", campamentos);

        request.getRequestDispatcher(getServletContext().getInitParameter("manageInscriptionsView")).forward(request,
                response);
    }
}