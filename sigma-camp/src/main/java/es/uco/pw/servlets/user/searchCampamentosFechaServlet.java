package es.uco.pw.servlets.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "searchCampamentosFecha", urlPatterns = "/searchCampamentosFecha")
public class searchCampamentosFechaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public searchCampamentosFechaServlet() {
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
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

        if (fechaInicioStr == null && fechaFinStr == null) {
            request.setAttribute("respuesta", "false");
            request.getRequestDispatcher(getServletContext().getInitParameter("searchCampamentosFechaView")).forward(
                    request,
                    response);
            return;
        }
        if (fechaInicioStr == null || fechaFinStr == null) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("searchCampamentosFechaView")).forward(
                    request,
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
            request.setAttribute("response", "Selección no válida");
            request.getRequestDispatcher(getServletContext().getInitParameter("searchCampamentosFechaView")).forward(
                    request,
                    response);
            return;
        }

        ArrayList<Campamento> campamentosByFechas = GestorCampamentos.getInstance().getCampamentosByFechas(fechaInicio,
                fechaFin);
        if (campamentosByFechas.isEmpty()) {
            request.setAttribute("response", "empty");
            request.getRequestDispatcher(getServletContext().getInitParameter("searchCampamentosFechaView")).forward(
                    request,
                    response);
            return;
        }

        ArrayList<Campamento> campamentosInfantiles = new ArrayList<Campamento>();
        ArrayList<Campamento> campamentosJuveniles = new ArrayList<Campamento>();
        ArrayList<Campamento> campamentosAdolescentes = new ArrayList<Campamento>();

        for (Campamento campamento : campamentosByFechas) {
            if (campamento.getNivel().name().equals("INFANTIL")) {
                campamentosInfantiles.add(campamento);
            } else if (campamento.getNivel().name().equals("JUVENIL")) {
                campamentosJuveniles.add(campamento);
            } else if (campamento.getNivel().name().equals("ADOLESCENTE")) {
                campamentosAdolescentes.add(campamento);
            }
        }
        request.setAttribute("respuesta", "true");
        request.setAttribute("campamentosInfantiles", campamentosInfantiles);
        request.setAttribute("campamentosJuveniles", campamentosJuveniles);
        request.setAttribute("campamentosAdolescentes", campamentosAdolescentes);
        request.getRequestDispatcher(getServletContext().getInitParameter("searchCampamentosFechaView")).forward(
                request,
                response);
        return;
    }
}
