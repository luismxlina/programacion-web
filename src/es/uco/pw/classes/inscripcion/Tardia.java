package es.uco.pw.classes.inscripcion;

import java.util.Date;

public class Tardia extends InscripcionCreator{
	
	public Inscripcion registrarInscripcionCompleta(int participante, int campamento, Date fecha)
	{
		Inscripcion nueva = new InscripcionCompleta();
		nueva.setId_Participante(participante);
		nueva.setId_Campamento(campamento);
		nueva.setFechaInscripcion(fecha);
		nueva.setCancelable(false);
		return nueva;
	}
	
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
