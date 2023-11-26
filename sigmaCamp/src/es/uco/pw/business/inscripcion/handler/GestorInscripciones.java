package es.uco.pw.business.inscripcion.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.*;
import es.uco.pw.data.dao.InscripcionDAO;
import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;

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

    // InscripcionCreator creator;
    // if (temprana) {
    // creator = new InscripcionTemprana();
    // } else {
    // creator = new InscripcionTardia();
    // }
    // Inscripcion nuevaInscripcion;
    // if (completa) {
    // nuevaInscripcion = creator.registrarInscripcionCompleta(idParticipante,
    // idCampamento, new Date());
    // } else {
    // nuevaInscripcion = creator.registrarInscripcionParcial(idParticipante,
    // idCampamento, new Date());
    // }

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

    public ArrayList<Inscripcion> getInscripciones() {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAll()) {
            ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
            // Busca el campamento en el array de campamentos que tenga el id = a
            // inscripcionDTO.getCampamentoId()
            Campamento campamento = campamentos.stream()
                    .filter(c -> c.getIdentificador() == inscripcionDTO.getCampamentoId()).findFirst().orElse(null);
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
            // Busca el campamento en el array de campamentos que tenga el id = a
            // inscripcionDTO.getCampamentoId()
            Campamento campamento = campamentos.stream()
                    .filter(c -> c.getIdentificador() == inscripcionDTO.getCampamentoId()).findFirst().orElse(null);
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

    // public ArrayList<Inscripcion> getInscripciones() {
    // ArrayList<Inscripcion> inscripciones = new ArrayList<>();
    // for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAll()) {
    // Inscripcion inscripcion = Inscripcion(inscripcionDTO);
    // inscripciones.add(inscripcion);
    // }
    // return inscripciones;
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
