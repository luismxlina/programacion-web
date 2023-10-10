package es.uco.pw.classes.inscripcion;

public class InscripcionCreator {
	
	public Inscripcion registro(int idParticipante, int idCampamento, Double precio, Boolean esCompleto, Boolean esTemprano)
	{
		if(esCompleto)
		{
			if(esTemprano)
			{
				Inscripcion obj = new InscripcionCompleta();
				obj.setId_Participante(idParticipante);
				obj.setId_Campamento(idCampamento);
				obj.setPrecio(precio);
				obj.setCancelable(true);
				return obj;
			}
			else
			{
				Inscripcion obj = new InscripcionCompleta();
				obj.setId_Participante(idParticipante);
				obj.setId_Campamento(idCampamento);
				obj.setPrecio(precio);
				obj.setCancelable(false);
				return obj;
			}
		}
		else
		{
			if(esTemprano)
			{
				Inscripcion obj = new InscripcionParcial();
				obj.setId_Participante(idParticipante);
				obj.setId_Campamento(idCampamento);
				obj.setPrecio(precio);
				obj.setCancelable(true);
				return obj;
			}
			else
			{
				Inscripcion obj = new InscripcionParcial();
				obj.setId_Participante(idParticipante);
				obj.setId_Campamento(idCampamento);
				obj.setPrecio(precio);
				obj.setCancelable(false);
				return obj;
			}
		}
	}

}
