package es.uco.pw.business.inscripcion.models.inscripcion.factory;

import java.util.Date;

import es.uco.pw.business.campamento.handler.GestorCampamentos;
import es.uco.pw.business.inscripcion.models.inscripcion.Inscripcion;
import es.uco.pw.business.inscripcion.models.inscripcion.TipoInscripcion;

/**
 * Clase que representa una inscripción parcial a un campamento.
 * Extiende la clase abstracta Inscripcion.
 */
public class InscripcionParcial extends Inscripcion {
	private static final int PRECIOINICIAL = 100;
	private Double precio;
	private TipoInscripcion tipoInscripcion = TipoInscripcion.PARCIAL;

	public InscripcionParcial() {
		super();
	}

	/**
	 * Constructor por defecto que inicializa los atributos comunes de la
	 * inscripción parcial.
	 */
	public InscripcionParcial(int id_Participante, int id_Campamento, Date fechaInscripcion,
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
		int numeroActividades = GestorCampamentos.getInstance().getActividadesCampamento(idCampamento).size();
		return PRECIOINICIAL + (numeroActividades * 20);
	}

	@Override
	public Double getPrecio() {
		return this.precio;
	}

	/**
	 * Devuelve el tipo de inscripción.
	 */
	public TipoInscripcion getTipoInscripcion() {
		return this.tipoInscripcion;
	}

	/**
	 * Establece el tipo de inscripción.
	 */
	public void setTipoInscripcion(TipoInscripcion tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
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

}
