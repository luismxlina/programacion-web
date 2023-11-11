package es.uco.pw.business.inscripcion.handler;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCreator;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTardia;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTemprana;
import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.business.users.models.asistente.Asistente;
import es.uco.pw.data.dao.InscripcionDAO;
import es.uco.pw.data.dao.CampamentoDAO;
import es.uco.pw.data.dao.AsistenteDAO;
import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;

/**
 * Clase que gestiona las inscripciones a campamentos.
 */
public class GestorInscripciones {

    // Singleton - Instancia única de GestorInscripciones.
    private static GestorInscripciones instance = null;
    private static InscripcionDAO inscripcionDAO;

    /**
     * Método estático para obtener la instancia única de GestorInscripciones.
     *
     * @param inscripciones ArrayList de inscripciones.
     * @param campamentos   ArrayList de campamentos.
     * @return Instancia única de GestorInscripciones.
     */
    public static GestorInscripciones getInstance(ArrayList<Inscripcion> inscripciones) {
        if (instance == null) {
            instance = new GestorInscripciones();
            inscripcionDAO = new InscripcionDAO();
        }
        return instance;
    }

    // Métodos

    public Boolean addInscripcion(int idParticipante, int idCampamento, boolean temprana,
            boolean necesidadesEspeciales) {

        if (existeInscripcion(idParticipante, idCampamento)) {
            System.out.println("El participante ya está inscrito en este campamento.\n");
            return false;
        }

        if (!GestorAsistentes.getInstance().existeAsistente(idParticipante)) {
            System.out.println("El participante no existe\n");
            return false;
        }

        if (!GestorCampamentos.getInstance().existeCampamento(idCampamento)) {
            System.out.println("El campamento con ID " + idCampamento + " no existe.");
            return false;
        }

        InscripcionCreator creator;
        if (temprana) {
            creator = new InscripcionTemprana();
        } else {
            creator = new InscripcionTardia();
        }

        Asistente asistente = GestorAsistentes.getInstance(null).getAsistente(idParticipante);

        if (asistente == null) {
            System.out.println("El participante con ID " + idParticipante + " no existe.");
            return false;
        }

        Inscripcion nuevaInscripcion;
        if (necesidadesEspeciales) {
            nuevaInscripcion = creator.registrarInscripcionCompleta(idParticipante, idCampamento, new Date());
        } else {
            nuevaInscripcion = creator.registrarInscripcionParcial(idParticipante, idCampamento, new Date());
        }

        // asistente.addInscripcion(nuevaInscripcion);
        inscripciones.add(nuevaInscripcion);

        System.out.println("Inscripción creada con éxito.");

        return true;
    }

    /**
     * Elimina una inscripción existente.
     *
     * @param id_Participante Identificador del participante.
     * @param id_Campamento   Identificador del campamento.
     * @return true si se eliminó la inscripción con éxito, false si no.
     */
    public Boolean deleteInscripcion(int id_Participante, int id_Campamento) {
        if (inscripcionDAO.delete(id_Participante, id_Campamento)) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene las inscripciones.
     * 
     * @return ArrayList de inscripciones.
     */
    public ArrayList<Inscripcion> getInscripciones() {

    }

    /**
     * Consulta los campamentos disponibles.
     */
    public void consultarCampamentosDisponibles() {
        Date fechaActual = new Date();
        for (Campamento campamento : GestorCampamentos.getInstance(null).getCampamentos()) {
            if (campamento.getFechaInicio().after(fechaActual)) {
                int cupoDisponible = campamento.getMax_asistentes()
                        - contarInscripciones(campamento.getIdentificador());
                if (cupoDisponible > 0) {
                    System.out.println("Campamento con ID " + campamento.getIdentificador()
                            + " disponible. Cupo restante: " + cupoDisponible);
                }
            }
        }
    }

    /**
     * Cuenta el número de inscripciones en un campamento.
     *
     * @param idCampamento Identificador del campamento.
     * @return Número de inscripciones.
     */
    private int contarInscripciones(int idCampamento) {
        int count = 0;
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == idCampamento) {
                count++;
            }
        }
        return count;
    }
}
