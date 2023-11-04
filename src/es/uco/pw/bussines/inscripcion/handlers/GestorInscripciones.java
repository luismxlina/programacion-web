package es.uco.pw.bussines.inscripcion.handlers;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.bussines.campamento.handlers.GestorCampamentos;
import es.uco.pw.bussines.campamento.models.campamento.Campamento;
import es.uco.pw.bussines.inscripcion.*;
import es.uco.pw.bussines.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.bussines.inscripcion.models.inscripcion.factory.InscripcionCreator;
import es.uco.pw.bussines.inscripcion.models.inscripcion.factory.Tardia;
import es.uco.pw.bussines.inscripcion.models.inscripcion.factory.Temprana;
import es.uco.pw.bussines.users.handlers.GestorAsistentes;
import es.uco.pw.bussines.users.models.asistente.Asistente;

/**
 * Clase que gestiona las inscripciones a campamentos.
 */
public class GestorInscripciones {

    // Atributos

    // ArrayList que contiene las inscripciones.
    private ArrayList<Inscripcion> inscripciones;

    // Singleton - Instancia única de GestorInscripciones.
    private static GestorInscripciones instance = null;

    /**
     * Constructor privado para crear una instancia de GestorInscripciones.
     *
     * @param inscripciones ArrayList de inscripciones.
     */
    private GestorInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    /**
     * Método estático para obtener la instancia única de GestorInscripciones.
     *
     * @param inscripciones ArrayList de inscripciones.
     * @param campamentos   ArrayList de campamentos.
     * @return Instancia única de GestorInscripciones.
     */
    public static GestorInscripciones getInstance(ArrayList<Inscripcion> inscripciones) {
        if (instance == null) {
            instance = new GestorInscripciones(inscripciones);
        }
        return instance;
    }

    // Métodos

    /**
     * Muestra todas las inscripciones en consola.
     *
     * @return false.
     */
    public Boolean mostrarInscripciones() {
        for (Inscripcion inscripcion : inscripciones) {
            System.out.println(inscripcion.toString());
        }
        return false;
    }

    /**
     * Muestra una inscripción específica en consola.
     *
     * @param idParticipante Identificador del participante.
     * @param idCampamento   Identificador del campamento.
     * @return true si se encontró la inscripción, false si no.
     */
    public Boolean mostrarInscripcion(int idParticipante, int idCampamento) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == idCampamento && inscripcion.getId_Participante() == idParticipante) {
                System.out.println(inscripcion.toString());
                return true;
            }
        }
        System.out.println("No se encontró una inscripción para el participante con ID " + idParticipante
                + " en el campamento con ID " + idCampamento);
        return false;
    }

    /**
     * Añade una nueva inscripción.
     *
     * @param idParticipante        Identificador del participante.
     * @param idCampamento          Identificador del campamento.
     * @param temprana              Booleano indicando si es inscripción temprana.
     * @param necesidadesEspeciales Booleano indicando si hay necesidades
     *                              especiales.
     * @return true si se añadió la inscripción con éxito, false si no.
     */
    public Boolean addInscripcion(int idParticipante, int idCampamento, boolean temprana,
            boolean necesidadesEspeciales) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == idCampamento && inscripcion.getId_Participante() == idParticipante) {
                System.out.println("El participante ya está inscrito en este campamento.");
                return false;
            }
        }

        Campamento campamento = GestorCampamentos.getInstance(null).getCampamento(idCampamento);
        

        if (campamento == null) {
            System.out.println("El campamento con ID " + idCampamento + " no existe.");
            return false;
        }

        InscripcionCreator creator;
        if (temprana) {
            creator = new Temprana();
        } else {
            creator = new Tardia();
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
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == id_Campamento
                    && inscripcion.getId_Participante() == id_Participante) {
                inscripciones.remove(inscripcion);
                System.out.println("Inscripción eliminada con éxito.");
                return true;
            }
        }
        System.out.println("No se encontró una inscripción con ID " + id_Participante + " en el campamento con ID "
                + id_Campamento);
        return false;
    }

    /**
     * Obtiene las inscripciones.
     * 
     * @return ArrayList de inscripciones.
     */
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
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
