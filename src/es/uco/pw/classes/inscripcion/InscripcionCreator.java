package es.uco.pw.classes.inscripcion;

import java.util.Date;

public abstract class InscripcionCreator {
	
	public abstract Inscripcion registrarInscripcionCompleta(int participante, int campamento, Date fecha);
	
	public abstract Inscripcion registrarInscripcionParcial();
	
}
