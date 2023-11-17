package es.uco.pw.business.campamento.handler;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;

import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.campamento.dto.monitor.MonitorDTO;
import es.uco.pw.business.campamento.models.monitor.Monitor;
import es.uco.pw.data.dao.ActividadDAO;
import es.uco.pw.data.dao.CampamentoDAO;
import es.uco.pw.data.dao.MonitorDAO;

public class GestorCampamentos {

    private static GestorCampamentos instance = null;
    private static CampamentoDAO campamentoDAO;
    private static MonitorDAO monitorDAO;
    private static ActividadDAO actividadDAO;

    /**
     * Devuelve la instancia única de la clase GestorCampamentos.
     * Si la instancia no existe, la crea e inicializa los DAOs.
     * 
     * @return la instancia única de la clase GestorCampamentos
     */
    public static GestorCampamentos getInstance() {
        if (instance == null) {
            instance = new GestorCampamentos();
            campamentoDAO = new CampamentoDAO();
            monitorDAO = new MonitorDAO();
            actividadDAO = new ActividadDAO();
        }
        return instance;
    }

    /**
     * Devuelve un campamento específico.
     * 
     * @param id el ID del campamento
     * @return el campamento
     */
    public Campamento getCampamento(int id) {
        return new Campamento(campamentoDAO.get(id));
    }

    /**
     * Devuelve todos los campamentos.
     * 
     * @return una lista de todos los campamentos
     */
    public ArrayList<Campamento> getCampamentos() {
        ArrayList<Campamento> campamentos = new ArrayList<Campamento>();
        for (CampamentoDTO campamento : campamentoDAO.getAll()) {
            campamentos.add(new Campamento(campamento));
        }
        return campamentos;
    }

    /**
     * Da de alta un nuevo campamento.
     * 
     * @param nuevoCampamento el nuevo campamento
     * @return true si el campamento se ha dado de alta correctamente, false en caso
     *         contrario
     */
    public Boolean altaCampamento(Campamento nuevoCampamento) {
        if (buscarCampamento(nuevoCampamento.getIdentificador())) {
            return false;
        }
        campamentoDAO.insert(new CampamentoDTO(nuevoCampamento));
        return true;
    }

    /**
     * Busca un campamento específico.
     * 
     * @param id el ID del campamento
     * @return true si el campamento existe, false en caso contrario
     */
    public Boolean buscarCampamento(Integer id) {
        return campamentoDAO.get(id) != null;
    }

    /**
     * Elimina un campamento específico.
     * 
     * @param id
     * @return true si el campamento se ha eliminado correctamente, false en caso
     *         contrario
     */

    public Boolean borrarCampamento(Integer id) {
        if (!buscarCampamento(id)) {
            return false;
        }
        campamentoDAO.delete(id);
        return true;
    }

