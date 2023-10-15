package es.uco.pw.classes.inscripcion;

import java.util.Date;

/**
 * Clase que implementa el patrón Creator para la creación de inscripciones tardías.
 * Extiende la clase abstracta InscripcionCreator.
 */
public class Tardia extends InscripcionCreator{
	
	 /**
     * Registra una nueva inscripción completa tardía.
     *
     * @param participante Identificador del participante.
     * @param campamento   Identificador del campamento.
     * @param fecha        Fecha de la inscripción.
     * @return Nueva inscripción completa tardía.
     */
	public Inscripcion registrarInscripcionCompleta(int participante, int campamento, Date fecha)
	{
		Inscripcion nueva = new InscripcionCompleta();
		nueva.setId_Participante(participante);
		nueva.setId_Campamento(campamento);
		nueva.setFechaInscripcion(fecha);
		nueva.setCancelable(false);
		return nueva;
	}
	
	/**
     * Registra una nueva inscripción parcial tardía.
     *
     * @param participante Identificador del participante.
     * @param campamento   Identificador del campamento.
     * @param fecha        Fecha de la inscripción.
     * @return Nueva inscripción parcial tardía.
     */
	public Inscripcion registrarInscripcionParcial(int participante, int campamento, Date fecha)
	{
		Inscripcion nueva = new InscripcionParcial();
		nueva.setId_Participante(participante);
		nueva.setId_Campamento(campamento);
		nueva.setFechaInscripcion(fecha);
		nueva.setCancelable(false);
		return nueva;
	}

}
