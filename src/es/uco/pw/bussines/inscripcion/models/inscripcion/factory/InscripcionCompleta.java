package es.uco.pw.bussines.inscripcion.models.inscripcion.factory;

import es.uco.pw.bussines.campamento.handlers.GestorCampamentos;
import es.uco.pw.bussines.campamento.models.campamento.Campamento;
import es.uco.pw.bussines.inscripcion.models.inscripcion.Inscripcion;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa una inscripción completa a un campamento.
 * Extiende la clase abstracta Inscripcion.
 */
public class InscripcionCompleta extends Inscripcion {

	private static final int PRECIOINICIAL = 300;
	private Double precio;

	/**
	 * Constructor que inicializa los atributos de la inscripción completa.
	 * El identificador del participante y del campamento se establecen en 0.
	 * La fecha de inscripción se establece en la fecha actual.
	 * La inscripción no es cancelable.
	 */
	public InscripcionCompleta() {
		super();
	}

	public InscripcionCompleta(int id_Participante, int id_Campamento, Date fechaInscripcion,
			boolean cancelable) {
		super(id_Participante, id_Campamento, fechaInscripcion, cancelable);
		this.precio = calculatePrice(id_Campamento);

	}

	/**
	 * Calcula el precio de la inscripción.
	 * 
	 * @param costeIncial
	 * @param idCampamento
	 * @return precio de la inscripción
	 */
	private double calculatePrice(int idCampamento) {
		int numeroActividades = GestorCampamentos.getInstance(new ArrayList<Campamento>()).getCampamento(idCampamento)
				.getActividades().size();
		return PRECIOINICIAL + (numeroActividades * 20);
	}
	@Override
	public Double getPrecio() {
		return this.precio;
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

}
