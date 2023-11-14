package es.uco.pw.business.campamento.handler;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalTime;

import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
import es.uco.pw.business.campamento.dto.campamento.CampamentoDTO;
import es.uco.pw.business.campamento.models.actividad.Actividad;
import es.uco.pw.business.campamento.models.actividad.NivelEducativo;
import es.uco.pw.business.campamento.models.campamento.Campamento;
import es.uco.pw.business.users.dto.monitor.MonitorDTO;
import es.uco.pw.business.users.models.monitor.Monitor;
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
     * Devuelve las actividades de un campamento específico.
     * 
     * @param idCampamento el ID del campamento
     * @return una lista de las actividades del campamento
     */
    public ArrayList<Actividad> getActividades(int idCampamento) {
        ArrayList<Actividad> actividades = new ArrayList<Actividad>();
        for(ActividadDTO actividad : campamentoDAO.getActividadesCampamento(idCampamento)) {
            actividades.add(new Actividad(actividad));
        }
        
        return actividades;
    }

     /**
     * Devuelve todos los campamentos.
     * 
     * @return una lista de todos los campamentos
     */
    public ArrayList<Campamento> getCampamentos() {
       ArrayList<Campamento> campamentos = new ArrayList<Campamento>();
         for(CampamentoDTO campamento : campamentoDAO.getAll()) {
              campamentos.add(new Campamento(campamento));
         }
       return campamentos;
    }


    /**
     * Devuelve todos los monitores.
     * 
     * @return una lista de todos los monitores
     */
    public ArrayList<Monitor> getMonitores() {
        ArrayList<Monitor> monitores = new ArrayList<Monitor>();
        for(MonitorDTO monitor : monitorDAO.getAll()) {
            monitores.add(new Monitor(monitor));
        }
        return monitores;
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
     * Busca un monitor especial en un campamento específico.
     * 
     * @param idCampamento el ID del campamento
     * @return true si el monitor especial existe, false en caso contrario
     */
    public Boolean buscarMonitorEspecialCampamento(Integer idCampamento) {
        for (MonitorDTO monitor : campamentoDAO.getMonitoresCampamento(idCampamento)) {
            if (monitor.getEsEducador()) {
                return true;
            }
        }
        return false;
    }
   
    /**
     * Da de alta un nuevo campamento.
     * 
     * @param nuevoCampamento el nuevo campamento
     * @return true si el campamento se ha dado de alta correctamente, false en caso contrario
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
    public Boolean buscarCampamento(int id) {
        return campamentoDAO.get(id) != null;
    }

   
     /**
     * Busca una actividad en un campamento específico.
     * 
     * @param nombreActividad el nombre de la actividad
     * @param idCampamento el ID del campamento
     * @return true si la actividad existe en el campamento, false en caso contrario
     */
    public Boolean buscarActividadCampamento(String nombreActividad, int idCampamento) {
        for (ActividadDTO actividad : campamentoDAO.getActividadesCampamento(idCampamento)) {
            if (actividad.getNombreActividad().equals(nombreActividad)) {
                return true;
            }
        }
        return false;
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
     * Devuelve una actividad específica.
     * 
     * @param nombreActividad el nombre de la actividad
     * @return la actividad
     */
    public Actividad getActividad(String nombreActividad) {
        return new Actividad(actividadDAO.get(nombreActividad));
    }

    /**
     * Asocia un monitor a una actividad.
     * 
     * @param idMonitor el ID del monitor
     * @param nombreActividad el nombre de la actividad
     * @param idCampamento el ID del campamento
     * @return true si el monitor se ha asociado correctamente, false en caso contrario
     */
    public Boolean asociarMonitorActividad(int idMonitor, String nombreActividad, int idCampamento) {
        MonitorDTO monitor = monitorDAO.get(idMonitor);
        if (monitor.getEsEducador()) {
            System.out.println("El monitor de educacion especial no se puede asociar a una actividad...");
            return false;
        }
                
        if(!actividadDAO.exists(nombreActividad, idCampamento) ){
            System.out.println("La actividad no existe en el campamento indicado...");
            return false;
        }
        actividadDAO.insertActividadMonitor(nombreActividad, idMonitor);
        return true;
    }

    public Boolean addMonitor(Monitor monitor) {
        ArrayList<MonitorDTO> monitores = monitorDAO.getAll();
        for(MonitorDTO m : monitores) {
            if(m.getIdentificador() == monitor.getIdentificador()) {
                return false;
            }
        }
        monitorDAO.insert(new MonitorDTO(monitor));
        return true;
    }
    public Boolean addMonitor(Monitor monitor, Integer idCampamento) {
        ArrayList<MonitorDTO> monitores = monitorDAO.getAll();
        for(MonitorDTO m : monitores) {
            if(m.getIdentificador() == monitor.getIdentificador()) {
                return false;
            }
        }

        monitorDAO.insert(new MonitorDTO(monitor));
        campamentoDAO.insertCampamentoMonitor(idCampamento, monitor.getIdentificador());
        return true;
    }

    public ArrayList<Integer> getAllIds() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (CampamentoDTO campamento : campamentoDAO.getAll()) {
			ids.add(campamento.getIdentificador());
		}
		return ids;
	}

    /**
     * Asocia una actividad a un campamento.
     * 
     * @param actividad el nombre de la actividad
     * @param idCampamento el ID del campamento
     * @return true si la actividad se ha asociado correctamente, false en caso contrario
     */
    public Boolean asociarActividadCampamento(String actividad, int idCampamento) {
        campamentoDAO.insertCampamentoActividad(idCampamento, actividad);
        return true;
    }

    /**
     * Borra un campamento específico.
     * 
     * @param id el ID del campamento
     * @return true si el campamento se ha borrado correctamente, false en caso contrario
     */
    public Boolean borrarActividad(String nombreActividad, Integer idCampamento) {
        ArrayList<ActividadDTO> actividades = actividadDAO.getAll();
        for (ActividadDTO actividad : actividades) {
            if (actividad.getNombreActividad().equals(nombreActividad)) {
                actividadDAO.delete(nombreActividad);
                actividadDAO.deleteActividadCampamento(nombreActividad, idCampamento);
                return true;
            }
        }
        return false;
    }

    /**
     * Añade una actividad específica.
     * 
     * @param id el ID del campamento
     * @return true si el campamento se ha borrado correctamente, false en caso contrario
     */
    public Boolean addActividad(Actividad actividad) {
       ArrayList<ActividadDTO> actividades = actividadDAO.getAll();
         for(ActividadDTO a : actividades) {
              if(a.getNombreActividad().equals(actividad.getNombreActividad())) {
                return false;
              }
         }
        campamentoDAO.insertActividad(new ActividadDTO(actividad));
        return true;
    }

     /**
     * Busca un monitor en un campamento específico.
     * 
     * @param idMonitor el ID del monitor
     * @param idCampamento el ID del campamento
     * @return true si el monitor existe en el campamento, false en caso contrario
     */
    public Boolean buscarMonitorCampamento(int idMonitor, int idCampamento) {
        CampamentoDAO campamentoDAO = new CampamentoDAO();
        ArrayList <MonitorDTO> monitores = campamentoDAO.getCampamentoMonitor(idMonitor, idCampamento);
        for (MonitorDTO monitor : monitores) {
            if (monitor.getIdentificador() == idMonitor) {
                return true;
            }
        }
        return false;
    }

    /**
     * Asocia un monitor a un campamento.
     * 
     * @param monitorDTO el monitor a asociar
     * @param idCampamento el ID del campamento
     * @return la lista de monitores después de la asociación
     */
   public  ArrayList<Monitor> asociarMonitorCampamento(MonitorDTO monitorDTO, int idCampamento) {
        CampamentoDAO campamentoDAO = new CampamentoDAO();
        campamentoDAO.insertCampamentoMonitor(idCampamento, monitorDTO.getIdentificador());
        return getMonitores();
    }

     /**
     * Crea una nueva actividad.
     * 
     * @param teclado el Scanner para la entrada de datos
     * @param nuevaActividad la nueva actividad a crear
     * @param nivel el nivel educativo de la actividad
     * @return la actividad creada
     */
    public static Actividad crearActividad(Scanner teclado, Actividad nuevaActividad, NivelEducativo nivel) {

        String nombreActividad;
        String hora;
        int max_participantes = 0;
        int max_monitores = 0;
        Actividad Actividad = new Actividad();

        System.out.println("Introduzca los datos de la nueva actividad:");

        System.out.print("Nombre de la actividad: ");
        teclado.nextLine();
        nombreActividad = teclado.nextLine();

        System.out.print("Introduzca la hora a la que empieza (HH:MM:SS): ");
        hora = teclado.nextLine();
        // Parsea el string howa a LocalTime
        LocalTime horaLocalTime = LocalTime.parse(hora);

        System.out.print("Introduzca el número máximo de asistentes: ");
        max_participantes = teclado.nextInt();

        System.out.print("Introduzca el número máximo de monitores: ");
        max_monitores = teclado.nextInt();

        nuevaActividad.setNombreActividad(nombreActividad);
        nuevaActividad.setNivel(nivel);
        nuevaActividad.setHora(horaLocalTime);
        nuevaActividad.setMax_participantes(max_participantes);
        nuevaActividad.setNum_monitores(max_monitores);
        return Actividad;
    }
}