    /**
     * Devuelve todos los IDs de los campamentos.
     * 
     * @return una lista de todos los IDs de los campamentos
     */
    public ArrayList<Integer> getAllIdsCampamentos() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids = campamentoDAO.getAllIdsCampamentos();
        return ids;
    }

    /**
     * Devuelve una actividad específica.
     * 
     * @param nombreActividad el nombre de la actividad
     * @return la actividad
     */
    public Actividad getActividad(String nombreActividad) {
        return new Actividad(actividadDAO.get(nombreActividad));
    }

    /**
     * Devuelve todas las actividades.
     * 
     * @return
     */
    public ArrayList<Actividad> getActividades() {
        ArrayList<Actividad> actividades = new ArrayList<Actividad>();
        for (ActividadDTO actividad : actividadDAO.getAll()) {
            actividades.add(new Actividad(actividad));
        }
        return actividades;
    }

    /**
     * Devuelve una actividad específica de un campamento específico.
     * 
     * @param nombreActividad
     * @param idCampamento
     * @return la actividad
     */
    public Actividad getActividadCampamento(String nombreActividad, Integer idCampamento) {
        ActividadDTO actividad = actividadDAO.getActividadCampamento(nombreActividad, idCampamento);
        if (actividad == null) {
            return null;
        }
        return new Actividad(actividad);
    }

    /**
     * Devuelve las actividades de un campamento específico.
     * 
     * @param idCampamento el ID del campamento
     * @return una lista de las actividades del campamento
     */
    public ArrayList<Actividad> getActividadesCampamento(int idCampamento) {
        ArrayList<Actividad> actividades = new ArrayList<Actividad>();
        for (ActividadDTO actividad : actividadDAO.getActividadesCampamento(idCampamento)) {
            actividades.add(new Actividad(actividad));
        }
        return actividades;
    }

    /**
     * Compueba si una actividad existe.
     * 
     * @param nombreActividad
     * @return true si la actividad existe, false en caso contrario
     */
    public Boolean buscarActividad(String nombreActividad) {
        return actividadDAO.get(nombreActividad) != null;
    }

    /**
     * Comprueba si una actividad existe en un campamento específico.
     * 
     * @param nombreActividad
     * @param idCampamento
     * @return true si la actividad existe en el campamento, false en caso contrario
     */
    public Boolean buscarActividadCampamento(String nombreActividad, int idCampamento) {
        return actividadDAO.getActividadCampamento(nombreActividad, idCampamento) != null;
    }

    /**
     * Añade una actividad específica.
     * 
     * @param id el ID del campamento
     * @return true si el campamento se ha borrado correctamente, false en caso
     *         contrario
     */
    public Boolean addActividad(Actividad actividad) {
        if (buscarActividad(actividad.getNombreActividad())) {
            return false;
        }
        actividadDAO.insert(new ActividadDTO(actividad));
        return true;
    }

    /**
     * Asocia una actividad a un campamento.
     * 
     * @param actividad    el nombre de la actividad
     * @param idCampamento el ID del campamento
     * @return true si la actividad se ha asociado correctamente, false en caso
     *         contrario
     */
    public Boolean asociarActividadCampamento(String actividad, int idCampamento) {
        if (!buscarActividad(actividad)) {
            return false;
        }
        if (buscarActividadCampamento(actividad, idCampamento) != null) {
            return false;
        }
        campamentoDAO.insertCampamentoActividad(idCampamento, actividad);
        return true;
    }

    /**
     * Borra una actividad específica.
     * 
     * @param nombreActividad el nombre de la actividad
     * @return true si la actividad se ha borrado correctamente, false en caso
     *         contrario.
     */
    public Boolean borrarActividad(String nombreActividad) {
        if (!buscarActividad(nombreActividad)) {
            return false;
        }
        actividadDAO.delete(nombreActividad);
        return true;
    }

    /**
     * Borra una actividad específica de un campamento específico.
     * 
     * @param nombreActividad el nombre de la actividad
     * @param idCampamento    el ID del campamento
     * @return true si la actividad se ha borrado correctamente, false en caso
     *         contrario
     */
    public Boolean borrarActividadCampamento(String nombreActividad, int idCampamento) {
        if (!buscarActividadCampamento(nombreActividad, idCampamento)) {
            return false;
        }
        actividadDAO.deleteActividadCampamento(nombreActividad, idCampamento);
        return true;
    }

    /**
     * Devuelve un monitor específico.
     * 
     * @param id el ID del monitor
     * @return el monitor
     */
    public Monitor getMonitor(int id) {
        return new Monitor(monitorDAO.get(id));
    }

    /**
     * Devuelve todos los monitores.
     * 
     * @return una lista de todos los monitores
     */
    public ArrayList<Monitor> getMonitores() {
        ArrayList<Monitor> monitores = new ArrayList<Monitor>();
        for (MonitorDTO monitor : monitorDAO.getAll()) {
            monitores.add(new Monitor(monitor));
        }
        return monitores;
    }

    /**
     * Inserta un nuevo monitor.
     * 
     * @param monitor
     * @return true si el monitor se ha insertado correctamente, false en caso
     *         contrario
     */
    public Boolean addMonitor(Monitor monitor) {
        if (buscarMonitor(monitor.getIdentificador())) {
            return false;
        }
        monitorDAO.insert(new MonitorDTO(monitor));
        return true;
    }

    /**
     * Busca un monitor específico.
     * 
     * @param id el ID del monitor
     * @return true si el monitor existe, false en caso contrario
     */
    public Boolean buscarMonitor(Integer id) {
        return monitorDAO.get(id) != null;
    }

    /**
     * Busca a un monitor en un campamento específico.
     * 
     * @param idMonitor
     * @param idCampamento
     * @return
     */
    public Boolean buscarMonitorCampamento(Integer idMonitor, Integer idCampamento) {
        return monitorDAO.getMonitorCampamento(idMonitor, idCampamento) != null;
    }

    

    /**
     * Asocia un monitor a una actividad.
     * 
     * @param idMonitor       el ID del monitor
     * @param nombreActividad el nombre de la actividad
     * @param idCampamento    el ID del campamento
     * @return true si el monitor se ha asociado correctamente, false en caso
     *         contrario
     */
    public Boolean asociarMonitorActividad(int idMonitor, String nombreActividad, int idCampamento) {
        MonitorDTO monitor = monitorDAO.get(idMonitor);
        if (monitor.getEsEducador()) {
            System.out.println("El monitor de educacion especial no se puede asociar a una actividad...");
            return false;
        }

        if (!actividadDAO.exists(nombreActividad, idCampamento)) {
            System.out.println("La actividad no existe en el campamento indicado...");
            return false;
        }
        actividadDAO.insertActividadMonitor(nombreActividad, idMonitor);
        return true;
    }

    public Boolean addMonitor(Monitor monitor, Integer idCampamento) {
        ArrayList<MonitorDTO> monitores = monitorDAO.getAll();
        for (MonitorDTO m : monitores) {
            if (m.getIdentificador() == monitor.getIdentificador()) {
                return false;
            }
        }

        monitorDAO.insert(new MonitorDTO(monitor));
        campamentoDAO.insertCampamentoMonitor(idCampamento, monitor.getIdentificador());
        return true;
    }

    /**
     * Busca un monitor especial en un campamento específico.
     * 
     * @param idCampamento el ID del campamento
     * @return true si el monitor especial existe, false en caso contrario
     */
    public Boolean buscarMonitorEspecialCampamento(Integer idCampamento) {

    }

    public ArrayList<Integer> getAllIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (CampamentoDTO campamento : campamentoDAO.getAll()) {
            ids.add(campamento.getIdentificador());
        }
        return ids;
    }



    /**
     * Asocia un monitor a un campamento.
     * 
     * @param monitorDTO   el monitor a asociar
     * @param idCampamento el ID del campamento
     * @return la lista de monitores después de la asociación
     */
    public ArrayList<Monitor> asociarMonitorCampamento(MonitorDTO monitorDTO, int idCampamento) {
        CampamentoDAO campamentoDAO = new CampamentoDAO();
        campamentoDAO.insertCampamentoMonitor(idCampamento, monitorDTO.getIdentificador());
        return getMonitores();
    }
}
