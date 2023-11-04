package es.uco.pw.bussines.campamento.models.actividad;

import java.util.ArrayList;

import es.uco.pw.bussines.users.models.monitor.Monitor;

import java.io.Serializable;

/**
 * Clase que representa una actividad.
 */
public class Actividad implements Serializable {

	// Atributos de la actividad
	private String nombreActividad;
	private NivelEducativo nivel;
	private String hora;
	private int max_participantes;
	private int num_monitores;
	private ArrayList<Monitor> monitores;

	/**
     * Constructor vacío para una actividad.
     * Inicializa los valores predeterminados.
     */
	public Actividad() {
		this.nombreActividad = "";
		this.nivel = NivelEducativo.INFANTIL;
		this.hora = "Mañana";
		this.max_participantes = 200;
		this.num_monitores = 20;
		this.monitores = new ArrayList<Monitor>();

	}

	/**
     * Constructor para una actividad con parámetros personalizados.
     * @param nombre Nombre de la actividad.
     * @param nivel Nivel educativo al que está dirigida la actividad.
     * @param hora Horario de la actividad (Mañana o Tarde).
     * @param max_participantes Número máximo de participantes permitidos.
     * @param num_monitores Número de monitores necesarios para la actividad.
     */
	public Actividad(String nombre, NivelEducativo nivel, String hora, int max_participantes, int num_monitores) {
		this.nombreActividad = nombre;
		this.nivel = nivel;
		this.hora = hora;
		this.max_participantes = max_participantes;
		this.num_monitores = num_monitores;
		this.monitores = new ArrayList<Monitor>();
	}

	// Getters y Setters

    /**
     * Obtiene el nombre de la actividad.
     * @return El nombre de la actividad.
     */
	public String getNombreActividad() {
		return nombreActividad;
	}

	/**
     * Establece el nombre de la actividad.
     * @param nombreActividad El nuevo nombre de la actividad.
     */
	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	/**
     * Obtiene el nivel educativo al que está dirigida la actividad.
     * @return El nivel educativo.
     */
	public NivelEducativo getNivel() {
		return nivel;
	}

	/**
     * Establece el nivel educativo de la actividad.
     * @param nivel El nuevo nivel educativo.
     */
	public void setNivel(NivelEducativo nivel) {
		this.nivel = nivel;
	}

	/**
     * Obtiene el horario de la actividad.
     * @return El horario de la actividad (Mañana o Tarde).
     */
	public String getHora() {
		return hora;
	}

	/**
     * Establece el horario de la actividad.
     * @param hora El nuevo horario (Mañana o Tarde).
     */
	public void setHora(String hora) {
		this.hora = hora;
	}

	 /**
     * Obtiene el número máximo de participantes permitidos en la actividad.
     * @return El número máximo de participantes.
     */
	public int getMax_participantes() {
		return max_participantes;
	}

	/**
     * Establece el número máximo de participantes permitidos en la actividad.
     * @param max_participantes El nuevo número máximo de participantes.
     */
	public void setMax_participantes(int max_participantes) {
		this.max_participantes = max_participantes;
	}

	/**
     * Obtiene el número de monitores necesarios para la actividad.
     * @return El número de monitores necesarios.
     */
	public int getNum_monitores() {
		return num_monitores;
	}

	/**
     * Establece el número de monitores necesarios para la actividad.
     * @param num_monitores El nuevo número de monitores necesarios.
     */
	public void setNum_monitores(int num_monitores) {
		this.num_monitores = num_monitores;
	}

	/**
     * Obtiene la lista de monitores asociados a la actividad.
     * @return La lista de monitores.
     */
	public ArrayList<Monitor> getMonitores() {
		return monitores;
	}

	 /**
     * Establece la lista de monitores asociados a la actividad.
     * @param monitores La nueva lista de monitores.
     */
	public void setMonitores(ArrayList<Monitor> monitores) {
		this.monitores = monitores;
	}
	
	/**
     * Devuelve una representación en forma de texto de la actividad.
     * @return Una cadena de texto que representa la actividad.
     */
	@Override
	public String toString() {
		return "Actividad [nombreActividad=" + nombreActividad + ", nivel=" + nivel + ", hora=" + hora
				+ ", max_participantes=" + max_participantes + ", num_monitores=" + num_monitores + ", monitores="
				+ monitores + "]";
	}

	// Otros métodos

    /**
     * Asocia un monitor a la actividad.
     * @param m El monitor a asociar.
     * @return true si se asocia correctamente, false si no hay espacio o el monitor ya está asociado.
     */
	public Boolean asociarMonitor(Monitor m) {
		if (this.monitores.size() == this.num_monitores) {
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
