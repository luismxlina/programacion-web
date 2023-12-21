package es.uco.pw.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "asociarActividad", urlPatterns = "/asociarActividad")
public class asociarActividadCampamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public asociarActividadCampamentoServlet() {
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
        request.getRequestDispatcher(getServletContext().getInitParameter("asociarActividadView")).forward(request,
                response);
    }
}
