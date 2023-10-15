package es.uco.pw.classes.inscripcion;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.classes.actividad.Actividad;

/**
 * Clase que representa una inscripción parcial a un campamento.
 * Extiende la clase abstracta Inscripcion.
 */
public class InscripcionParcial extends Inscripcion {
	
	/**
     * Constructor por defecto que inicializa los atributos comunes de la inscripción parcial.
     */
	public InscripcionParcial()
	{
		this.setId_Participante(0);
		this.setId_Campamento(0);
		this.setFechaInscripcion(new Date());
		this.setCancelable(false);
	}

	/**
     * Genera una representación de cadena de la inscripción parcial.
     *
     * @return Cadena que representa la inscripción parcial.
     */
	@Override
	public String toString() {
		return "InscripcionParcial [getId_Participante()=" + getId_Participante() + ", getId_Campamento()="
				+ getId_Campamento() + ", getFechaInscripcion()=" + getFechaInscripcion() + ", getPrecio()="
				+ getPrecio() + ", getCancelable()=" + getCancelable() + "]";
	}
	
	/**
     * Establece el precio de la inscripción parcial en función de las actividades en las que participa.
     *
     * @param actividades Lista de actividades en las que participa el asistente.
     */
	public void setPrecio(ArrayList<Actividad> actividades)
	{
		Double precio = 100.0;
		for(Actividad actividad : actividades)
		{
			if(actividad.getHora().equals("Mañana"))
			{
				precio += 20.0;
			}
		}
		this.setPrecio(precio);
	}

}
