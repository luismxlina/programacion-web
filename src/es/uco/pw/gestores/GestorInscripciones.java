package es.uco.pw.gestores;

import es.uco.pw.classes.asistente.Asistente;
import es.uco.pw.classes.campamento.Campamento;
import es.uco.pw.classes.inscripcion.*;
import es.uco.pw.classes.monitor.Monitor;

import java.util.ArrayList;
import java.util.Date;

public class GestorInscripciones {

    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Campamento> campamentos;

    private static GestorInscripciones instance = null;

    private GestorInscripciones(ArrayList<Inscripcion> inscripciones, ArrayList<Campamento> campamentos) {
        this.inscripciones = inscripciones;
        this.campamentos = campamentos;
    }

    public static GestorInscripciones getInstance(ArrayList<Inscripcion> inscripciones, ArrayList<Campamento> campamentos) {
        if (instance == null) {
            instance = new GestorInscripciones(inscripciones, campamentos);
        }
        return instance;
    }

    public Boolean mostrarInscripciones() {
        for (Inscripcion inscripcion : inscripciones) {
            System.out.println(inscripcion.toString());
        }
        return false;
    }

    public Boolean mostrarInscripcion(int idParticipante, int idCampamento) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == idCampamento && inscripcion.getId_Participante() == idParticipante) {
                System.out.println(inscripcion.toString());
                return true;
            }
        }
        System.out.println("No se encontró una inscripción para el participante con ID " + idParticipante + " en el campamento con ID " + idCampamento);
        return false;
    }

    public Boolean addInscripcion(int idParticipante, int idCampamento, boolean temprana, boolean necesidadesEspeciales) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == idCampamento && inscripcion.getId_Participante() == idParticipante) {
                System.out.println("El participante ya está inscrito en este campamento.");
                return false;
            }
        }

        Campamento campamento = null;
        for (Campamento c : campamentos) {
            if (c.getIdentificador() == idCampamento) {
                campamento = c;
                break;
            }
        }

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

    public Boolean editarInscripcion(int id_Participante, int id_Campamento) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == id_Campamento && inscripcion.getId_Participante() == id_Participante) {
                // Realizar edición de la inscripción
                // ...
                System.out.println("Inscripción editada con éxito.");
                return true;
            }
        }
        System.out.println("No se encontró una inscripción con ID " + id_Participante + " en el campamento con ID " + id_Campamento);
        return false;
    }

    public Boolean deleteInscripcion(int id_Participante, int id_Campamento) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getId_Campamento() == id_Campamento && inscripcion.getId_Participante() == id_Participante) {
                inscripciones.remove(inscripcion);
                System.out.println("Inscripción eliminada con éxito.");
                return true;
            }
        }
        System.out.println("No se encontró una inscripción con ID " + id_Participante + " en el campamento con ID " + id_Campamento);
        return false;
    }

    public void consultarCampamentosDisponibles() {
        Date fechaActual = new Date();
        for (Campamento campamento : campamentos) {
            if (campamento.getFechaInicio().after(fechaActual)) {
                int cupoDisponible = campamento.getMax_asistentes() - contarInscripciones(campamento.getIdentificador());
                if (cupoDisponible > 0) {
                    System.out.println("Campamento con ID " + campamento.getIdentificador() + " disponible. Cupo restante: " + cupoDisponible);
                }
            }
        }
    }

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
