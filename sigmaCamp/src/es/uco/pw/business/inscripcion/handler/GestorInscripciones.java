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
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCompleta;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionCreator;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionParcial;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTardia;
import es.uco.pw.business.inscripcion.models.inscripcion.factory.InscripcionTemprana;
import es.uco.pw.business.users.handler.GestorAsistentes;
import es.uco.pw.business.users.models.asistente.Asistente;
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

    // Métodos

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

        Asistente asistente = GestorAsistentes.getInstance(null).getAsistente(idParticipante);

        Inscripcion nuevaInscripcion;
        if (completa) {
            nuevaInscripcion = creator.registrarInscripcionCompleta(idParticipante, idCampamento, new Date());
        } else {
            nuevaInscripcion = creator.registrarInscripcionParcial(idParticipante, idCampamento, new Date());
        }

        if(necesidadesEspeciales) {
            Boolean aux = GestorCampamentos.getInstance().buscarMonitorEspecialCampamento(idCampamento);

            if(!aux) {
                ArrayList<Monitor> = GestorCampamentos.getInstance().getMonitores();
                for(Monitor monitor : monitores) {
                    if(monitor.getEsEducador()) {
                        GestorCampamentos.getInstance().asociarMonitorCampamento(monitor.getIdentificador(), idCampamento);
                        break;
                    }
                }
            }
        }

        // asistente.addInscripcion(nuevaInscripcion);
        inscripcionDAO.add(nuevaInscripcion);

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
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();

        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();

        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }

        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesParciales()) {

            try {
                Date fechaInscripcion = formatoFecha.parse(inscripcionDTO.getFechaInscripcion());
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionParcial(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesParciales(ArrayList<Inscripcion> inscripciones) {
        Boolean cancelable = false;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();
        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }
        //
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesParciales()) {

            try {
                Date fechaInscripcion = formatoFecha.parse(inscripcionDTO.getFechaInscripcion());
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionParcial(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesCompletas() {
        ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
        Boolean cancelable = false;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();
        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }
        //
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesCompletas()) {

            try {
                Date fechaInscripcion = formatoFecha.parse(inscripcionDTO.getFechaInscripcion());
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionCompleta(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return inscripciones;
    }

    public ArrayList<Inscripcion> getInscripcionesCompletas(ArrayList<Inscripcion> inscripciones) {
        Boolean cancelable = false;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Campamento> campamentos = GestorCampamentos.getInstance().getCampamentos();
        // Mapea cada id de campamento con su fecha de inicio
        HashMap<Integer, Date> fechasInicioCampamentos = new HashMap<>();
        for (Campamento campamento : campamentos) {
            fechasInicioCampamentos.put(campamento.getIdentificador(), campamento.getFechaInicio());
        }
        //
        for (InscripcionDTO inscripcionDTO : inscripcionDAO.getAllInscripcionesCompletas()) {

            try {
                Date fechaInscripcion = formatoFecha.parse(inscripcionDTO.getFechaInscripcion());
                Date fechaInicioCampamento = fechasInicioCampamentos.get(inscripcionDTO.getCampamentoId());

                long diferenciaEnMilisegundos = fechaInicioCampamento.getTime() - fechaInscripcion.getTime();
                long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                cancelable = diferenciaEnDias >= 15;
                inscripciones.add(new InscripcionCompleta(inscripcionDTO.getAsistenteId(),
                        inscripcionDTO.getCampamentoId(), fechaInscripcion, cancelable));
            } catch (ParseException e) {
                e.printStackTrace();
            }

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
