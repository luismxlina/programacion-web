package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.campamento.models.monitor.Monitor;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "asociarMonitorCampamento", urlPatterns = "/asociarMonitorCampamento")
public class asociarMonitorCampamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public asociarMonitorCampamentoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean User = (CustomerBean) session.getAttribute("User");
        if (User == null || User.getRol() == null || !User.getRol().equals("ADMIN")) {
            request.setAttribute("ACL", "Not allowed to go there");
            request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
            return;
        }

        String idMonitorStr = request.getParameter("idMonitor");
        String idCampamentoStr = request.getParameter("idCampamento");
        ArrayList<Monitor> monitores = GestorCampamentos.getInstance().getMonitores();
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();

        if (idMonitorStr == null && idCampamentoStr == null) {
            request.setAttribute("arrayMonitores", monitores);
            request.setAttribute("arrayCampamentos", campamentos);
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorCampamentoView")).forward(
                    request,
                    response);
            return;
        }
        if (idMonitorStr == null || idCampamentoStr == null) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorCampamentoView")).forward(
                    request,
                    response);
            return;
        }

        int idMonitor = Integer.parseInt(idMonitorStr);
        int idCampamento = Integer.parseInt(idCampamentoStr);
        ArrayList<Actividad> actividades = GestorCampamentos.getInstance().getActividadesCampamento(idCampamento);
        Boolean puedeAsociarse = GestorCampamentos.getInstance().getMonitor(idMonitor).getEsEducador();

        if (!puedeAsociarse) {
            for (Actividad actividad : actividades) {
                if (GestorCampamentos.getInstance().buscarMonitorActividad(actividad.getNombreActividad(), idMonitor)) {
                    puedeAsociarse = true;
                    break;
                }
            }
        }

        if (puedeAsociarse && GestorCampamentos.getInstance().asociarMonitorCampamento(idMonitor, idCampamento)) {
            request.setAttribute("arrayMonitores", monitores);
            request.setAttribute("arrayCampamentos", campamentos);
            request.setAttribute("response", "success");
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorCampamentoView")).forward(
                    request,
                    response);
            return;
        } else if (!puedeAsociarse) {

            request.setAttribute("response",
                    "El monitor seleccionado debe estar en alguna actividad del campamento seleccionado");
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorActividadView")).forward(
                    request,
                    response);
            return;
        } else {
            request.setAttribute("arrayMonitores", monitores);
            request.setAttribute("arrayCampamentos", campamentos);
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorCampamentoView")).forward(
                    request,
                    response);
            return;
        }

    }
}