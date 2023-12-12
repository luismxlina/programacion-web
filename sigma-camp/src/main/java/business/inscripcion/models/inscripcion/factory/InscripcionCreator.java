package business.inscripcion.models.inscripcion.factory;

import java.util.Date;


import business.inscripcion.models.inscripcion.Inscripcion;

/**
 * Clase abstracta que actúa como creador (creator) para las inscripciones.
 * Define métodos abstractos para registrar inscripciones completas y parciales.
 */
public abstract class InscripcionCreator {
	
	 /**
     * Registra una inscripción completa.
     *
     * @param participante Identificador del participante.
     * @param campamento   Identificador del campamento.
     * @param fecha        Fecha de la inscripción.
     * @return InscripcionCompleta creada.
     */
	public abstract Inscripcion registrarInscripcionCompleta(int participante, int campamento, Date fecha);
	
	/**
     * Registra una inscripción parcial.
     *
     * @param participante Identificador del participante.
     * @param campamento   Identificador del campamento.
     * @param fecha        Fecha de la inscripción.
     * @return InscripcionParcial creada.
     */
	public abstract Inscripcion registrarInscripcionParcial(int participante, int campamento, Date fecha);
	
}
