package es.uco.pw.servlets.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import es.uco.pw.display.javabean.CustomerBean;

import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.actividad.Horario;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.handler.GestorCampamentos;

/**
 * Servlet implementation class addActividadServlet
 */
@WebServlet(name = "addActividad", urlPatterns = "/addActividad")
public class addActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addActividadServlet() {
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
        request.getRequestDispatcher(getServletContext().getInitParameter("addActividadView")).forward(request,
                response);
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

        String nombre = request.getParameter("nombre");
        NivelEducativo nivel = NivelEducativo.valueOf(request.getParameter("nivel"));
        String horaParam = request.getParameter("hora");
        if (horaParam.equals("MANANA")) {
            horaParam = "MAÑANA";
        }

        if (!horaParam.equals("MAÑANA") && !horaParam.equals("TARDE")) {
            request.setAttribute("error", "Valor inválido para 'hora'. Sólo puede ser 'MAÑANA' or 'TARDE'.");
            request.getRequestDispatcher("/errorPage").forward(request, response);
            return;
        }

        Horario hora = Horario.valueOf(horaParam);
        int maxParticipantes = Integer.parseInt(request.getParameter("maxParticipantes"));
        int numMonitores = Integer.parseInt(request.getParameter("numMonitores"));

        Actividad actividad = new Actividad(nombre, nivel, hora, maxParticipantes, numMonitores);

        if (!GestorCampamentos.getInstance().addActividad(actividad)) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addActividadView")).forward(request,
                    response);
            return;
        }

        request.setAttribute("response", "success");
        request.getRequestDispatcher(getServletContext().getInitParameter("addActividadView")).forward(request,
                response);
    }
}