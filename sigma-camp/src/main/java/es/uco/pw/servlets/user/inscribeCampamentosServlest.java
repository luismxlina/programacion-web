package es.uco.pw.servlets.user;

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

import es.uco.pw.business.inscripcion.handler.GestorInscripciones;
import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.display.javabean.CustomerBean;

@WebServlet(name = "addInscripcion", urlPatterns = "/addInscripcion")
public class inscribeCampamentosServlest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public inscribeCampamentosServlest() {
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
        String asistenteIdStr = request.getParameter("id_Participante");
        String campamentoIdStr = request.getParameter("id_Campamento");
        String fechaInscripcionStr = request.getParameter("fechaInscripcion");
        String precioStr = request.getParameter("precio");
        String esCanceladaStr = request.getParameter("cancelable");
        String tipoInscripcionStr = request.getParameter("tipoInscripcion");

        if (asistenteIdStr == null && campamentoIdStr == null && fechaInscripcionStr == null && precioStr == null && tipoInscripcionStr == null) {
            request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(request,
                    response);
            return;
        }

        if (asistenteIdStr == null || campamentoIdStr == null || fechaInscripcionStr == null || precioStr == null || tipoInscripcionStr == null) {
            request.setAttribute("response", "fail");
            request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(request,
                    response);
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInscripcion = null;
        try {
            fechaInscripcion = formatter.parse(fechaInscripcionStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int asistenteId = Integer.parseInt(request.getParameter("id_Participante"));
        int campamentoId = Integer.parseInt(request.getParameter("id_Campamento"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        TipoInscripcion tipoInscripcion = TipoInscripcion.valueOf(request.getParameter("tipoInscripcion"));
        boolean esCancelada = Boolean.parseBoolean(request.getParameter("cancelable"));

        // Inscripcion inscripcion = new Inscripcion(asistenteId, campamentoId, fechaInscripcion, esCancelada);

        // if (!GestorInscripciones.getInstance().addInscripcion(inscripcion)) {
        //     request.setAttribute("response", "fail");
        //     request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(request,
        //             response);
        //     return;
        // }
        // request.setAttribute("response", "success");

        request.getRequestDispatcher(getServletContext().getInitParameter("addInscripcionView")).forward(request,
                response);
        return;
    }
}