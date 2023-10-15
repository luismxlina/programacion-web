package es.uco.pw.classes.inscripcion;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.classes.actividad.Actividad;

/**
 * Clase que representa una inscripción completa a un campamento.
 * Extiende la clase abstracta Inscripcion.
 */
public class InscripcionCompleta extends Inscripcion {
	
	 /**
     * Constructor que inicializa los atributos de la inscripción completa.
     * El identificador del participante y del campamento se establecen en 0.
     * La fecha de inscripción se establece en la fecha actual.
     * La inscripción no es cancelable.
     */
	public InscripcionCompleta()
	{
		this.setId_Participante(0);
		this.setId_Campamento(0);
		this.setFechaInscripcion(new Date());
		this.setCancelable(false);
	}

	
	
	/**
     * Devuelve una representación en cadena de la inscripción completa.
     *
     * @return Cadena que representa la inscripción completa.
     */
	@Override
	public String toString() {
		return "InscripcionCompleta [getId_Participante()=" + getId_Participante() + ", getId_Campamento()="
				+ getId_Campamento() + ", getFechaInscripcion()=" + getFechaInscripcion() + ", getPrecio()="
				+ getPrecio() + ", getCancelable()=" + getCancelable() + "]";
	}
	
	/**
     * Calcula y establece el precio de la inscripción completa
     * basado en el número de actividades asociadas.
     *
     * @param actividades Lista de actividades asociadas a la inscripción completa.
     */
	public void setPrecio(ArrayList<Actividad> actividades)
	{
		Double precio = 300.0;
		precio += (actividades.size()*20);
		this.setPrecio(precio);
	}

}
