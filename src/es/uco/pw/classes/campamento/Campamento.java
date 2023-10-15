package es.uco.pw.classes.campamento;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

import es.uco.pw.classes.actividad.Actividad;
import es.uco.pw.classes.actividad.NivelEducativo;
import es.uco.pw.classes.monitor.Monitor;

/**
 * Esta clase representa un campamento.
 */
public class Campamento implements Serializable {
	private int identificador;
	private Date fechaInicio;
	private Date fechaFin;
	private NivelEducativo nivel;
	private int max_asistentes;
	private ArrayList<Actividad> actividades;
	private ArrayList<Monitor> monitoresResponsables;

/**
     * Constructor predeterminado para la clase Campamento.
     * Inicializa los atributos con valores predeterminados.
     */
	public Campamento() {
		this.identificador = 0;
		this.fechaInicio = new Date();
		this.fechaFin = new Date();
		this.nivel = null;
		this.max_asistentes = 0;
		this.actividades = new ArrayList<Actividad>();
		this.monitoresResponsables = new ArrayList<Monitor>();

		Actividad actividadPredeterminada = new Actividad("Presentación", nivel, "Mañana", 50, 5);
		this.actividades.add(actividadPredeterminada);

		Monitor director = new Monitor(1, "Director", "Centro", false);
		this.monitoresResponsables.add(director);
	}
	 /**
     * Constructor paramatrizado para la clase Campamento.
     * @param identificador El identificador del campamento.
     * @param fechaInicio La fecha de inicio del campamento.
     * @param fechaFin La fecha de fin del campamento.
     * @param nivel El nivel educativo del campamento.
     * @param max_asistentes El número máximo de asistentes permitidos en el campamento.
     */
	public Campamento(int identificador, Date fechaInicio, Date fechaFin, NivelEducativo nivel, int max_asistentes) {
		this.identificador = identificador;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nivel = nivel;
		this.max_asistentes = max_asistentes;
		this.actividades = new ArrayList<Actividad>();
		this.monitoresResponsables = new ArrayList<Monitor>();

		Actividad actividadPredeterminada = new Actividad("Presentación", nivel, "Mañana", 50, 5);
		this.actividades.add(actividadPredeterminada);

		Monitor director = new Monitor(1, "Director", "Centro", false);
		this.monitoresResponsables.add(director);
	}

	/**
     * Constructor para la clase Campamento que toma solo el identificador.
     * @param identificador El identificador del campamento.
     */
	public Campamento(int identificador) {
		this.identificador = identificador;
		this.fechaInicio = new Date();
		this.fechaFin = new Date();
		this.nivel = null;
		this.max_asistentes = 0;
		this.actividades = new ArrayList<Actividad>();
		this.monitoresResponsables = new ArrayList<Monitor>();

		Actividad actividadPredeterminada = new Actividad("Presentación", nivel, "Mañana", 50, 5);
		this.actividades.add(actividadPredeterminada);

		Monitor director = new Monitor(1, "Director", "Centro", false);
		this.monitoresResponsables.add(director);
	}

	/**
     * Obtiene el identificador del campamento.
     * @return El identificador del campamento.
     */
	public int getIdentificador() {
		return identificador;
	}

	 /**
     * Establece el identificador del campamento.
     * @param identificador El nuevo identificador del campamento.
     */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	/**
     * Obtiene la fecha de inicio del campamento.
     * @return La fecha de inicio del campamento.
     */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	 /**
     * Establece la fecha de inicio del campamento.
     * @param fechaInicio La nueva fecha de inicio del campamento.
     */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	 /**
     * Obtiene la fecha de fin del campamento.
     * @return La fecha de fin del campamento.
     */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
     * Establece la fecha de fin del campamento.
     * @param fechaFin La nueva fecha de fin del campamento.
     */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	 /**
     * Obtiene el nivel educativo del campamento.
     * @return El nivel educativo del campamento.
     */
	public NivelEducativo getNivel() {
		return nivel;
	}

	 /**
     * Establece el nivel educativo del campamento.
     * @param nivel El nuevo nivel educativo del campamento.
     */
	public void setNivel(NivelEducativo nivel) {
		this.nivel = nivel;
	}

	/**
     * Obtiene el número máximo de asistentes permitidos en el campamento.
     * @return El número máximo de asistentes.
     */
	public int getMax_asistentes() {
		return max_asistentes;
	}

	 /**
     * Establece el número máximo de asistentes permitidos en el campamento.
     * @param max_asistentes El nuevo número máximo de asistentes.
     */
	public void setMax_asistentes(int max_asistentes) {
		this.max_asistentes = max_asistentes;
	}

	/**
     * Obtiene la lista de actividades del campamento.
     * @return La lista de actividades.
     */
	public ArrayList<Actividad> getActividades() {
		return actividades;
	}

	 /**
     * Establece la lista de actividades del campamento.
     * @param actividades La nueva lista de actividades.
     */
	public void setActividades(ArrayList<Actividad> actividades) {
		this.actividades = actividades;
	}

	/**
     * Obtiene la lista de monitores responsables del campamento.
     * @return La lista de monitores responsables.
     */
	public ArrayList<Monitor> getMonitoresResponsables() {
		return monitoresResponsables;
	}

	/**
     * Obtiene un monitor por su identificador.
     * @param id El identificador del monitor.
     * @return El monitor correspondiente al identificador o null si no se encuentra.
     */
	public Monitor getMonitor(int id) {
		for (Monitor monitor : this.monitoresResponsables) {
			if (monitor.getIdentificador() == id) {
				return monitor;
			}
		}
		return null;
	}

