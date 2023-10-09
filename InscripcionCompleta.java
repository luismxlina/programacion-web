package Practica1;

import java.util.Date;

public class InscripcionCompleta extends Inscripcion {
	
	private static final String TIPO_INSCRIPCION = "Completa";
	
	public InscripcionCompleta() {
		// TODO Auto-generated constructor stub
	}

	public InscripcionCompleta(int idParticipante, int idCampamento, Date fechaInscripcion, int precioInscripcion) {
		super(idParticipante, idCampamento, fechaInscripcion, precioInscripcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Inscripción " + TIPO_INSCRIPCION + ":\n" +
                "ID Participante: " + getIdParticipante() + "\n" +
                "ID Campamento: " + getIdCampamento() + "\n" +
                "Fecha de inscripción: " + getFechaInscripcion() + "\n" +
                "Precio de inscripción: " + getPrecioInscripcion();
	}
	
	

}
