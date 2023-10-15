package es.uco.pw.classes.inscripcion;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

import es.uco.pw.classes.actividad.Actividad;

/**
 * Clase abstracta que representa una inscripción a un campamento.
 * Proporciona atributos y métodos comunes para todas las inscripciones.
 */

public abstract class Inscripcion implements Serializable {

 // Atributos comunes

    /**
     * Identificador del participante asociado a la inscripción.
     */

	private int id_Participante;
	/**
     * Identificador del campamento al que se realiza la inscripción.
     */
	private int id_Campamento;
	/**
     * Fecha en la que se realizó la inscripción.
     */
	private Date fechaInscripcion;
	/**
     * Precio total de la inscripción.
     */
	private Double precio;
	/**
     * Indica si la inscripción es cancelable.
     */

	private Boolean cancelable;

	 // Métodos abstractos

    /**
     * Devuelve una representación en cadena de la inscripción.
     *
     * @return Cadena que representa la inscripción.
     */
	public abstract String toString();

	/**
     * Obtiene el identificador del participante asociado a la inscripción.
     *
     * @return Identificador del participante.
     */
	public int getId_Participante() {
		return id_Participante;
	}

	/**
     * Establece el identificador del participante asociado a la inscripción.
     *
     * @param id_Participante Identificador del participante.
     */
	public void setId_Participante(int id_Participante) {
		this.id_Participante = id_Participante;
	}

	/**
     * Obtiene el identificador del campamento al que se realiza la inscripción.
     *
     * @return Identificador del campamento.
     */
	public int getId_Campamento() {
		return id_Campamento;
	}

	/**
     * Establece el identificador del campamento al que se realiza la inscripción.
     *
     * @param id_Campamento Identificador del campamento.
     */
	public void setId_Campamento(int id_Campamento) {
		this.id_Campamento = id_Campamento;
	}

	/**
     * Obtiene la fecha en la que se realizó la inscripción.
     *
     * @return Fecha de inscripción.
     */
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	/**
     * Establece la fecha en la que se realizó la inscripción.
     *
     * @param fechaInscripcion Fecha de inscripción.
     */
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	 /**
     * Obtiene el precio total de la inscripción.
     *
     * @return Precio de la inscripción.
     */
	public Double getPrecio() {
		return precio;
	}

	/**
     * Establece el precio total de la inscripción.
     *
     * @param precio Precio de la inscripción.
     */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
     * Establece el precio de la inscripción basado en la lista de actividades proporcionada.
     *
     * @param actividades Lista de actividades asociadas a la inscripción.
     */
	public abstract void setPrecio(ArrayList<Actividad> actividades);

	 /**
     * Obtiene si la inscripción es cancelable.
     *
     * @return true si la inscripción es cancelable, false en caso contrario.
     */
	public Boolean getCancelable() {
		return cancelable;
	}

	 /**
     * Establece si la inscripción es cancelable.
     *
     * @param cancelable true si la inscripción es cancelable, false en caso contrario.
     */
	public void setCancelable(Boolean cancelable) {
		this.cancelable = cancelable;
	}

}
