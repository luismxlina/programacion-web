package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.monitor.Monitor;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "asociarMonitorActividad", urlPatterns = "/asociarMonitorActividad")
public class asociarMonitorActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public asociarMonitorActividadServlet() {
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
        String nombreActividad = request.getParameter("nombreActividad");
        ArrayList<Monitor> monitores = GestorCampamentos.getInstance().getMonitores();
        ArrayList<Actividad> actividades = GestorCampamentos.getInstance().getActividades();

        Iterator<Monitor> iterator = monitores.iterator();
        while (iterator.hasNext()) {
            Monitor monitor = iterator.next();
            if (monitor.getEsEducador()) {
                iterator.remove();
            }
        }

        if (idMonitorStr == null && nombreActividad == null) {
            request.setAttribute("arrayMonitores", monitores);
            request.setAttribute("arrayActividades", actividades);
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorActividadView")).forward(
                    request,
                    response);
            return;
        }
        if (idMonitorStr == null || nombreActividad == null) {
            request.setAttribute("response", "fail");
            request.setAttribute("arrayMonitores", monitores);
            request.setAttribute("arrayActividades", actividades);
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorActividadView")).forward(
                    request,
                    response);
            return;
        }

        int idMonitor = Integer.parseInt(idMonitorStr);

        try {
            GestorCampamentos.getInstance().asociarMonitorActividad(idMonitor, nombreActividad);
            request.setAttribute("response", "success");
        } catch (Exception e) {
            request.setAttribute("response", "fail");
        } finally {
            request.setAttribute("arrayMonitores", monitores);
            request.setAttribute("arrayActividades", actividades);
            request.getRequestDispatcher(getServletContext().getInitParameter("asociarMonitorActividadView")).forward(
                    request,
                    response);
        }
        return;
    }
}