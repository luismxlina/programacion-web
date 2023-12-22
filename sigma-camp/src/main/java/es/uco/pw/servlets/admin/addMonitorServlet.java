package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.monitor.Monitor;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "addMonitor", urlPatterns = "/addMonitor")
public class addMonitorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addMonitorServlet() {
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

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String esEducadorStr = request.getParameter("esEducador");
        if (nombre == null && apellidos == null && esEducadorStr == null) {
            request.getRequestDispatcher(getServletContext().getInitParameter("addMonitorView")).forward(request,
                    response);
            return;
        }
        Boolean esEducador = Boolean.valueOf(request.getParameter("esEducador"));

        if (nombre == null || nombre.isEmpty() || apellidos == null || apellidos.isEmpty() || esEducador == null) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addMonitorView")).forward(request,
                    response);
            return;
        }
        Monitor monitor = new Monitor(nombre, apellidos, esEducador);
        if (GestorCampamentos.getInstance().addMonitor(monitor)) {
            request.setAttribute("response", "success");
            return;
        } else {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addMonitorView")).forward(request,
                    response);
            return;
        }
    }
}