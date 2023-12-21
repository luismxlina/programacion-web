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

@WebServlet(name = "asociarActividadData", urlPatterns = "/asociarActividadData")
public class asociarActividadCampamentoServletData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public asociarActividadCampamentoServletData() {
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

        // Crear el objeto JSON
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        // Iterar sobre todos los niveles educativos
        for (NivelEducativo nivelEducativo : NivelEducativo.values()) {
            ArrayList<Campamento> campamentos = GestorCampamentos.getInstance()
                    .getCampamentosByNivelEducativo(nivelEducativo);
            JsonArrayBuilder campamentosArrayBuilder = Json.createArrayBuilder();
            for (Campamento campamento : campamentos) {
                System.out.println("Campamento ID: " + campamento.getIdentificador());
                campamentosArrayBuilder.add(Json.createObjectBuilder()
                        .add("identificador", campamento.getIdentificador()));
            }
            jsonObjectBuilder.add(nivelEducativo.name() + "Campamentos", campamentosArrayBuilder);

            ArrayList<Actividad> actividades = GestorCampamentos.getInstance()
                    .getActividadesByNivelEducativo(nivelEducativo);
            JsonArrayBuilder actividadesArrayBuilder = Json.createArrayBuilder();
            for (Actividad actividad : actividades) {
                System.out.println("Nombre de la actividad: " + actividad.getNombreActividad()); // Imprimir el nombre
                                                                                                 // de la actividad
                System.out.println("Hora de la actividad: " + actividad.getHora()); // Impri
                actividadesArrayBuilder.add(Json.createObjectBuilder()
                        .add("nombreActividad", actividad.getNombreActividad())
                        .add("hora", (actividad.getHora().toString())));
            }
            jsonObjectBuilder.add(nivelEducativo.name() + "Actividades", actividadesArrayBuilder);
        }

        // Escribir el objeto JSON en la respuesta
        response.setContentType("application/json");
        response.getWriter().write(jsonObjectBuilder.build().toString());
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

        // Get the selected educational level, camp, and activity from the request
        Integer campamento = Integer.parseInt(request.getParameter("campamento"));
        String actividad = request.getParameter("actividad");

        // Use the GestorCampamentos class to associate the selected activity with the
        // selected camp
        boolean success = GestorCampamentos.getInstance().asociarActividadCampamento(actividad, campamento);

        // Set the response content type to text/plain
        response.setContentType("text/plain");

        // Write the result to the response
        if (success) {
            request.setAttribute("response", "success");
            request.getRequestDispatcher(getServletContext().getInitParameter("addActividadView")).forward(request,
                    response);
            return;
        } else {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addActividadView")).forward(request,
                    response);
            return;
        }
    }
}
