package es.uco.pw.business.inscripcion.handler;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.inscripcion.dto.inscripcion.InscripcionDTO;
import es.uco.pw.business.users.dto.monitor.MonitorDTO;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCompleta;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCreator;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionParcial;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTardia;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTemprana;
import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.business.users.models.monitor.Monitor;
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

    public Boolean buscarInscripcion(int idParticipante, int idCampamento) {
        return inscripcionDAO.get(idParticipante, idCampamento) != null;
    }

    public Boolean addInscripcion(int idParticipante, int idCampamento, boolean completa,
            boolean necesidadesEspeciales) {

        if (buscarInscripcion(idParticipante, idCampamento)) {
            System.out.println("El participante ya está inscrito en este campamento.\n");
            return false;
        }

        if (!GestorAsistentes.getInstance().buscarAsistente(idParticipante)) {
            System.out.println("El participante no existe\n");
            return false;
        }

        if (!GestorCampamentos.getInstance().buscarCampamento(idCampamento)) {
            System.out.println("El campamento con ID " + idCampamento + " no existe.");
            return false;
        }

        InscripcionCreator creator;

        Boolean temprana = GestorCampamentos.getInstance().getCampamento(idCampamento).getFechaInicio().getTime()
                - new Date().getTime() >= 15 * 24 * 60 * 60 * 1000;

        if (temprana) {
            creator = new InscripcionTemprana();
        } else {
            creator = new InscripcionTardia();
        }

        Inscripcion nuevaInscripcion;
        if (completa) {
            nuevaInscripcion = creator.registrarInscripcionCompleta(idParticipante, idCampamento, new Date());
        } else {
            nuevaInscripcion = creator.registrarInscripcionParcial(idParticipante, idCampamento, new Date());
        }

        if(necesidadesEspeciales) {

            Boolean aux = GestorCampamentos.getInstance().buscarMonitorEspecialCampamento(idCampamento);

            if(!aux) {
                ArrayList<Monitor> monitores = GestorCampamentos.getInstance().getMonitores();
                for(Monitor monitor : monitores) {
                    if(monitor.getEsEducador()) {
                        MonitorDTO monitorAux = new MonitorDTO(monitor);
                        GestorCampamentos.getInstance().asociarMonitorCampamento(monitorAux, idCampamento);
                        break;
                    }
                }
            }
        }

        // asistente.addInscripcion(nuevaInscripcion);
        inscripcionDAO.insert(new InscripcionDTO(nuevaInscripcion, completa));

        System.out.println("Inscripción creada con éxito.");

        return true;
    }

    public Boolean deleteInscripcion(int id_Participante, int id_Campamento) {

        if (inscripcionDAO.delete(id_Participante, id_Campamento)) {
            return true;
        }
        return false;
    }

    public ArrayList<Inscripcion> getInscripciones() {

        ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
        getInscripcionesParciales(inscripciones);
        getInscripcionesCompletas(inscripciones);
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesParciales() {

        ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
        Boolean cancelable = false;
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();

        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();

        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }

        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesParciales()) {

            
                Date fechaInscripcion = inscripcionDTO.getFechaInscripcion();
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionParcial(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));
        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesCampamento(Integer idCampamento) {
        ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
        for (InscripcionDTO inscripcion : inscripcionDAO.getAllInscripcionesCampamento(idCampamento)) {
              boolean tipoInscripcion = Boolean.parseBoolean(inscripcion.getTipoInscripcion());
            if (tipoInscripcion) {
                inscripciones.add(new InscripcionCompleta(inscripcion.getAsistenteId(), inscripcion.getCampamentoId(),
                        inscripcion.getFechaInscripcion(), false));
            } else {
                inscripciones.add(new InscripcionParcial(inscripcion.getAsistenteId(), inscripcion.getCampamentoId(),
                        inscripcion.getFechaInscripcion(), false));
            }
        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesParciales(ArrayList<Inscripcion> inscripciones) {
        Boolean cancelable = false;
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();
        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }
        //
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesParciales()) {

                Date fechaInscripcion =inscripcionDTO.getFechaInscripcion();
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionParcial(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));
           

        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesCompletas() {
        ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
        Boolean cancelable = false;
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();
        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }
        //
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesCompletas()) {

            
                Date fechaInscripcion = inscripcionDTO.getFechaInscripcion();
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionCompleta(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));

        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesCompletas(ArrayList<Inscripcion> inscripciones) {
        Boolean cancelable = false;
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();
        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }
        //
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesCompletas()) {

            
                Date fechaInscripcion = inscripcionDTO.getFechaInscripcion();
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionCompleta(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));

        }
        return inscripciones;
    }

    public ArrayList<Campamento> getCampamentosDisponibles() {
        ArrayList<Campamento> campamentos = new ArrayList<Campamento>();
        for (Campamento campamento : GestorCampamentos.getInstance().getCampamentos()) {
            if (campamento.getMax_asistentes() > 0) {
                campamentos.add(campamento);
            }
        }
        return campamentos;
    }

    public int contarInscripciones(int idCampamento) {
        return inscripcionDAO.count(idCampamento);
    }
}