	/**
     * Obtiene un monitor por su nombre.
     * @param nombre El nombre del monitor.
     * @return El monitor correspondiente al nombre o null si no se encuentra.
     */
	public Monitor getMonitor(String nombre) {
		for (Monitor monitor : this.monitoresResponsables) {
			if (monitor.getNombre() == nombre) {
				return monitor;
			}
		}
		return null;
	}

	 /**
     * Establece la lista de monitores responsables del campamento.
     * @param monitoresResponsables La nueva lista de monitores responsables.
     */
	public void setMonitoresResponsables(ArrayList<Monitor> monitoresResponsables) {
		this.monitoresResponsables = monitoresResponsables;
	}
 	/**
     * Obtiene una representación en forma de cadena de caracteres del campamento.
     * @return La representación en forma de cadena de caracteres del campamento.
     */
    @Override
	public String toString() {
		return "Campamento [identificador=" + identificador + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", nivel=" + nivel + ", max_asistentes=" + max_asistentes + ", actividades=" + actividades
				+ ", monitoresResponsables=" + monitoresResponsables + "]";
	}

	 /**
     * Asocia una nueva actividad al campamento si cumple con los requisitos.
     * @param nuevaActividad La nueva actividad a asociar.
     * @return true si la asociación fue exitosa, false en caso contrario.
     */
	public Boolean asociarActividad(Actividad nuevaActividad) {

		if (this.buscarActividad(nuevaActividad.getNombreActividad()) == true)
			return false;

		if (nuevaActividad.getNivel() != this.nivel) {
			return false;
		}
		this.actividades.add(nuevaActividad);
		for (int i = 0; i < nuevaActividad.getMonitores().size(); i++) {
			if (this.monitoresResponsables.contains(nuevaActividad.getMonitores().get(i)) == false) {
				this.monitoresResponsables.add(nuevaActividad.getMonitores().get(i));
			}
		}

		return true;

	}

	/**
     * Busca una actividad por su nombre.
     * @param nombreActividad El nombre de la actividad a buscar.
     * @return true si la actividad fue encontrada, false en caso contrario.
     */
	public Boolean buscarActividad(String nombreActividad) {
		if (actividades.size() == 0)
			return false;

		for (int i = 0; i < actividades.size(); i++) {
			if (this.actividades.get(i).getNombreActividad() == nombreActividad) {
				return true;
			}
		}

		return false;
	}

	/**
     * Obtiene una actividad por su nombre.
     * @param nombreActividad El nombre de la actividad a obtener.
     * @return La actividad correspondiente al nombre o null si no se encuentra.
     */
	public Actividad obtenerActividad(String nombreActividad) {

		if (this.buscarActividad(nombreActividad) == false)
			return null;

		for (int i = 0; i < actividades.size(); i++) {
			if (this.actividades.get(i).getNombreActividad() == nombreActividad) {
				return this.actividades.get(i);
			}
		}

		return null;
	}

	 /**
     * Borra una actividad del campamento si existe.
     * @param nombreActividad El nombre de la actividad a borrar.
     * @return true si la actividad fue borrada, false en caso contrario.
     */
	public Boolean borrarActividad(String nombreActividad) {
		Actividad actividadAEliminar = null;
		for (Actividad actividad : actividades) {
			if (actividad.getNombreActividad().equals(nombreActividad)) {
				actividadAEliminar = actividad;
				break;
			}
		}

		if (actividadAEliminar == null) {
			return false;
		}

		for (Monitor monitor : actividadAEliminar.getMonitores()) {
			System.out.println("Borrando monitor " + monitor.getNombre());
			monitoresResponsables.remove(monitor);
		}
		System.out.println("Borrando actividad finalizado" + actividadAEliminar.getNombreActividad());
		actividades.remove(actividadAEliminar);

		return true;
	}

	// Métodos privados:

    /**
     * Asocia un nuevo monitor no especial al campamento.
     * @param nuevoMonitor El nuevo monitor a asociar.
     * @return true si la asociación fue exitosa, false en caso contrario.
     */
	private Boolean asociarMonitorNoEspecial(Monitor nuevoMonitor) {
		for (Actividad actividad : actividades) {
			if (!actividad.getMonitores().contains(nuevoMonitor)) {
				return false;
			}
		}
		monitoresResponsables.add(nuevoMonitor);
		return true;
	}

	/**
     * Asocia un nuevo monitor especial al campamento.
     * @param nuevoMonitorEspecial El nuevo monitor especial a asociar.
     * @return true si la asociación fue exitosa, false en caso contrario.
     */
	private Boolean asociarMonitorEspecial(Monitor nuevoMonitorEspecial) {
		for (Actividad actividad : actividades) {
			if (actividad.getMonitores().contains(nuevoMonitorEspecial)) {
				return false;
			}
		}
		monitoresResponsables.add(nuevoMonitorEspecial);
		return true;

	}
	 /**
     * Asocia un monitor al campamento, considerando si es especial o no.
     * @param monitor El monitor a asociar.
     * @return true si la asociación fue exitosa, false en caso contrario.
     */
	public Boolean asociarMonitor(Monitor monitor) {
		return (monitor.getEsEducador()) ? asociarMonitorEspecial(monitor) : asociarMonitorNoEspecial(monitor);
	}
}