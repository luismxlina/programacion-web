package es.uco.pw.business.campamento.models.actividad;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalTime;

import es.uco.pw.business.campamento.models.monitor.Monitor;
import es.uco.pw.business.campamento.dto.actividad.ActividadDTO;
import es.uco.pw.business.campamento.models.actividad.Horario;

/**
 * Clase que representa una actividad.
 */
public class Actividad implements Serializable {

	// Atributos de la actividad
	private String nombreActividad;
	private NivelEducativo nivel;
	private Horario hora;
	private int maxParticipantes;
	private int numMonitores;
	private ArrayList<Monitor> monitores;

	/**
	 * Constructor vacío para una actividad.
	 * Inicializa los valores predeterminados.
	 */
	public Actividad() {
		this.nombreActividad = "";
		this.nivel = NivelEducativo.INFANTIL;
		this.hora = Horario.MAÑANA;
		this.maxParticipantes = 200;
		this.numMonitores = 20;
		this.monitores = new ArrayList<Monitor>();

	}

	/**
	 * Constructor para una actividad con parámetros personalizados.
	 * 
	 * @param nombre           Nombre de la actividad.
	 * @param nivel            Nivel educativo al que está dirigida la actividad.
	 * @param hora             Horario de la actividad (Mañana o Tarde).
	 * @param maxParticipantes Número máximo de participantes permitidos.
	 * @param numMonitores     Número de monitores necesarios para la actividad.
	 */
	public Actividad(String nombre, NivelEducativo nivel, Horario hora, int maxParticipantes, int numMonitores) {
		this.nombreActividad = nombre;
		this.nivel = nivel;
		this.hora = hora;
		this.maxParticipantes = maxParticipantes;
		this.numMonitores = numMonitores;
		this.monitores = new ArrayList<Monitor>();
	}

	public Actividad(ActividadDTO actividad) {
		this.nombreActividad = actividad.getNombreActividad();
		this.nivel = actividad.getNivel();
		this.hora = actividad.getHora();
		this.maxParticipantes = actividad.getMaxParticipantes();
		this.numMonitores = actividad.getNumMonitores();
		this.monitores = new ArrayList<Monitor>();
	}

	public Actividad(ActividadDTO actividad, ArrayList<Monitor> monitores) {
		this.nombreActividad = actividad.getNombreActividad();
		this.nivel = actividad.getNivel();
		this.hora = actividad.getHora();
		this.maxParticipantes = actividad.getMaxParticipantes();
		this.numMonitores = actividad.getNumMonitores();
		this.monitores = monitores;
	}
	// Getters y Setters

	/**
	 * Obtiene el nombre de la actividad.
	 * 
	 * @return El nombre de la actividad.
	 */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
	 * Establece el nombre de la actividad.
	 * 
	 * @param nombreActividad El nuevo nombre de la actividad.
	 */
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	/**
	 * Obtiene el nivel educativo al que está dirigida la actividad.
	 * 
	 * @return El nivel educativo.
	 */
	public NivelEducativo getNivel() {
		return nivel;
	}

	/**
	 * Establece el nivel educativo de la actividad.
	 * 
	 * @param nivel El nuevo nivel educativo.
	 */
	public void setNivel(NivelEducativo nivel) {
		this.nivel = nivel;
	}

	/**
	 * Obtiene el horario de la actividad.
	 * 
	 * @return El horario de la actividad (Mañana o Tarde).
	 */
	public Horario getHora() {
		return hora;
	}

	/**
	 * Establece el horario de la actividad.
	 * 
	 * @param hora El nuevo horario (Mañana o Tarde).
	 */
	public void setHora(Horario hora) {
		this.hora = hora;
	}

	/**
	 * Obtiene el número máximo de participantes permitidos en la actividad.
	 * 
	 * @return El número máximo de participantes.
	 */
	public int getMaxParticipantes() {
		return maxParticipantes;
	}

	/**
	 * Establece el número máximo de participantes permitidos en la actividad.
	 * 
	 * @param maxParticipantes El nuevo número máximo de participantes.
	 */
	public void setMaxParticipantes(int maxParticipantes) {
		this.maxParticipantes = maxParticipantes;
	}

	/**
	 * Obtiene el número de monitores necesarios para la actividad.
	 * 
	 * @return El número de monitores necesarios.
	 */
	public int getNumMonitores() {
		return numMonitores;
	}

	/**
	 * Establece el número de monitores necesarios para la actividad.
	 * 
	 * @param numMonitores El nuevo número de monitores necesarios.
	 */
	public void setNumMonitores(int numMonitores) {
		this.numMonitores = numMonitores;
	}

	/**
	 * Obtiene la lista de monitores asociados a la actividad.
	 * 
	 * @return La lista de monitores.
	 */
	public ArrayList<Monitor> getMonitores() {
		return monitores;
	}

	/**
	 * Establece la lista de monitores asociados a la actividad.
	 * 
	 * @param monitores La nueva lista de monitores.
	 */
	public void setMonitores(ArrayList<Monitor> monitores) {
		this.monitores = monitores;
	}

	/**
	 * Devuelve una representación en forma de texto de la actividad.
	 * 
	 * @return Una cadena de texto que representa la actividad.
	 */
	@Override
	public String toString() {
		return "Actividad [nombreActividad=" + nombreActividad + ", nivel=" + nivel + ", hora=" + hora
				+ ", maxParticipantes=" + maxParticipantes + ", numMonitores=" + numMonitores + ", monitores="
				+ monitores + "]";
	}

	// Otros métodos

	/**
	 * Asocia un monitor a la actividad.
	 * 
	 * @param m El monitor a asociar.
	 * @return true si se asocia correctamente, false si no hay espacio o el monitor
	 *         ya está asociado.
	 */
	public Boolean asociarMonitor(Monitor m) {
		if (this.monitores.size() == this.numMonitores) {
			return false;
		}

		// Busco el monitor por si estuviese
		/*
		 * 
		 * this.monitores.get(i) = this.monitores[i]
		 */
		for (int i = 0; i < this.monitores.size(); i++) {
			if (this.monitores.get(i).getIdentificador() == m.getIdentificador()) {
				return false;
			}
		}

		this.monitores.add(m);
		return true;
	}
}
