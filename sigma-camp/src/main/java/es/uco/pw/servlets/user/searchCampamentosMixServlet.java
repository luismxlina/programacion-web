package es.uco.pw.servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "searchCampamentos", urlPatterns = "/searchCampamentosMix")
public class searchCampamentosMixServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public searchCampamentosMixServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        CustomerBean User = (CustomerBean) session.getAttribute("User");
        if (User == null || User.getRol() == null || !User.getRol().equals("USER")) {
            request.setAttribute("ACL", "Not allowed to go there");
            request.getRequestDispatcher(getServletContext().getInitParameter("index")).forward(request, response);
            return;
        }

        String nivel = request.getParameter("nivel");
        String plazasStr = request.getParameter("plazas");
        int plazas = 0;
        if (plazasStr != null && !plazasStr.isEmpty()) {
            plazas = Integer.parseInt(plazasStr);
        }

        ArrayList<Campamento> campamentos;
        if (nivel != null && Arrays.stream(NivelEducativo.values()).anyMatch(e -> e.name().equals(nivel))) {
            campamentos = GestorCampamentos.getInstance().getCampamentosByNivelEducativo(NivelEducativo.valueOf(nivel));
        } else if (plazas > 0) {
            campamentos = GestorCampamentos.getInstance().getCampamentosByPlazas(plazas);
        } else {
            request.setAttribute("error", "Petición incorrecta");
            request.getRequestDispatcher(getServletContext().getInitParameter("searchCampamentosMixView")).forward(
                    request,
                    response);
            return;
        }

        request.setAttribute("campamentos", campamentos);
        request.getRequestDispatcher(getServletContext().getInitParameter("searchCampamentosMixView")).forward(request,
                response);
    }
}