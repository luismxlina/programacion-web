package es.uco.pw.business.inscripcion.models.inscripcion.factory;

import java.util.Date;

import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;

/**
 * Clase que implementa el patrón Creator para la creación de inscripciones
 * tempranas.
 * Extiende la clase abstracta InscripcionCreator.
 */
public class InscripcionTemprana extends InscripcionCreator {

	private static final boolean ISCANCELABLE = true;

	/**
	 * Registra una nueva inscripción completa temprana.
	 *
	 * @param participante Identificador del participante.
	 * @param campamento   Identificador del campamento.
	 * @param fecha        Fecha de la inscripción.
	 * @return Nueva inscripción completa temprana.
	 */
	public Inscripcion registrarInscripcionCompleta(int participante, int campamento, Date fecha) {
		return new InscripcionCompleta(participante, campamento, fecha, ISCANCELABLE);
	}

	/**
	 * Registra una nueva inscripción parcial temprana.
	 *
	 * @param participante Identificador del participante.
	 * @param campamento   Identificador del campamento.
	 * @param fecha        Fecha de la inscripción.
	 * @return Nueva inscripción parcial temprana.
	 */
	public Inscripcion registrarInscripcionParcial(int participante, int campamento, Date fecha) {
		return new InscripcionParcial(participante, campamento, fecha, ISCANCELABLE);
	}

}
