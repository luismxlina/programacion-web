package es.uco.pw.classes.inscripcion;

import java.util.Date;

/**
 * Clase que implementa el patrón Creator para la creación de inscripciones tempranas.
 * Extiende la clase abstracta InscripcionCreator.
 */
public class Temprana extends InscripcionCreator{
	
	/**
     * Registra una nueva inscripción completa temprana.
     *
     * @param participante Identificador del participante.
     * @param campamento   Identificador del campamento.
     * @param fecha        Fecha de la inscripción.
     * @return Nueva inscripción completa temprana.
     */
	public Inscripcion registrarInscripcionCompleta(int participante, int campamento, Date fecha)
	{
		Inscripcion nueva = new InscripcionCompleta();
		nueva.setId_Participante(participante);
		nueva.setId_Campamento(campamento);
		nueva.setFechaInscripcion(fecha);
		nueva.setCancelable(true);
		return nueva;
	}
	
	 /**
     * Registra una nueva inscripción parcial temprana.
     *
     * @param participante Identificador del participante.
     * @param campamento   Identificador del campamento.
     * @param fecha        Fecha de la inscripción.
     * @return Nueva inscripción parcial temprana.
     */
	public Inscripcion registrarInscripcionParcial(int participante, int campamento, Date fecha)
	{
		Inscripcion nueva = new InscripcionParcial();
		nueva.setId_Participante(participante);
		nueva.setId_Campamento(campamento);
		nueva.setFechaInscripcion(fecha);
		nueva.setCancelable(true);
		return nueva;
	}

}
