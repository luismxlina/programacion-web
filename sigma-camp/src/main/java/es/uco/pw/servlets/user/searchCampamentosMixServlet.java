package es.uco.pw.servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.campamento.Campamento;

@WebServlet(name = "searchCampamentos", urlPatterns = "/searchCampamentosMix")
public class searchCampamentosMixServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public searchCampamentosMixServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nivel = request.getParameter("nivel");
        String plazasStr = request.getParameter("plazas");
        int plazas = plazasStr != null ? Integer.parseInt(plazasStr) : 0;

        ArrayList<Campamento> campamentos;
        if (nivel != null) {
            campamentos = GestorCampamentos.getInstance().getCampamentosByNivelEducativo(NivelEducativo.valueOf(nivel));
        } else if (plazas > 0) {
            campamentos = GestorCampamentos.getInstance().getCampamentosByPlazas(plazas);
        } else {
            request.setAttribute("error", "Invalid search criteria");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("campamentos", campamentos);
        request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
    }
}