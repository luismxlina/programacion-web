package es.uco.pw.data.dao;

import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
import es.uco.pw.business.users.dto.asistente.AsistenteDTO;
import es.uco.pw.data.dao.CampamentoDAO;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // CampamentoDTO campamento1 = new CampamentoDTO(111, new Date(), new Date(), "Infantil", 50);
        // CampamentoDTO campamento2 = new CampamentoDTO(222, new Date(), new Date(), "Infantil", 40);

        // CampamentoDAO campamentoDAO = new CampamentoDAO();

        // boolean resultado1 = campamentoDAO.insert(campamento1);
        // boolean resultado2 = campamentoDAO.insert(campamento2);

        // if (resultado1 && resultado2) {
        //     System.out.println("Campamentos insertados correctamente.");
        // } else {
        //     System.out.println("Error al insertar los campamentos.");
        // }

        // ArrayList<CampamentoDTO> campamentos = campamentoDAO.getAll();
        // for (CampamentoDTO campamento : campamentos) {
        //     System.out.println(campamento.getIdentificador());
        //     System.out.println(campamento.getFechaInicio());
        //     System.out.println(campamento.getFechaFin());
        //     System.out.println(campamento.getNivel());
        //     System.out.println(campamento.getMaxAsistentes());
        // }

        // AsistenteDTO asistente1 = new AsistenteDTO(90, "Pepe", "Perez", new Date(), false);
        // AsistenteDTO asistente2 = new AsistenteDTO(91, "Juan", "Perez", new Date(), false);
        // AsistenteDAO asistenteDAO = new AsistenteDAO();
        // asistenteDAO.insert(asistente1);
        // asistenteDAO.insert(asistente2);

        // ArrayList<AsistenteDTO> asistentes = asistenteDAO.getAll();
        // for (AsistenteDTO asistente : asistentes) {
        //     System.out.println(asistente.getIdentificador());
        //     System.out.println(asistente.getNombre());
        //     System.out.println(asistente.getApellidos());
        //     System.out.println(asistente.getFechaNacimiento());
        //     System.out.println(asistente.getRequiereAtencion());
        // }

        // public InscripcionDTO(int asistenteId, int campamentoId, String fechaInscripcion, double precio,
        //     String tipoInscripcion) {
        InscripcionDAO inscripcionDAO = new InscripcionDAO();
        // InscripcionDTO inscripcion1 = new InscripcionDTO(90, 111, "2020-01-01", 100, "Completa");
        // InscripcionDTO inscripcion2 = new InscripcionDTO(91, 222, "2020-01-01", 100, "Completa");
        // inscripcionDAO.insert(inscripcion1);
        // inscripcionDAO.insert(inscripcion2);
        ArrayList<InscripcionDTO> inscripciones = inscripcionDAO.getAll();
        for (InscripcionDTO inscripcion : inscripciones) {
            System.out.println(inscripcion.getAsistenteId());
            System.out.println(inscripcion.getCampamentoId());
            System.out.println(inscripcion.getFechaInscripcion());
            System.out.println(inscripcion.getPrecio());
            System.out.println(inscripcion.getTipoInscripcion());
        }
    }
}
