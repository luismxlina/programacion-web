package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "asociarMonitores", urlPatterns = "/asociarMonitores")
public class asociarMonitorActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public asociarMonitorActividadServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBean User = (CustomerBean) session.getAttribute("User");
        if (User == null || User.getRol() == null || !User.getRol().equals("ADMIN")) {
            request.setAttribute("ACL", "Not allowed to go there");
            request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
            return;
        }

        String accion = request.getParameter("accion");

        // Verifica la acción y realiza la asociación correspondiente
        if ("asociarCampamento".equals(accion)) {
            asociarMonitorCampamento(request, response);
        } else if ("asociarActividad".equals(accion)) {
            asociarMonitorActividad(request, response);
        } else {
            response.getWriter().write("Acción no válida");
        }
    }

    private void asociarMonitorCampamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMonitor = Integer.parseInt(request.getParameter("idMonitor"));
            int idCampamento = Integer.parseInt(request.getParameter("idCampamento"));

            // Lógica para asociar monitores a campamentos (considerando monitores responsables)
            if (GestorCampamentos.getInstance().asociarMonitorCampamento(idMonitor, idCampamento)) {
                response.getWriter().write("Asociación exitosa");
            } else {
                response.getWriter().write("Error en la asociación");
            }
        } catch (Exception e) {
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    private void asociarMonitorActividad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMonitor = Integer.parseInt(request.getParameter("idMonitor"));
            String nombreActividad = request.getParameter("nombreActividad");
            int idCampamento = Integer.parseInt(request.getParameter("idCampamento"));

            // Lógica para asociar monitores a actividades (considerando monitores de atención especial)
            if (GestorCampamentos.getInstance().asociarMonitorActividad(idMonitor, nombreActividad, idCampamento)) {
                response.getWriter().write("Asociación exitosa");
            } else {
                response.getWriter().write("Error en la asociación");
            }
        } catch (Exception e) {
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
