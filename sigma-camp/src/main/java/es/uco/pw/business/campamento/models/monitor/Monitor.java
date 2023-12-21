package es.uco.pw.business.campamento.models.monitor;

import java.io.Serializable;

import es.uco.pw.business.campamento.dto.monitor.MonitorDTO;

/**
 * La clase Monitor representa a una persona encargada de dirigir actividades en
 * un campamento.
 * Implementa la interfaz Serializable para permitir su almacenamiento y
 * recuperación.
 */

public class Monitor implements Serializable {
	private int identificador;
	private String nombre;
	private String apellidos;
	private Boolean esEducador;

	/**
	 * Constructor parametrizado que inicializa un objeto Monitor con información
	 * completa.
	 *
	 * @param id Identificador único del monitor.
	 * @param n  Nombre del monitor.
	 * @param a  Apellidos del monitor.
	 * @param e  Indica si el monitor es un educador especial.
	 */

	public Monitor(int id, String n, String a, Boolean e) {
		this.identificador = id;
		this.nombre = n;
		this.apellidos = a;
		this.esEducador = e;
	}

	/**
	 * Constructor vacío que inicializa un objeto Monitor sin información.
	 */

	public Monitor() {

	}

	/**
	 * Constructor que inicializa un objeto Monitor con el identificador
	 * proporcionado.
	 *
	 * @param id Identificador único del monitor.
	 */

	public Monitor(int id) {
		this.identificador = id;
	}

	/**
	 * Constructor que inicializa un objeto Monitor con la información
	 * proporcionada.
	 *
	 * @param monitor Objeto MonitorDTO con la información del monitor.
	 */
	public Monitor(String nombre, String apellidos, Boolean esEducador) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.esEducador = esEducador;
	}

	public Monitor(MonitorDTO monitor) {
		this.identificador = monitor.getIdentificador();
		this.nombre = monitor.getNombre();
		this.apellidos = monitor.getApellidos();
		this.esEducador = monitor.getEsEducador();
	}

	// Métodos de acceso (getters y setters)

	/**
	 * Obtiene el identificador del monitor.
	 *
	 * @return El identificador del monitor.
	 */

	public int getIdentificador() {
		return identificador;
	}

	/**
	 * Establece el identificador del monitor.
	 *
	 * @param identificador El identificador único del monitor.
	 */

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	/**
	 * Obtiene el nombre del monitor.
	 *
	 * @return El nombre del monitor.
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del monitor.
	 *
	 * @param nombre El nombre del monitor.
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene los apellidos del monitor.
	 *
	 * @return Los apellidos del monitor.
	 */

	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece los apellidos del monitor
	 *
	 * @param apellidos Los apellidos del monitor.
	 */

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Verifica si el monitor es un educador especial.
	 *
	 * @return true si el monitor es un educador especial, false en caso contrario.
	 */

	public Boolean getEsEducador() {
		return esEducador;
	}

	/**
	 * Establece si el monitor es un educador especial.
	 *
	 * @param esEducador Indica si el monitor es un educador especial.
	 */

	public void setEsEducador(Boolean esEducador) {
		this.esEducador = esEducador;
	}

	/**
	 * Genera una representación en formato de cadena del objeto Monitor.
	 *
	 * @return Una cadena que representa el objeto Monitor.
	 */

	@Override
	public String toString() {
		return "Monitor [identificador=" + identificador + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", esEducador=" + esEducador + "]";
	}

}
