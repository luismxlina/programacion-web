package es.uco.pw.classes.inscripcion;

import java.util.Date;

/**
 * Clase que implementa el patrón Creator para la creación de inscripciones
 * tardías.
 * Extiende la clase abstracta InscripcionCreator.
 */
public class Tardia extends InscripcionCreator {

	private static final boolean ISCANCELABLE = false;

	/**
	 * Registra una nueva inscripción completa tardía.
	 *
	 * @param participante Identificador del participante.
	 * @param campamento   Identificador del campamento.
	 * @param fecha        Fecha de la inscripción.
	 * @return Nueva inscripción completa tardía.
	 */

	public Inscripcion registrarInscripcionCompleta(int participante, int campamento, Date fecha) {
		return new InscripcionCompleta(participante, campamento, fecha, ISCANCELABLE);

	}

	/**
	 * Registra una nueva inscripción parcial tardía.
	 *
	 * @param participante Identificador del participante.
	 * @param campamento   Identificador del campamento.
	 * @param fecha        Fecha de la inscripción.
	 * @return Nueva inscripción parcial tardía.
	 */
	public Inscripcion registrarInscripcionParcial(int participante, int campamento, Date fecha) {
		return new InscripcionParcial(participante, campamento, fecha, ISCANCELABLE);

	}

}
