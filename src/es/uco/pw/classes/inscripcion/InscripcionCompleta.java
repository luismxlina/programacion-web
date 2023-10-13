package es.uco.pw.classes.inscripcion;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.classes.actividad.Actividad;

public class InscripcionCompleta extends Inscripcion {
	
	public InscripcionCompleta()
	{
		this.setId_Participante(0);
		this.setId_Campamento(0);
		this.setFechaInscripcion(new Date());
		this.setCancelable(false);
	}

	@Override
	public String toString() {
		return "InscripcionCompleta [getId_Participante()=" + getId_Participante() + ", getId_Campamento()="
				+ getId_Campamento() + ", getFechaInscripcion()=" + getFechaInscripcion() + ", getPrecio()="
				+ getPrecio() + ", getCancelable()=" + getCancelable() + "]";
	}
	
	public void setPrecio(ArrayList<Actividad> actividades)
	{
		Double precio = 300.0;
		precio += (actividades.size()*20);
		this.setPrecio(precio);
	}

}
