package es.uco.pw.servlets.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "addCampamento", urlPatterns = "/addCampamento")
public class addCampamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public addCampamentoServlet() {
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
        String nivelStr = request.getParameter("nivel");
        String maxAsistentesStr = request.getParameter("maxAsistentes");
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

        if (nivelStr == null && fechaInicioStr == null && fechaFinStr == null && maxAsistentesStr == null) {
            request.getRequestDispatcher(getServletContext().getInitParameter("addCampamentoView")).forward(request,
                    response);
            return;
        }

        if (nivelStr == null || fechaInicioStr == null || fechaFinStr == null || maxAsistentesStr == null) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addCampamentoView")).forward(request,
                    response);
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio = null;
        Date fechaFin = null;
        try {
            fechaInicio = formatter.parse(fechaInicioStr);
            fechaFin = formatter.parse(fechaFinStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        NivelEducativo nivel = NivelEducativo.valueOf(request.getParameter("nivel"));
        Integer maxAsistentes = Integer.parseInt(request.getParameter("maxAsistentes"));

        Campamento campamento = new Campamento(fechaInicio, fechaFin, nivel, maxAsistentes);

        if (!GestorCampamentos.getInstance().altaCampamento(campamento)) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addCampamentoView")).forward(request,
                    response);
            return;
        }
        request.setAttribute("response", "success");

        request.getRequestDispatcher(getServletContext().getInitParameter("addCampamentoView")).forward(request,
                response);
        return;
    }
}