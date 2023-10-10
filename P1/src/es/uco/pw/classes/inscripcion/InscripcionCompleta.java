package es.uco.pw.classes.inscripcion;

import java.util.Date;

public class InscripcionCompleta extends Inscripcion {
	
	public InscripcionCompleta()
	{
		this.setId_Participante(0);
		this.setId_Campamento(0);
		this.setFechaInscripcion(new Date());
		this.setPrecio(0.0);
		this.setCancelable(false);
	}

	@Override
	public String toString() {
		return "InscripcionCompleta [getId_Participante()=" + getId_Participante() + ", getId_Campamento()="
				+ getId_Campamento() + ", getFechaInscripcion()=" + getFechaInscripcion() + ", getPrecio()="
				+ getPrecio() + ", getCancelable()=" + getCancelable() + "]";
	}
	
	

}
