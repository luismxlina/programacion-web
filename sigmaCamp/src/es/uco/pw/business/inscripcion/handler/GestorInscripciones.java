package es.uco.pw.business.inscripcion.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.*;
import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.business.users.models.asistente.Asistente;
import es.uco.pw.data.dao.InscripcionDAO;

// ("");
// ("(1) Mostrar inscripciones");
// ("(2) Mostrar inscripción");
// ("(3) Añadir inscripción");
// ("(4) Eliminar inscripción");
// ("(0) Volver al menú principal");
// ("");

/**
 * Clase que gestiona las inscripciones a campamentos.
 */
public class GestorInscripciones {

    // Singleton - Instancia única de GestorInscripciones.
    private static GestorInscripciones instance = null;
    private static InscripcionDAO inscripcionDAO;

    public static GestorInscripciones getInstance() {
        if (instance == null) {
            instance = new GestorInscripciones();
            inscripcionDAO = new InscripcionDAO();
        }
        return instance;
    }

    // Métodos
    // - getInscripcion
    // - getInscripciones
    // - addInscripcion
    // - buscarInscripcion
    // - borrarInscripcion

    /*
     * InscripcionCreator creator;
     * if (temprana) {
     * creator = new InscripcionTemprana();
     * } else {
     * creator = new InscripcionTardia();
     * }
     * Inscripcion nuevaInscripcion;
     * if (completa) {
     * nuevaInscripcion = creator.registrarInscripcionCompleta(idParticipante,
     * idCampamento, new Date());
     * } else {
     * nuevaInscripcion = creator.registrarInscripcionParcial(idParticipante,
     * idCampamento, new Date());
     * }
     */

    public Inscripcion getInscripcion(Integer asistenteId, Integer campamentoId) {
        InscripcionDTO inscripcionDTO = inscripcionDAO.get(asistenteId, campamentoId);
        if (inscripcionDTO == null) {
            return null; // No se encontró la inscripción
        }

        Boolean esTemprana = getEsTemprana(inscripcionDTO.getFechaInscripcion(),
                GestorCampamentos.getInstance().getCampamento(campamentoId).getFechaInicio());
        InscripcionCreator creator;

        if (esTemprana) {
            creator = new InscripcionTemprana();
        } else {
            creator = new InscripcionTardia();
        }

        Inscripcion inscripcion;

        if (inscripcionDTO.getTipoInscripcion().equals("Completa")) {

            inscripcion = creator.registrarInscripcionCompleta(inscripcionDTO.getAsistenteId(),
                    inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());

        } else {

            inscripcion = creator.registrarInscripcionParcial(inscripcionDTO.getAsistenteId(),
                    inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());

        }

        return inscripcion;
    }

    public Boolean getEsTemprana(Date fechaInscripcion, Date fechaInicioCampamento) {
        long diffInMillies = Math.abs(fechaInicioCampamento.getTime() - fechaInscripcion.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff > 15;
    }

    // public ArrayList<Inscripcion> getInscripciones() {
    //     ArrayList<Inscripcion> inscripciones = new ArrayList<>();
    //     for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAll()) {
    //         Inscripcion inscripcion = Inscripcion(inscripcionDTO);
    //         inscripciones.add(inscripcion);
    //     }
    //     return inscripciones;
    // }

    public boolean addInscripcion(Inscripcion inscripcion) {
        if (buscarInscripcion(inscripcion.getId_Participante(), inscripcion.getId_Campamento())) {
            return false;
        }
        if (inscripcionDAO.insert(new InscripcionDTO(inscripcion))) {
            return true;
        }
        return false;
    }

    // public boolean addInscripcion(int asistenteId, int campamentoId, Date
    // fechaInscripcion, String tipoInscripcion) {
    // // Comprobar si la inscripción ya existe
    // if (buscarInscripcion(asistenteId, campamentoId) != null) {
    // return false; // Ya existe la inscripción
    // }
    // // si la fecha de inscripcion es anterior a 15 dias de la fecha de inicio del
    // // campamento, la inscripcion es cancelable
    // // haz que cancelable sea true si fechaInscripcion es anterior a 15 dias de
    // la
    // // fecha de inicio del campamento
    // Campamento campamento =
    // GestorCampamentos.getInstance().getCampamento(campamentoId);
    // Date campStartDate = campamento.getFechaInicio();
    // long diffInMillies = Math.abs(campStartDate.getTime() -
    // fechaInscripcion.getTime());
    // long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

    // Boolean temprana = diff > 15;

    // InscripcionCreator creator;
    // if (temprana) {
    // creator = new InscripcionTemprana();
    // } else {
    // creator = new InscripcionTardia();
    // }

    // Asistente asistente = GestorAsistentes.getInstance().getAsistente();

    // Inscripcion nuevaInscripcion;
    // if (necesidadesEspeciales) {
    // nuevaInscripcion = creator.registrarInscripcionCompleta(idParticipante,
    // idCampamento, new Date());
    // } else {
    // nuevaInscripcion = creator.registrarInscripcionParcial(idParticipante,
    // idCampamento, new Date());
    // }

    // asistente.addInscripcion(nuevaInscripcion);
    // inscripciones.add(nuevaInscripcion);

    // // Crear la inscripción según el tipo (Completa o Parcial)
    // InscripcionCreator inscripcionCreator;
    // if (tipoInscripcion.equals("Completa")) {
    // inscripcionCreator = new InscripcionTemprana(); // O InscripcionTardia si es
    // necesario
    // } else {
    // inscripcionCreator = new InscripcionParcial(); // O InscripcionParcialTardia
    // si es necesario
    // }
    // Inscripcion inscripcion =
    // inscripcionCreator.registrarInscripcion(asistenteId, campamentoId,
    // fechaInscripcion);

    // // Guardar la inscripción en la base de datos
    // InscripcionDTO inscripcionDTO = new InscripcionDTO(asistenteId, campamentoId,
    // fechaInscripcion,
    // inscripcion.getPrecio(), tipoInscripcion);
    // inscripcionDAO.insert(inscripcionDTO);

    // return true; // Inserción exitosa
    // }

    public Boolean buscarInscripcion(int asistenteId, int campamentoId) {
        return getInscripcion(asistenteId, campamentoId) != null;
    }

    public Boolean borrarInscripcion(int asistenteId, int campamentoId) {
        // Verificar si la inscripción existe
        Inscripcion inscripcion = getInscripcion(asistenteId, campamentoId);
        if (inscripcion == null) {
            return false; // La inscripción no existe
        }

        // Eliminar la inscripción de la base de datos
        if (inscripcionDAO.delete(asistenteId, campamentoId)) {
            return true; // Eliminación exitosa
        }
        return false; // Error al eliminar la inscripción
    }
}
