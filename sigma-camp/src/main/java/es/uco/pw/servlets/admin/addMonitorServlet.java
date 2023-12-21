package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import es.uco.pw.display.javabean.CustomerBean;

import es.uco.pw.business.campamento.models.monitor.Monitor;
import es.uco.pw.business.campamento.dto.monitor.MonitorDTO;
import es.uco.pw.data.dao.MonitorDAO;
import es.uco.pw.business.campamento.handler.GestorCampamentos;


@WebServlet(name="addMonitor", urlPatterns="/addMonitor")
public class addMonitorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public addMonitorServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession  session = request.getSession();
        CustomerBean User = (CustomerBean)session.getAttribute("User");
        if(User == null ||User.getRol() == null||! User.getRol().equals("ADMIN")) {
            request.setAttribute("ACL","Not allowed to go there");
            request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
            return;
        }

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        Boolean esEducador = Boolean.valueOf(request.getParameter("esEducador"));
        
        if( nombre == null  || apellidos == null || esEducador == null) {
            request.getRequestDispatcher(getServletContext().getInitParameter("addMonitorView")).forward(request, response);
            return;
        }
    }
}
