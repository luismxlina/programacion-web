package es.uco.pw.business.inscripcion.handler;

import java.util.ArrayList;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.campamento.models.monitor.Monitor;
import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.*;
import es.uco.pw.business.users.handler.asistente.GestorAsistentes;
import es.uco.pw.data.dao.InscripcionDAO;

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

        if (inscripcionDTO.getTipoInscripcion() == TipoInscripcion.COMPLETA) {

            inscripcion = creator.registrarInscripcionCompleta(inscripcionDTO.getAsistenteId(),
                    inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());

        } else {

            inscripcion = creator.registrarInscripcionParcial(inscripcionDTO.getAsistenteId(),
                    inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());

        }

        return inscripcion;
    }

    public ArrayList<Inscripcion> getInscripcionesAsistente(Integer idAsistente) {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAll()) {
            if (inscripcionDTO.getAsistenteId() == idAsistente) {
                ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();

                Campamento campamento = null;
                for (Campamento c : campamentos) {
                    if (c.getIdentificador() == inscripcionDTO.getCampamentoId()) {
                        campamento = c;
                        break;
                    }
                }

                Boolean esTemprana = getEsTemprana(inscripcionDTO.getFechaInscripcion(), campamento.getFechaInicio());
                InscripcionCreator creator;

                if (esTemprana) {
                    creator = new InscripcionTemprana();
                } else {
                    creator = new InscripcionTardia();
                }

                Inscripcion inscripcion;

                if (inscripcionDTO.getTipoInscripcion() == TipoInscripcion.COMPLETA) {
                    inscripcion = creator.registrarInscripcionCompleta(inscripcionDTO.getAsistenteId(),
                            inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());
                } else {
                    inscripcion = creator.registrarInscripcionParcial(inscripcionDTO.getAsistenteId(),
                            inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());
                }
                inscripciones.add(inscripcion);
            }
        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAll()) {
            ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();

            Campamento campamento = null;
            for (Campamento c : campamentos) {
                if (c.getIdentificador() == inscripcionDTO.getCampamentoId()) {
                    campamento = c;
                    break;
                }
            }

            Boolean esTemprana = getEsTemprana(inscripcionDTO.getFechaInscripcion(), campamento.getFechaInicio());
            InscripcionCreator creator;

            if (esTemprana) {
                creator = new InscripcionTemprana();
            } else {
                creator = new InscripcionTardia();
            }

            Inscripcion inscripcion;

            if (inscripcionDTO.getTipoInscripcion() == TipoInscripcion.COMPLETA) {
                inscripcion = creator.registrarInscripcionCompleta(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());
            } else {
                inscripcion = creator.registrarInscripcionParcial(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());
            }
            inscripciones.add(inscripcion);
        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesCampamento(Integer idCampamento) {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesCampamento(idCampamento)) {

            ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();

            Campamento campamento = null;
            for (Campamento c : campamentos) {
                if (c.getIdentificador() == inscripcionDTO.getCampamentoId()) {
                    campamento = c;
                    break;
                }
            }

            Boolean esTemprana = getEsTemprana(inscripcionDTO.getFechaInscripcion(), campamento.getFechaInicio());
            InscripcionCreator creator;

            if (esTemprana) {
                creator = new InscripcionTemprana();
            } else {
                creator = new InscripcionTardia();
            }

            Inscripcion inscripcion;

            if (inscripcionDTO.getTipoInscripcion() == TipoInscripcion.COMPLETA) {
                inscripcion = creator.registrarInscripcionCompleta(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());
            } else {
                inscripcion = creator.registrarInscripcionParcial(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), inscripcionDTO.getFechaInscripcion());
            }
            inscripciones.add(inscripcion);
        }
        return inscripciones;
    }

    public Boolean getEsTemprana(Date fechaInscripcion, Date fechaInicioCampamento) {
        long diffInMillies = Math.abs(fechaInicioCampamento.getTime() - fechaInscripcion.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff > 15;
    }

    public Boolean addInscripcion(Inscripcion inscripcion) throws Exception {
        if (buscarInscripcion(inscripcion.getId_Participante(), inscripcion.getId_Campamento())) {
            throw new Exception("Ya existe la inscripción");
        }

        Integer numInscripciones = inscripcionDAO.getAllInscripcionesCampamento(inscripcion.getId_Campamento()).size();
        Integer numPlazas = GestorCampamentos.getInstance().getCampamento(inscripcion.getId_Campamento())
                .getMaxAsistentes();

        if (numInscripciones >= numPlazas) {
            throw new Exception("No hay plazas disponibles");
        }

        if (GestorAsistentes.getInstance().getAsistente(inscripcion.getId_Participante()).getRequiereAtencion()
                && !GestorCampamentos.getInstance()
                        .buscarMonitoresEspecialesCampamento(inscripcion.getId_Campamento())) {

            ArrayList<Monitor> monitoresEspeciales = GestorCampamentos.getInstance().getMonitoresEspeciales();

            for (Monitor monitor : monitoresEspeciales) {

                if (!GestorCampamentos.getInstance().buscarMonitorCampamento(monitor.getIdentificador(),
                        inscripcion.getId_Campamento())) {

                    if (!GestorCampamentos.getInstance().asociarMonitorCampamento(monitor.getIdentificador(),
                            inscripcion.getId_Campamento())) {

                        return false;
                    }
                    break;
                }
            }
        }

        if (inscripcionDAO.insert(new InscripcionDTO(inscripcion))) {
            return true;
        }
        return false;
    }

    public Boolean buscarInscripcion(int idParticipante, int idCampamento) {
        return inscripcionDAO.get(idParticipante, idCampamento) != null;
    }

    /**
     * 
     * Elimina una inscripción específica.
     * 
     * @param idParticipante
     * @param idCampamento
     * @return true si la inscripción se ha eliminado correctamente, false en caso
     *         contrario
     */
    public Boolean deleteInscripcion(int idParticipante, int idCampamento) {
        if (!buscarInscripcion(idParticipante, idCampamento)) {
            return false;
        }
        if (inscripcionDAO.delete(idParticipante, idCampamento)) {
            return true;
        }
        return false;
    }

}
