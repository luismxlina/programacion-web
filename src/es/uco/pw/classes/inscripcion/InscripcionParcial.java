package es.uco.pw.classes.inscripcion;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.classes.actividad.Actividad;

public class InscripcionParcial extends Inscripcion {
	
	public InscripcionParcial()
	{
		this.setId_Participante(0);
		this.setId_Campamento(0);
		this.setFechaInscripcion(new Date());
		this.setCancelable(false);
	}

	@Override
	public String toString() {
		return "InscripcionParcial [getId_Participante()=" + getId_Participante() + ", getId_Campamento()="
				+ getId_Campamento() + ", getFechaInscripcion()=" + getFechaInscripcion() + ", getPrecio()="
				+ getPrecio() + ", getCancelable()=" + getCancelable() + "]";
	}
	
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
